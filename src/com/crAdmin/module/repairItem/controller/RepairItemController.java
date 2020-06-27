package com.crAdmin.module.repairItem.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crAdmin.bean.Car;
import com.crAdmin.bean.Message;
import com.crAdmin.bean.Repair;
import com.crAdmin.bean.RepairItem;
import com.crAdmin.bean.User;
import com.crAdmin.module.car.service.CarService;
import com.crAdmin.module.repair.service.RepairService;
import com.crAdmin.module.repairItem.service.RepairItemService;
import com.crAdmin.util.BaseController;
import com.crAdmin.util.annotation.LogAnnotation;
import com.crAdmin.util.annotation.OptionType;

/**
 * 维修项目相关controller
 * 
 * @ClassName: RepairItemController
 * @Description:
 * @author 桑越
 * @date 2015-12-8 下午8:59:23
 * @version V1.0
 */
@Controller("repairItemController")
@RequestMapping("/repairItem")
public class RepairItemController extends BaseController {
	@Autowired
	private RepairItemService repairItemService;
	@Autowired
	private CarService carService;
	@Autowired
	private RepairService repairService;

	/**
	 * 查询维修详细记录列表
	 * 
	 * @param provId
	 * @return
	 */
	@RequestMapping("/queryRepairItemList.do")
	@ResponseBody
//	@LogAnnotation(option = "查询维修详细记录列表", optionType = OptionType.query)
	public Object queryRepairItemList(Integer repairId) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (repairId == null) {// 如果维修记录id为null，则代表前台页面在新增维修记录和维修项目，此时直接返回
			returnMap.put("data", null);
			return returnMap;
		}
		// 维修记录id不为null，则代表修改维修记录和维修项目
		RepairItem repairItem = new RepairItem();
		repairItem.setIsdelete("0");
		repairItem.setRepairId(repairId);
		List<RepairItem> list = repairItemService.queryRepairItemList(repairItem);

		returnMap.put("data", list);

