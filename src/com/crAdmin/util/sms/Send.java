package com.crAdmin.util.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.crAdmin.bean.Message;

/**
 * 舜耕交易百分通联短信接口发送类 例如：感谢您注册舜耕交易网，您的手机验证码是 8563 ，非本人操作请忽略 【舜耕交易网】 签名会自动加，无需人工拼串。
 * 
 * @ClassName: Send
 * @Description:
 * @author 桑越
 * @date 2015-10-22 上午11:43:39
 * @version V1.0
 */
public class Send {

	public static String SMS(String postData, String postUrl) {
		try {
			// 发送POST请求
			URL url = new URL(postUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setUseCaches(false);
			conn.setDoOutput(true);

			conn.setRequestProperty("Content-Length", "" + postData.length());
			OutputStreamWriter out = new OutputStreamWriter(
					conn.getOutputStream(), "UTF-8");
			out.write(postData);
			out.flush();
			out.close();

			// 获取响应状态
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				System.out.println("connect failed!");
				return "";
			}
			// 获取响应内容体
			String line, result = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8"));
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
			in.close();
			return result;
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		return "";
	}

	/**
	 * 单条发送短信
	 * 
	 */
	public static Message mmsSend(String mobile, String code) {
//		String content = "感谢您注册舜耕交易网，您的手机验证码是 "+code+" ，非本人操作请忽略";
		Message msg = new Message();
		try {
//			String PostData = "sname=dlwsdd00&spwd=YJl9WC0D&scorpid=&sprdid=1012818&sdst="
//					+ mobile + "&smsg=" + content + "【舜耕交易网】";
			if (code == null){
				msg.setCode("1111");
				msg.setMsg("发送失败");
				return msg;
			}
//			String ret = SMS(PostData,
//					"http://cf.lmobile.cn/submitdata/Service.asmx/g_Submit");
//			// 输出发送结果
//			System.out.println(ret);
//			// 把发送结果的XML转换为Map
//			Map map = Util.xmlToMap(ret);
			msg.setCode("0000");
			msg.setMsg("发送成功");
			return msg;

		} catch (Exception e) {
			msg.setCode("1111");
			msg.setMsg("发送失败");
			return msg;
		}
	}

	public static void main(String[] args) {

		String phone = "13122895272";
		String content = "感谢您注册舜耕交易网，您的手机验证码是 8563 ，非本人操作请忽略";
		System.out.println(content.length());
		System.out.println(mmsSend(phone, content));

	}

}
