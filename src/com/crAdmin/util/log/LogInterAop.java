package com.crAdmin.util.log;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.crAdmin.bean.Car;
import com.crAdmin.bean.OperLog;
import com.crAdmin.bean.Repair;
import com.crAdmin.bean.RepairItem;
import com.crAdmin.bean.Statistics;
import com.crAdmin.bean.Stock;
import com.crAdmin.bean.StockIncome;
import com.crAdmin.bean.User;
import com.crAdmin.module.log.service.LogService;
import com.crAdmin.util.Util;
import com.crAdmin.util.annotation.LogAnnotation;

@Aspect
@Component
public class LogInterAop {

	@Autowired
	LogService logService;

	@Pointcut("execution(* com.crAdmin.module..*.*(..))")
	public void aApplogic() {
	}

	@Around(value = "aApplogic() && @annotation(annotation) &&args(object,..) ", argNames = "annotation,object")
	public Object interceptorApplogic(ProceedingJoinPoint pj, LogAnnotation annotation, Object object)
			throws Throwable {
		OperLog operLog = new OperLog();
		operLog.setType(annotation.optionType());
		operLog.setOperContent(annotation.option());
		Object[] params = pj.getArgs();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		operLog.setOperUserId(Util.getUserId(session));
		String remark = pj.getClass() + pj.getSignature().getName();
		operLog.setRemark(remark);
		operLog.setCreateTime(new Date());

		JSONArray ja = new JSONArray();
		if (params != null && params.length > 0) {
			for (Object param : params) {
				if (params.length < 2 || param instanceof User || param instanceof Car || param instanceof Repair
						|| param instanceof RepairItem || param instanceof Stock || param instanceof StockIncome
						|| param instanceof Statistics) {
					String objJson = JSON.toJSONString(param);
					ja.add(objJson);
				}
			}
		}
		String str = ja.toJSONString();
		if (str.length() > 1000) {
			str = "数据长度过长，已达--" + str.length() + "!截取后为--" + str.substring(0, 900);
		}
		operLog.setParams(str);
		try {
			object = pj.proceed(params);
			String objJson = JSON.toJSONString(object, SerializerFeature.WriteMapNullValue,
					SerializerFeature.WriteNullStringAsEmpty);
			if (objJson.length() > 500) {
				objJson = "数据长度过长，已达--" + objJson.length() + "!截取后为--" + objJson.substring(0, 450);
			}
			operLog.setReturnVal(objJson);
		} catch (Exception e) {
			operLog.setRemark(e.getMessage());
		}
		if (operLog.getOperUserId() == null) {
			JSONObject paramJson = JSONObject.parseObject(object.toString());
			Integer userId = (Integer) paramJson.get("userId");
			operLog.setOperUserId(userId);
		}
		operLog.setOperTime(new Date());
		logService.insertLog(operLog);
		return object;
	}
}