		return returnMap;
	}

	/**
	 * 导出excel
	 * 
	 * @param provId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/exportToExcel.do")
	@ResponseBody
	@LogAnnotation(option = "导出维修详细记录列表Excel", optionType = OptionType.export)
	public Object exportToExcel(Integer repairId, HttpServletRequest request) throws Exception {
		String flag = "1";
		Repair repair = new Repair();
		repair.setRepairId(repairId);
		// 查询汽车基本信息
		repair.setIsdelete("0");
		List<Repair> queryRepairInfoList = repairService.queryRepairList(repair);
		Repair queryRepairInfo = null;
		if (queryRepairInfoList == null || queryRepairInfoList.size() <= 0) {
			flag = "0";
		}
		queryRepairInfo = queryRepairInfoList.get(0);
		Integer carid = queryRepairInfo.getCarId();
		if (carid == null) {
			flag = "0";
		}
		// 查询汽车基本信息
		Car pcar = new Car();
		pcar.setCarId(carid);
		pcar.setIsdelete("0");
		Car queryCarInfo = carService.queryCarInfo(pcar);

		RepairItem repairItem = new RepairItem();
		repairItem.setIsdelete("0");
		repairItem.setRepairId(repairId);
		List<RepairItem> list = repairItemService.queryRepairItemList(repairItem);

		String root = request.getSession().getServletContext().getRealPath(File.separator);
		String imagePath = File.separator + "excelfiles";
		String path = root + ".." + imagePath;
		if (!(new File(path).isDirectory())) {
			new File(path).mkdir();
		}
		String image = File.separator + queryCarInfo.getOwnerName() + "-汽车维修记录.xls";
		String filepath = path + image;
		imagePath += image;

		try {
			exportExcel(queryCarInfo, queryRepairInfo, list, filepath, request);
		} catch (Exception e) {
			flag = "0";
		}

		Message message = new Message();
		message.setCode(flag);
		message.setMsg(imagePath);
		return message;
	}

	/**
	 * 导出内容到excel
	 * 
	 * @param queryCarInfo
	 * @param queryRepairInfo
	 * @param list
	 * @param request
	 * @throws Exception
	 */
	@LogAnnotation(option = "导出汽车维修详细记录列表", optionType = OptionType.export)
	private void exportExcel(Car queryCarInfo, Repair queryRepairInfo, List<RepairItem> list, String filepath,
			HttpServletRequest request) throws Exception {
		// 创建工作薄
		WritableWorkbook workbook = Workbook.createWorkbook(new File(filepath));
		// 创建新的一页
		WritableSheet sheet = workbook.createSheet("汽车维修记录", 0);

		creatMainMsgArea(sheet, request);

		creatCarArea(sheet, queryCarInfo);

		creatRepairArea(sheet, queryRepairInfo);

		creatRepairItemArea(sheet, list);
		// 把创建的内容写入到输出流中，并关闭输出流
		workbook.write();
		workbook.close();
		// os.close();
	}

	/**
	 * 店铺信息
	 * 
	 * @param sheet
	 * @param request
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	private void creatMainMsgArea(WritableSheet sheet, HttpServletRequest request)
			throws RowsExceededException, WriteException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String userName = user.getUserName();

		int initrow = 0;
		// 构造表头
		int row = initrow;
		sheet.mergeCells(0, row, 7, row);// 添加合并单元格，第一个参数是起始列，第二个参数是起始行，第三个参数是终止列，第四个参数是终止行
		sheet.mergeCells(0, ++row, 7, row);
		sheet.mergeCells(0, ++row, 7, row);

		WritableFont bold = new WritableFont(WritableFont.ARIAL, 20, WritableFont.BOLD);// 设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体显示
		WritableCellFormat cellFormat = new WritableCellFormat(bold);// 生成一个单元格样式控制对象
		cellFormat.setAlignment(jxl.format.Alignment.LEFT);// 单元格中的内容水平方向居中
		cellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格的内容垂直方向居中
		row = initrow;
		Label lable = new Label(0, row, "秦皇岛" + userName + "汽车服务有限公司", cellFormat);
		sheet.setRowView(row, 600, false);// 设置第一行的高度
		sheet.addCell(lable);

		bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);// 设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体显示
		cellFormat = new WritableCellFormat(bold);// 生成一个单元格样式控制对象
		cellFormat.setAlignment(jxl.format.Alignment.LEFT);// 单元格中的内容水平方向居中
		cellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格的内容垂直方向居中
		lable = new Label(0, ++row, "地址："+ user.getAddr() , cellFormat);
		sheet.setRowView(row, 400, false);// 设置第一行的高度
		sheet.addCell(lable);

		lable = new Label(0, ++row, "电话：" + user.getTel(), cellFormat);
		sheet.setRowView(row, 400, false);// 设置第一行的高度
		sheet.addCell(lable);

		// 添加图片对象,jxl只支持png格式图片
//		String root = request.getSession().getServletContext().getRealPath(File.separator);
//		File image = new File(root + File.separator + "images" + File.separator + "bmbz.png");
//		WritableImage wimage = new WritableImage(7, 0, 1.5, 2, image);
		// 暂时不显示图片
		// sheet.addImage(wimage);

	}

	// 创建汽车区域
	private void creatCarArea(WritableSheet sheet, Car queryCarInfo) throws RowsExceededException, WriteException {
		int initrow = 4;
		// 构造表头
		sheet.mergeCells(0, initrow, 7, initrow);// 添加合并单元格，第一个参数是起始列，第二个参数是起始行，第三个参数是终止列，第四个参数是终止行
		// 汽车form合并
		int collumn = 0, row = initrow + 1;
		setFormMergeCells(sheet, collumn, row, 4);

		collumn = 2;
		row = initrow + 1;
		setFormMergeCells(sheet, collumn, row, 3);

		collumn = 4;
		row = initrow + 1;
		setFormMergeCells(sheet, collumn, row, 3);

		collumn = 6;
		row = initrow + 1;
		setFormMergeCells(sheet, collumn, row, 3);

		WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);// 设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体显示
		WritableCellFormat cellFormat = new WritableCellFormat(bold);// 生成一个单元格样式控制对象
		cellFormat.setAlignment(jxl.format.Alignment.CENTRE);// 单元格中的内容水平方向居中
		cellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格的内容垂直方向居中
		Label lable = new Label(0, initrow, "车辆信息", cellFormat);
		sheet.setRowView(initrow, 600, false);// 设置第一行的高度
		sheet.addCell(lable);

		// sheet.setColumnView(0, 20);// 设置第一行的宽度
		// 创建要显示的具体内容
		WritableFont font = new WritableFont(WritableFont.ARIAL);// 选择字体
		font.setColour(Colour.DARK_TEAL);// 设置字体颜色为金黄色
		cellFormat = new WritableCellFormat(font);
		collumn = 0;
		row = initrow + 1;
		sheet.addCell(new Label(collumn, row++, "客户名称", cellFormat));
		sheet.addCell(new Label(collumn, row++, "联系人", cellFormat));
		sheet.addCell(new Label(collumn, row++, "联系电话", cellFormat));

		collumn = 2;
		row = initrow + 1;
		sheet.addCell(new Label(collumn, row++, queryCarInfo.getOwnerName()));
		sheet.addCell(new Label(collumn, row++, queryCarInfo.getLinker()));
		sheet.addCell(new Label(collumn, row++, queryCarInfo.getLinkerMobile()));

		collumn = 4;
		row = initrow + 1;
		sheet.addCell(new Label(collumn, row++, "车牌号", cellFormat));
		sheet.addCell(new Label(collumn, row++, "车型", cellFormat));
		sheet.addCell(new Label(collumn, row++, "车架号", cellFormat));

		collumn = 6;
		row = initrow + 1;
		sheet.addCell(new Label(collumn, row++, queryCarInfo.getLicensePlateNum()));
		sheet.addCell(new Label(collumn, row++, queryCarInfo.getCarType()));
		sheet.addCell(new Label(collumn, row++, queryCarInfo.getCarFrameNum()));

		sheet.mergeCells(2, row, 7, row);
		sheet.addCell(new Label(0, row, "车主详细位置", cellFormat));
		sheet.addCell(new Label(2, row, queryCarInfo.getOwnerAddress()));

	}

	/**
	 * 创建维修记录区域
	 * 
	 * @param sheet
	 * @param queryCarInfo
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	private void creatRepairArea(WritableSheet sheet, Repair repairBean) throws RowsExceededException, WriteException {
		int initrow = 9;
		// 构造表头
		sheet.mergeCells(0, initrow, 7, initrow);
		// form合并
		int collumn = 0, row = initrow + 1;
		sheet.mergeCells(collumn, row, collumn + 1, row);
		sheet.mergeCells(collumn, ++row, collumn + 1, row);
		sheet.mergeCells(collumn, ++row, collumn + 1, row);
		sheet.mergeCells(collumn, ++row, collumn + 1, row);

		collumn = 2;
		row = initrow + 1;
		sheet.mergeCells(collumn, row, collumn + 1, row);
		sheet.mergeCells(collumn, ++row, collumn + 1, row);
		sheet.mergeCells(collumn, ++row, collumn + 1, row);
		sheet.mergeCells(collumn, ++row, collumn + 1, row);

		collumn = 4;
		row = initrow + 1;
		sheet.mergeCells(collumn, row, collumn + 1, row);
		sheet.mergeCells(collumn, ++row, collumn + 1, row);
		sheet.mergeCells(collumn, ++row, collumn + 1, row);

		collumn = 6;
		row = initrow + 1;
		sheet.mergeCells(collumn, row, collumn + 1, row);
		sheet.mergeCells(collumn, ++row, collumn + 1, row);
		sheet.mergeCells(collumn, ++row, collumn + 1, row);

		WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);// 设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体显示
		WritableCellFormat cellFormat = new WritableCellFormat(bold);// 生成一个单元格样式控制对象
		cellFormat.setAlignment(jxl.format.Alignment.CENTRE);// 单元格中的内容水平方向居中
		cellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格的内容垂直方向居中
		Label lable = new Label(0, initrow, "维修信息", cellFormat);
		sheet.setRowView(initrow, 600, false);
		sheet.addCell(lable);

		// 创建要显示的具体内容
		WritableFont font = new WritableFont(WritableFont.ARIAL);// 选择字体
		font.setColour(Colour.DARK_TEAL);// 设置字体颜色为金黄色
		cellFormat = new WritableCellFormat(font);
		// 工单编号（位置特殊）
		sheet.addCell(new Label(6, initrow - 6, "工单编号:", cellFormat));
		sheet.addCell(new Label(7, initrow - 6, repairBean.getRepairNum()));

		collumn = 0;
		row = initrow + 1;
		sheet.addCell(new Label(collumn, row++, "维修顾问", cellFormat));
		sheet.addCell(new Label(collumn, row++, "维修时间", cellFormat));
		sheet.addCell(new Label(collumn, row++, "付款方式", cellFormat));
		sheet.addCell(new Label(collumn, row++, "实收总额", cellFormat));

		collumn = 2;
		row = initrow + 1;
		String repairPayment = repairBean.getRepairPayment();
		String repairPaymentStr = "";
		if ("0".equals(repairPayment)) {
			repairPaymentStr = "现金";
		} else if ("1".equals(repairPayment)) {
			repairPaymentStr = "支票";

		} else if ("2".equals(repairPayment)) {
			repairPaymentStr = "信用卡";

		}

		String repairTimeStr = "";
		try {
			Date repairTime = repairBean.getRepairTime();
			repairTimeStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(repairTime);
		} catch (Exception e) {
		}

		sheet.addCell(new Label(collumn, row++, repairBean.getServiceAdviser()));
		sheet.addCell(new Label(collumn, row++, repairTimeStr));
		sheet.addCell(new Label(collumn, row++, repairPaymentStr));
		sheet.addCell(new Label(collumn, row++,
				repairBean.getRepairActualSum() != null ? repairBean.getRepairActualSum().toString() : ""));

		collumn = 4;
		row = initrow + 1;
		sheet.addCell(new Label(collumn, row++, "维修工", cellFormat));
		sheet.addCell(new Label(collumn, row++, "行驶里程", cellFormat));
		sheet.addCell(new Label(collumn, row++, "应收总额", cellFormat));

		collumn = 6;
		row = initrow + 1;
		sheet.addCell(new Label(collumn, row++, repairBean.getRepairMan()));
		sheet.addCell(new Label(collumn, row++,
				(repairBean.getWarrantyStartMiles() != null
						&& repairBean.getWarrantyStartMiles().compareTo(new BigDecimal(0)) > 0)
								? repairBean.getWarrantyStartMiles().toString() : ""));
		sheet.addCell(new Label(collumn, row++, repairBean.getRepairSum().toString()));

	}

	/**
	 * 维修详细记录列表
	 * 
	 * @param sheet
	 * @param list
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	private void creatRepairItemArea(WritableSheet sheet, List<RepairItem> list)
			throws RowsExceededException, WriteException {
		int initrow = 15;
		WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);// 设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体显示
		WritableCellFormat cellFormat = new WritableCellFormat(bold);// 生成一个单元格样式控制对象
		cellFormat.setAlignment(jxl.format.Alignment.CENTRE);// 单元格中的内容水平方向居中
		cellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格的内容垂直方向居中
		cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		int collumn = 0;
		int row = initrow;
		sheet.addCell(new Label(collumn++, row, "项目编号", cellFormat));
		sheet.addCell(new Label(collumn++, row, "项目描述", cellFormat));
		sheet.addCell(new Label(collumn++, row, "项目单价", cellFormat));
		sheet.addCell(new Label(collumn++, row, "项目数量", cellFormat));
		sheet.addCell(new Label(collumn++, row, "项目单位", cellFormat));
		sheet.addCell(new Label(collumn++, row, "项目总额", cellFormat));
		sheet.addCell(new Label(collumn++, row, "配件编号", cellFormat));// 更换原因
																		// 字段显示改为
																		// 配件编号
		sheet.addCell(new Label(collumn++, row, "工时费", cellFormat));
		// 表格数据格式
		WritableCellFormat tableCellFormat = new WritableCellFormat();// 生成一个单元格样式控制对象
		tableCellFormat.setAlignment(jxl.format.Alignment.CENTRE);// 单元格中的内容水平方向居中
		tableCellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格的内容垂直方向居中
		// 边框
		tableCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		for (int i = 0; i < list.size(); i++) {
			row++;
			collumn = 0;
			RepairItem repairItem = list.get(i);
			sheet.addCell(new Label(collumn++, row, repairItem.getItemNum(), tableCellFormat));
			sheet.addCell(new Label(collumn++, row, repairItem.getItemDes(), tableCellFormat));
			sheet.addCell(new Label(collumn++, row, repairItem.getItemPrice().toString(), tableCellFormat));
			sheet.addCell(new Label(collumn++, row, repairItem.getItemQuantity().toString(), tableCellFormat));
			sheet.addCell(new Label(collumn++, row, repairItem.getItemUnit(), tableCellFormat));
			sheet.addCell(new Label(collumn++, row, repairItem.getItemSum().toString(), tableCellFormat));
			sheet.addCell(new Label(collumn++, row, repairItem.getReplaceReason(), tableCellFormat));
			sheet.addCell(new Label(collumn++, row, repairItem.getWorkHoursCost().toString(), tableCellFormat));

		}

	}

	/**
	 * 设置form的合并单元格
	 * 
	 * @param sheet
	 * @param collumn
	 * @param row
	 * @param i
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	private void setFormMergeCells(WritableSheet sheet, int collumn, int row, int rows)
			throws RowsExceededException, WriteException {
		for (int i = 0; i < rows; i++) {
			sheet.mergeCells(collumn, row, collumn + 1, row++);
		}
	}

}
