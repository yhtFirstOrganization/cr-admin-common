//返回值工具类
package com.crAdmin.util;

import java.util.HashMap;

public class ResultUtils extends HashMap<String, Object> {
    private static final long serialVersionUID = 4730071944281221061L;

    public static final String CODE = "code";
    public static final String MESSAGE = "msg";
    public static final int STATUS_SUCESS = 200;
    public static final int STATUS_FAIL = 500;
    public static final String STATE_SUCESS = "success";
    public static final String STATE_ERROR = "error";
    public static final String STATE_INFO = "info";
    public static final String STATE_WARNING = "warning";

    public ResultUtils add(String key, Object obj) {
        put(key, obj);
        return this;
    }

}
