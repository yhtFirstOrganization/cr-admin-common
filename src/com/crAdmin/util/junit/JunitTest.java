/**
 * @Title: Test1.java
 * @Package com.ctrl.test
 * @Description: TODO(用一句话描述该文件做什么)
 * @author A18ccms A18ccms_gmail_com  
 * @date 2015-12-18 上午10:22:31
 * @version V1.0
 */
package com.crAdmin.util.junit;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName: Test1
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2015-12-18 上午10:22:31
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml,classpath*:spring-*.xml")
public class JunitTest {
	Map<String, String> params = new HashMap<String, String>();
	String appKey = "002";
	String secret = "abc";
	String v = "1.0";
	String format = "JSON";
	@Test
	public void selectCompanysBySortType() {
		String method = "pm.ppt.companys.selectCompanysBySortType";
		String longitude = "117.079522";
		String latitude = "36.658993";
		String provinceName = "山东省";
		String cityName = "济南市";
		String areaName = "历下区";
		String companyKindId = "515a6b438080429babd4023d25a20850";
		String currentPage = "1";
		String rowCountPerPage = "10";

		params.put("method", method);
		params.put("appKey", appKey);
		params.put("secret", secret);
		params.put("v", v);
		params.put("format", format);
		params.put("sortType", "0");

		params.put("longitude", longitude);

		params.put("latitude", latitude);
		params.put("provinceName", provinceName);

		params.put("cityName", cityName);

		params.put("areaName", areaName);
		params.put("companyKindId", companyKindId);
		params.put("currentPage", currentPage);
		params.put("rowCountPerPage", rowCountPerPage);
		params.put("type", "1");

//		String sign = AopUtils.sign(params, secret);
//		params.put("sign", sign);
//		
//		System.out.println(AopUtils.sign(params, secret));
//
//		String s = HttpClientUtils.sendPostSSLRequest(
//				"http://localhost:8088/pm/api", params);
//		System.out.println(s);
	}
	
	@Test
	public void selectCompanysBySortType1() {
		String method = "pm.ppt.companys.selectCompanysBySortType123123123123123";
		String longitude = "117.079522";
		String latitude = "36.658993";
		String provinceName = "山东省";
		String cityName = "济南市";
		String areaName = "历下区";
		String companyKindId = "515a6b438080429babd4023d25a20850";
		String currentPage = "1";
		String rowCountPerPage = "10";

		params.put("method", method);
		params.put("appKey", appKey);
		params.put("secret", secret);
		params.put("v", v);
		params.put("format", format);
		params.put("sortType", "0");

		params.put("longitude", longitude);

		params.put("latitude", latitude);
		params.put("provinceName", provinceName);

		params.put("cityName", cityName);

		params.put("areaName", areaName);
		params.put("companyKindId", companyKindId);
		params.put("currentPage", currentPage);
		params.put("rowCountPerPage", rowCountPerPage);
		params.put("type", "1");

//		String sign = AopUtils.sign(params, secret);
//		params.put("sign", sign);
//		
//		System.out.println(AopUtils.sign(params, secret));
//
//		String s = HttpClientUtils.sendPostSSLRequest(
//				"http://localhost:8088/pm/api", params);
//		System.out.println(s);
	}
}
