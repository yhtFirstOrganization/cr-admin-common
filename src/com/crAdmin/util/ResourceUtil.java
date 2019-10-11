package com.crAdmin.util;

import java.util.ResourceBundle;

/**
 * 项目参数工具类
 * 
 * @ClassName: ResourceUtil
 * @Description:获取系统级别的参数
 * @author 桑越
 * @date 2015-11-9 下午4:16:33
 * @version V1.0
 */
public class ResourceUtil {

	private static final ResourceBundle bundle = java.util.ResourceBundle
			.getBundle("myconfig");

	/**
	 * 获得sessionInfo名字
	 * 
	 * @return
	 */
	public static final String getSessionInfoName() {
		return bundle.getString("sessionInfoName");
	}

	public static final String getRegisterMobileCode() {
		return bundle.getString("registerMobileCode");
	}

	public static final String getGoodsCatPic_Directory() {
		return bundle.getString("goodsCatPic");
	}

	/**
	 * 图片缀名
	 */
	public static final String getImage_Ext() {
		return bundle.getString("images_Ext");
	}

	public static final String getGoods_Img_Directory() {
		return bundle.getString("goods_Img_Directory");
	}

	/**
	 * FTP所需
	 * 
	 * @Title: getWaterPath
	 * @Description: TODO(这里用一句话描述这个方法的作�?
	 * @param @return    设定文件
	 * @return String    返回类型
	 */
	public static String getWaterPath() {
		// TODO Auto-generated method stub
		return bundle.getString("waterPath");
	}

	public static String getWebPath() {
		return bundle.getString("webpath");
	}

	public static String getFtpIp() {
		return bundle.getString("ftpIp");
	}

	public static String getFtpUserName() {
		return bundle.getString("ftpUserName");
	}

	public static String getFtpPassword() {
		return bundle.getString("ftpPassword");
	}

	public static String getFtpPort() {
		return bundle.getString("ftpPort");
	}

	public static String getFtpDocPath() {
		return bundle.getString("ftpDocPath");
	}

	public static String getHeadImgPath() {
		return bundle.getString("headImgPath");
	}

	/**
	 * 通过KEY获取值
	 * 
	 * @Title: get
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param key
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 桑越
	 * @throws
	 */
	public static final String get(String key) {
		return bundle.getString(key);
	}

	/**
	 * 编辑器所需要
	 * 
	 * @Title: getKindeditorFieldName
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 桑越
	 */
	public static final String getKindeditorFieldName() {
		return bundle.getString("kindeditorFieldName");
	}

	public static final String getKindeditorUploadFileExts() {
		return bundle.getString("kindeditorUploadFileExts");
	}

	public static final String getDomain() {
		return bundle.getString("domain");
	}

	/**
	 * @Title: getRandNum
	 * @Description: TODO(获取有效时间)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wangmei
	 */
	public static final String getValidTime(String type) {
		if ("1".equals(type)) {
			return bundle.getString("validTimeForMobile");
		} else {
			return bundle.getString("validTimeForEmail");
		}
	}

	/**
	 * 获取联行支付的MerId
	 * 
	 * @Title: getLhMerId
	 * @Description: TODO(这里用一句话描述这个方法的作�?
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @author 桑越
	 */
	public static final String getLhMerId() {
		return bundle.getString("LhMerId");
	}

	/**
	 * 获取联行支付的授权码key
	 * 
	 * @Title: getLhKey
	 * @Description: TODO(这里用一句话描述这个方法的作�?
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @author 桑越
	 */
	public static final String getLhKey() {
		return bundle.getString("LhKey");
	}

	/**
	 * 支付完成后支付结果返回到该url，主要用于结果展�?
	 * 
	 * @Title: getLhDealReturn
	 * @Description: TODO(这里用一句话描述这个方法的作�?
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @author 桑越
	 */
	public static final String getLhDealReturn() {
		return bundle.getString("LhDealReturn");
	}

	/**
	 * 支付完成后支付结果�?知到该url，主要用于�?知接�?
	 * 
	 * @Title: getLhDealNotify
	 * @Description: TODO(这里用一句话描述这个方法的作�?
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @author 桑越
	 */
	public static final String getLhDealNotify() {
		return bundle.getString("LhDealNotify");
	}

	/**
	 * 获得上传文件要放到那个目录
	 * 
	 * @return
	 */
	public static final String getUploadDirectory() {
		return bundle.getString("uploadDirectory");
	}

	public static Long getUploadFileMaxSize() {
		// TODO Auto-generated method stub
		return Long.valueOf(bundle.getString("uploadFileMaxSize"));
	}

	public static String getApkVersions_Directory() {
		// TODO Auto-generated method stub
		return bundle.getString("apkVersionsDirectory");
	}

	public static String getIApk_Ext() {
		// TODO Auto-generated method stub
		return bundle.getString("apk_Ext");
	}

}
