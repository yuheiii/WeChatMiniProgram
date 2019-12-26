package com.yuxi.projectdemo.wechat.utils;

import com.yuxi.projectdemo.wechat.frontendObject.ResultFrontendObject;

public class ResultFrontendObjectUtil {

    public static ResultFrontendObject success(Object object) {
        ResultFrontendObject resultFrontendObject = new ResultFrontendObject();
        resultFrontendObject.setData(object);
        resultFrontendObject.setCode(0);
        resultFrontendObject.setMsg("success");
        return resultFrontendObject;
    }

    public static ResultFrontendObject success() {
        return success(null);
    }

    public static ResultFrontendObject error(Integer code, String msg) {
        ResultFrontendObject resultFrontendObject = new ResultFrontendObject();
        resultFrontendObject.setCode(code);
        resultFrontendObject.setMsg(msg);
        return resultFrontendObject;
    }
}
