package com.rd.bsk.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 页面响应的数据(自定义返回json数据)
 * */
public class ResultUtil  extends HashMap<String, Object> {

    /**重写了HashMap中的put方法*/
    @Override
    public ResultUtil put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public ResultUtil() {
    }

    public static ResultUtil success() {
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.put("code", 200);
        resultUtil.put("msg", "操作成功!");
        return resultUtil;
    }

    public static ResultUtil success(String msg) {
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.put("code", 200);
        resultUtil.put("msg", msg);
        return resultUtil;
    }

    public static ResultUtil success(String key,Object value) {
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.put("code", 200);
        resultUtil.put("msg", "操作成功!");
        resultUtil.put(key,value);
        return resultUtil;
    }

    public static ResultUtil success(Map<String, Object> map) {
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.put("code", 200);
        resultUtil.put("msg", "操作成功!");
        resultUtil.putAll(map);
        return resultUtil;
    }




    public static ResultUtil fail() {
        ResultUtil rm = new ResultUtil();
        rm.put("code", 500);
        rm.put("msg", "后台服务器错误!");
        return rm;
    }

    public static ResultUtil fail(String msg) {
        ResultUtil rm = new ResultUtil();
        rm.put("code", 500);
        rm.put("msg", msg);
        return rm;
    }

    public static ResultUtil fail(Integer code,String msg) {
        ResultUtil rm = new ResultUtil();
        rm.put("code", code);
        rm.put("msg", msg);
        return rm;
    }
}

