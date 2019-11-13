package com.chainup.postguide.web;

import com.alibaba.fastjson.JSONObject;
import com.chainup.postguide.util.MD5Util;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.io.UnsupportedEncodingException;
import java.util.Map;
@Controller
public class CommenController {
public  final static   String PREFIXURL = "https://dev5open.chaindown.com/fe-platform-api";

    @RequestMapping("dddd.html")
    public  String dddd(){
        return  "html/dddd";
    }


    @RequestMapping("commenPost")
    @ResponseBody
    public  String commenPost ( @RequestBody Map<String,Object> map)throws  UnsupportedEncodingException{
        String url = (String)map.get("url");
        String securt = "aa123456";
        String sign = MD5Util.generateSign(map,securt);
        map.put("sign",sign);
        HttpResponse response = HttpRequest.post(PREFIXURL+url).form(map).charset("utf-8")
                .contentType("application/x-www-form-urlencoded").connectionTimeout(2000)
                .timeout(5000).send();
            String result = new String(response.bodyBytes(), "utf-8");
            return result;

    }

}

