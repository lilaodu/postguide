package com.chainup.postguide.web;

import com.alibaba.fastjson.JSONArray;
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

    @RequestMapping("post.html")
    public  String dddd(){
        return  "html/post";
    }


    @RequestMapping("commenPost")
    @ResponseBody
    public  String commenPost ( @RequestBody Map<String,Object> map)throws  UnsupportedEncodingException{
        String urll = (String)map.get("url");
        String url = urll.trim();
        String securt = "aa123456";
        String sign = MD5Util.generateSign(map,securt);
        map.put("sign",sign);

        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(map);
        System.out.println(jsonObject.toString());
        HttpResponse response = HttpRequest.post(PREFIXURL+url).body(jsonObject.toString()).charset("utf-8")
               //.contentType("application/x-www-form-urlencoded").connectionTimeout(2000)
                .contentType("application/json").connectionTimeout(2000)
                .timeout(5000).send();
            String result = new String(response.bodyBytes(), "utf-8");
            return result;

    }

}

