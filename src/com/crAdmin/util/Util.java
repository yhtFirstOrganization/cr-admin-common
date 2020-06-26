package com.crAdmin.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.alibaba.fastjson.JSONObject;
import com.crAdmin.bean.User;

/**
 * 把数据转换为指定格式的工具类
 * 
 * @ClassName: Util
 * @Description:
 * @author 桑越
 * @date 2015-10-22 上午11:16:47
 * @version V1.0
 */
@SuppressWarnings("restriction")
public class Util {

	/**
	 * 判断是否为空
	 * 
	 * @Title: isNull
	 * @Description:
	 * @author 桑越
	 * @date 2015-10-22 上午11:16:56
	 * @param @param
	 *            object
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws @version
	 *             V1.0
	 */
	public static String isNull(Object object) {
		try {
			if ("null".equals(object)) {
				return "";
			} else {
				return object == null ? "" : object + "";
			}
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 提供精确的小数位四舍五入处理
	 * 
	 * @Title: round
	 * @Description:
	 * @author 桑越
	 * @date 2015-10-22 上午11:17:06
	 * @param @param
	 *            val 需要四舍五入的数字
	 * @param @param
	 *            scale 小数点后保留几位
	 * @param @return 设定文件
	 * @return double 返回类型
	 * @throws @version
	 *             V1.0
	 */
	public static double round(double val, int scale) {
		if (scale < 0) {
			scale = 0;
		}
		BigDecimal bd = new BigDecimal(Double.toString(val));
		BigDecimal divisor = new BigDecimal("1");
		return bd.divide(divisor, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 
	 * add 方法
	 * <p>
	 * 方法说明: 两个数值相加
	 * </p>
	 * 
	 * @param val1
	 * @param val2
	 * @return double
	 * @author FangWen
	 * @date 2015-5-27 下午15:13:00
	 */
	public static double add(double val1, double val2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(val1));
		BigDecimal bd2 = new BigDecimal(Double.toString(val2));
		return bd1.add(bd2).doubleValue();
	}

	/**
	 * xml转换为Map格式
	 * 
	 * @Title: xmlToMap
	 * @Description:
	 * @author 桑越
	 * @date 2015-10-22 上午11:23:02
	 * @param @param
	 *            xmlString
	 * @param @return
	 * @param @throws
	 *            DocumentException 设定文件
	 * @return Map 返回类型
	 * @throws @version
	 *             V1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map xmlToMap(String xmlString) throws DocumentException {
		Document doc = DocumentHelper.parseText(xmlString);
		Element rootElement = doc.getRootElement();
		Map<String, Object> map = new HashMap<String, Object>();
		map = (Map<String, Object>) xmlToMap(rootElement);
		System.out.println(map);
		// 到此xmlTomap完成，下面的代码是将map转成了json用来观察我们的xmlTomap转换的是否ok
		String string = JSONObject.toJSONString(map);
		System.out.println(string);
		return map;
	}

	/**
	 * xmlToMap 方法
	 * 
	 * @Title: xmlToMap
	 * @Description:
	 * @author 桑越
	 * @date 2015-10-22 上午11:23:24
	 * @param @param
	 *            element
	 * @param @return 设定文件
	 * @return Object 返回类型
	 * @throws @version
	 *             V1.0
	 */
	@SuppressWarnings("unchecked")
	private static Object xmlToMap(Element element) {
		System.out.println(element);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Element> elements = element.elements();
		if (elements.size() == 0) {
			map.put(element.getName(), element.getText());
			if (!element.isRootElement()) {
				return element.getText();
			}
		} else if (elements.size() == 1) {
			map.put(elements.get(0).getName(), xmlToMap(elements.get(0)));
		} else if (elements.size() > 1) {
			// 多个子节点的话就得考虑list的情况了，比如多个子节点有节点名称相同的
			// 构造一个map用来去重
			Map<String, Element> tempMap = new HashMap<String, Element>();
			for (Element ele : elements) {
				tempMap.put(ele.getName(), ele);
			}

			Set<String> keySet = tempMap.keySet();
			for (String string : keySet) {
				Namespace namespace = tempMap.get(string).getNamespace();
				List<Element> elements2 = element.elements(new QName(string, namespace));
				// 如果同名的数目大于1则表示要构建list
				if (elements2.size() > 1) {
					List<Object> list = new ArrayList<Object>();
					for (Element ele : elements2) {
						list.add(xmlToMap(ele));
					}
					map.put(string, list);
				} else {
					// 同名的数量不大于1则直接递归去
					map.put(string, xmlToMap(elements2.get(0)));
				}
			}
		}
		return map;
	}

	/**
	 * base64字符串转化成图片
	 * 
	 * @Title: generateImage
	 * @Description:
	 * @author 桑越
	 * @date 2015-10-22 上午11:24:10
	 * @param @param
	 *            imgUrl 生成图片完整路径
	 * @param @param
	 *            imgStr 图片字符串
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws @version
	 *             V1.0
	 */
	public static boolean generateImage(String imgUrl, String imgStr) {
		OutputStream out = null;
		// 图像数据为空
		if (imgStr == null) {
			return false;
		}

		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] byteArr = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < byteArr.length; ++i) {
				// 调整异常数据
				if (byteArr[i] < 0) {
					byteArr[i] += 256;
				}
			}

			// 生成png图片
			out = new FileOutputStream(imgUrl);
			out.write(byteArr);
			out.flush();
			out.close();

			return true;
		} catch (Exception e0) {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e1) {

				}
			}
			return false;
		}
	}

	/**
	 * 图片转化成base64字符串
	 * 
	 * @Title: getImageStr
	 * @Description:
	 * @author 桑越
	 * @date 2015-10-22 上午11:25:48
	 * @param @param
	 *            imgUrl 图片完整路径
	 * @param @return 图片字符串
	 * @return String 返回类型
	 * @throws @version
	 *             V1.0
	 */
	public static String getImageStr(String imgUrl) {
		// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		InputStream in = null;
		byte[] data = null;

		// 读取图片字节数组
		try {
			in = new FileInputStream(imgUrl);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e0) {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
					return null;
				}
			}
		}

		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		// 返回Base64编码过的字节数组字符串
		return encoder.encode(data);
	}

	/**
	 * 从session中获取companyId
	 * 
	 * @param session
	 * @return
	 */
	public static String getCompanyId(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return null;
		}
		return user.getCompanyId();
	}

	public static Integer getUserId(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return null;
		}
		return user.getUserId();
	}

	public static String getLoginName(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return null;
		}
		return user.getLoginName();
	}

}
