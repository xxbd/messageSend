package com.huaao.message.controller;


import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@RequestMapping("/web")
public class WebController {





    /**
     * 发送短信
     *
     */
    @PostMapping("/message")
    public  void sendShortMessage(@RequestParam(value = "phoneNumbers")String phoneNumbers, @RequestParam(value = "kh")String kh,
                                  @RequestParam(value = "xm")String xm, @RequestParam(value = "xb")String xb,
                                  @RequestParam(value = "nl")String nl, @RequestParam(value = "sjrq")String sjrq,
                                  @RequestParam(value = "xmmc")String xmmc, @RequestParam(value = "result")String result) {
        DefaultProfile profile = DefaultProfile.getProfile( "cn-hefei", ACCESSKEYID, ACCESSKEYSECRET );
        IAcsClient client = new DefaultAcsClient( profile );
        CommonRequest request = new CommonRequest();
        request.setMethod( MethodType.POST );
        request.setDomain( "dysmsapi.aliyuncs.com" );
        request.setVersion( "2017-05-25" );
        request.setAction( "SendSms" );
        request.putQueryParameter( "RegionId", "cn-hefei" );
        request.putQueryParameter( "PhoneNumbers", phoneNumbers );
        request.putQueryParameter( "SignName", "合肥华澳医学检验实验室" );//短信签名名称。请在控制台签名管理页面签名名称一列查看。
        request.putQueryParameter( "TemplateCode", VALIDATE_CODE );
        request.putQueryParameter( "TemplateParam", "{\"kh\":\"" + kh + "\",\"xm\":\"" + xm + "\",\"xb\":\"" + xb + "\",\"nl\":\"" + nl + "\",\"sjrq\":\"" + sjrq + "\",\"xmmc\":\"" + xmmc + "\",\"result\":\"" + result + "\"}" ); //拼接成官网指定格式
        try {
            CommonResponse response = client.getCommonResponse( request );
            System.out.println( response.getData() ); //回显消息
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
    /** * 跳转到登录页面*/
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
