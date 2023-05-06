package com.qhl.salary_java.response;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.ToString;

import java.util.Map;

/**
 * 2019-05-06
 *
 * @author noxus
 */
@Data
@ToString
public class WebApiResponse<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> WebApiResponse<T> netGetResponse(String data) {
        WebApiResponse<T> response = new WebApiResponse<>();
        Map map = JSON.parseObject(data, Map.class);
        Integer code = (Integer) map.get("Code");
        if (code != 0) {
            response.setMsg((String) map.get("Message"));
            response.setCode(1);
            return response;
        } else {
            response.setCode(0);
            response.setMsg("success");
            Map newMap = (Map) map.get("Data");
            response.setData((T) newMap.get("Table"));
            return response;
        }
    }

    public static <T> WebApiResponse<T> mapGetResponse(String data) {
        WebApiResponse<T> response = new WebApiResponse<>();
        Map map = JSON.parseObject(data, Map.class);
        Integer code = (Integer) map.get("Code");
        if (code != 0) {
            response.setMsg((String) map.get("ErrorMessage"));
            response.setCode(1);
            return response;
        } else {
            response.setCode(0);
            response.setMsg("success");
            response.setData((T) map.get("Data"));
            return response;
        }
    }

    public static <T> WebApiResponse<T> WxPostResponse(String data) {
        WebApiResponse<T> response = new WebApiResponse<>();
        Map map = JSON.parseObject(data, Map.class);
        String returnCode = (String) map.get("return_code");
        String resultCode = (String) map.get("result_code");
        if (!"SUCCESS".equals(returnCode)) {
            response.setMsg((String) map.get("return_msg"));
            response.setCode(1);
            return response;
        } else {
            response.setCode(0);
            response.setMsg("success");
            String s = (String) map.get("Data");
            response.setData((T) s);
            return response;
        }
    }


    public static <T> WebApiResponse<T> netPostResponse(String data) {
        WebApiResponse<T> response = new WebApiResponse<>();
        if ("连接超时".equals(data)){
            response.setMsg("连接超时");
            response.setCode(1);
        }
        Map map = JSON.parseObject(data, Map.class);
        Integer code = (Integer) map.get("Code");
        if (code != 0) {
            response.setMsg((String) map.get("Message"));
            response.setCode(1);
            return response;
        } else {
            response.setCode(0);
            response.setMsg("success");
            String s = (String) map.get("Data");
            response.setData((T) s);
            return response;
        }
    }

    public static <T> WebApiResponse<T> success(T data) {
        WebApiResponse<T> response = new WebApiResponse<>();
        response.setCode(0);
        response.setMsg("success");
        response.setData(data);
        return response;
    }
    public static <T> WebApiResponse<T> success(T data,Integer code) {
        WebApiResponse<T> response = new WebApiResponse<>();
        response.setCode(code);
        response.setMsg("success");
        response.setData(data);
        return response;
    }

    public static WebApiResponse msg(String msg) {
        WebApiResponse response = new WebApiResponse();
        response.setCode(0);
        response.setMsg(msg);
        return response;
    }

    public static <T> WebApiResponse<T> error(String message) {
        WebApiResponse<T> response = new WebApiResponse<>();
        response.setCode(1);
        response.setMsg(message);
        return response;
    }
    public static <T> WebApiResponse<T> error(T data,String message) {
        WebApiResponse<T> response = new WebApiResponse<>();
        response.setCode(1);
        response.setData(data);
        response.setMsg(message);
        return response;
    }

    public static <T> WebApiResponse<T> error(T data) {
        WebApiResponse<T> response = new WebApiResponse<>();
        response.setCode(1);
        response.setData(data);
        return response;
    }
    public static <T> WebApiResponse<T> error(T data,Integer code) {
        WebApiResponse<T> response = new WebApiResponse<>();
        response.setCode(code);
        response.setData(data);
        return response;
    }
    // 自定义错误
    public static <T> WebApiResponse<T> customError(T data,Integer code) {
        WebApiResponse<T> response = new WebApiResponse<>();
        response.setCode(code);
        response.setData(data);
        return response;
    }

    public static <T> WebApiResponse<T> customError(T data,Integer code, String message) {
        WebApiResponse<T> response = new WebApiResponse<>();
        response.setCode(code);
        response.setData(data);
        response.setMsg(message);
        return response;
    }

    // 泊位错误
    public static <T> WebApiResponse<T> spaceError(T data) {
        WebApiResponse<T> response = new WebApiResponse<>();
        response.setCode(101);
        response.setData(data);
        return response;
    }
    // 用户错误
    public static <T> WebApiResponse<T> uidError(T data) {
        WebApiResponse<T> response = new WebApiResponse<>();
        response.setCode(102);
        response.setData(data);
        return response;
    }
    // 时间错误
    public static <T> WebApiResponse<T> tmsError(T data) {
        WebApiResponse<T> response = new WebApiResponse<>();
        response.setCode(103);
        response.setData(data);
        return response;
    }
    // 车牌号错误
    public static <T> WebApiResponse<T> vidError(T data) {
        WebApiResponse<T> response = new WebApiResponse<>();
        response.setCode(104);
        response.setData(data);
        return response;
    }
    public static <T> WebApiResponse<T> vidEnterError(T data) {
        WebApiResponse<T> response = new WebApiResponse<>();
        response.setCode(104);
        response.setData(data);
        return response;
    }
    public static <T> WebApiResponse<T> vidLimitError(T data) {
        WebApiResponse<T> response = new WebApiResponse<>();
        response.setCode(104);
        response.setData(data);
        return response;
    }
    public static <T> WebApiResponse<T> error(String message, T data) {
        WebApiResponse<T> response = new WebApiResponse<>();
        response.setCode(1);
        response.setMsg(message);
        response.setData(data);
        return response;
    }

    public static <T> WebApiResponse<T> error(String message, int code) {
        WebApiResponse<T> response = new WebApiResponse<>();
        response.setCode(code);
        response.setMsg(message);
        return response;
    }
    public static <T> WebApiResponse<T> success(String message, int code) {
        WebApiResponse<T> response = new WebApiResponse<>();
        response.setCode(code);
        response.setMsg(message);
        return response;
    }

    public boolean success() {
        return code != null && code == 0;
    }
}
