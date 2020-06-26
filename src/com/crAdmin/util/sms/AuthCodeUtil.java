package com.crAdmin.util.sms;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证码工具类
 * 
 * @ClassName: AuthCodeUtil
 * @Description:
 * @author 桑越
 * @date 2015-10-22 上午11:47:16
 * @version V1.0
 */
public class AuthCodeUtil {

	/**
	 * 用来存放验证码
	 */
	public static Map<String, AuthCode> codeMap = new HashMap<String, AuthCode>();

	/**
	 * 验证码类
	 * 
	 * @ClassName: AuthCode
	 * @Description:
	 * @author 桑越
	 * @date 2015-10-22 上午11:47:26
	 * @version V1.0
	 */
	public static class AuthCode {
		/** 手机号码 */
		private String phoneNum;
		/** 验证码 */
		private String authCode;
		/** 发送时间 */
		private Date sendTime;
		/** 过期时间 */
		private Date expireTime;

		/**
		 * @return 手机号码
		 */
		public String getPhoneNum() {
			return phoneNum;
		}

		/**
		 * @return 验证码
		 */
		public String getAuthCode() {
			return authCode;
		}

		/**
		 * @return 过期时间
		 */
		public Date getExpireTime() {
			return expireTime;
		}

		/**
		 * @param expireTime
		 *            过期时间
		 */
		public void setExpireTime(Date expireTime) {
			this.expireTime = expireTime;
		}

		/**
		 * @return 发送时间
		 */
		public Date getSendTime() {
			return sendTime;
		}

		/**
		 * @param phoneNum
		 *            手机号码
		 */
		public void setPhoneNum(String phoneNum) {
			this.phoneNum = phoneNum;
		}

		/**
		 * @param authCode
		 *            验证码
		 */
		public void setAuthCode(String authCode) {
			this.authCode = authCode;
		}

		/**
		 * @param sendTime
		 *            发送时间
		 */
		public void setSendTime(Date sendTime) {
			this.sendTime = sendTime;
		}
	}
}
