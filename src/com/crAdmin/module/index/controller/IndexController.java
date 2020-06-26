package com.crAdmin.module.index.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Decoder.BASE64Encoder;

import com.alibaba.fastjson.JSONObject;
import com.crAdmin.bean.User;
import com.crAdmin.module.index.service.IndexService;
import com.crAdmin.util.BaseController;
import com.crAdmin.util.Util;
import com.crAdmin.util.annotation.LogAnnotation;
import com.crAdmin.util.annotation.OptionType;

/**
 * 主页相关controller
 * 
 * @ClassName: IndexController
 * @Description:
 * @author 桑越
 * @date 2015-12-3 下午8:12:00
 * @version V1.0
 */
@Controller("indexController")
@RequestMapping(value = "/index")
public class IndexController extends BaseController {
	@Resource
	private IndexService service;

	/**
	 * 登录方法
	 * 
	 * @param user
	 * @param session
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	@LogAnnotation(option = "登录系统", optionType = OptionType.Login)
	public String checkLogin(User user, HttpSession session)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		JSONObject json = new JSONObject();

		if (StringUtils.isBlank(user.getLoginName())
				|| StringUtils.isBlank(user.getPassword())) {
			json.put("code", "401");
			json.put("msg", "请输入正确的用户名密码！");
			return json.toString();
		}
		String password = md5Ecode(user.getPassword());
		user.setPassword(password);
		User userResult = service.checkUser(user);
		if (userResult == null) {
			json.put("code", "402");
			json.put("msg", "用户名不存在或密码错误！");
			return json.toString();
		} else if ("1".equals(userResult.getIsDelete())) {
			json.put("code", "402");
			json.put("msg", "用户已注销！");
			return json.toString();
		}
		session.setAttribute("user", userResult);
		json.put("code", "200");
		json.put("msg", "登录成功！");
		json.put("userId", userResult.getUserId());
		return json.toString();
	}

	/**
	 * 进入主页
	 * 
	 * @Title: toIndex
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-3 下午8:12:13
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws @version V1.0
	 */
	@RequestMapping("/toIndex.do")
	public String toIndex() {
		return "index/index.ftl";
	}

	/**
	 * 进入主页
	 * 
	 * @Title: toIndex
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-3 下午8:12:13
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws @version V1.0
	 */
	@RequestMapping("/toPassword.do")
	public String toPassword(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		return "index/editPassword.ftl";
	}

	@RequestMapping("/editPassword.do")
	@ResponseBody
	@LogAnnotation(option = "修改密码", optionType = OptionType.edit)
	public String editPassword(HttpSession session, User user)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		JSONObject json = new JSONObject();
		// 密码加密
		String password = md5Ecode(user.getPassword());
		user.setPassword(password);
		// 获取当前用户登录id
		user.setLoginName(Util.getLoginName(session));
		// 获取用户信息
		User userResult = service.checkUser(user);
		if (userResult == null) {
			json.put("code", "401");
			json.put("msg", "原密码不正确！");
			return json.toString();
		}
		// 新密码
		String newPassword = "";
		if (StringUtils.isNotBlank(user.getNewPassword())) {
			newPassword = md5Ecode(user.getNewPassword());
		}
		User newUser = new User();
		newUser.setPassword(newPassword);
		newUser.setOtherPassword(user.getNewOtherPassword());
		newUser.setUpdateTime(new Date());
		newUser.setUserId(userResult.getUserId());
		int i = service.updateUser(newUser);
		if (i > 0) {
			//更新session中密码
			User user_session = (User) session.getAttribute("user");
			if(StringUtils.isNotBlank(user.getNewOtherPassword())){
				user_session.setOtherPassword(user.getNewOtherPassword());
			}
			
			json.put("code", "200");
			json.put("msg", "修改成功！");
			return json.toString();
		}
		json.put("code", "401");
		json.put("msg", "修改失败！");
		return json.toString();
	}

	/**
	 * 退出登录，清空session
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout.do")
	@ResponseBody
	@LogAnnotation(option = "退出系统", optionType = OptionType.LoginOut)
	public String logout(HttpSession session) {
		JSONObject json = new JSONObject();
		session.removeAttribute("user");
		session.invalidate();
		json.put("code", "200");
		json.put("msg", "退出成功！");
		return json.toString();
	}

	/**
	 * md5加密方法
	 * 
	 * @param rawString
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	private static String md5Ecode(String rawString)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		return base64en.encode(md5.digest(rawString.getBytes("utf-8")));
	}

	private static BASE64Encoder base64en = new BASE64Encoder();

}
