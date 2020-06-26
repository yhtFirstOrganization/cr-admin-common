package com.crAdmin.util;

import java.util.Date;
import java.util.Random;

/**
 * 号码产生器，用来生成各种号码，如订单号码、产品编码
 * 
 * @ClassName: NumGenerator
 * @Description:
 * @author 桑越
 * @date 2015-11-8 下午9:24:34
 * @version V1.0
 */
public class NumGenerator {

	/**
	 * 编号生成器
	 * 
	 * @Title: numGenerate
	 * @Description:
	 * @author 桑越
	 * @date 2015-11-8 下午9:24:43
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 * @version V1.0
	 */
	@SuppressWarnings("deprecation")
	public static String numGenerate() {
		int month = new Date().getMonth()+1;
		int day = new Date().getDate();
		return "ddb" + month + "" + day + "" + new Random().nextInt(1000) + "";
	}

}
