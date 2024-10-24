package com.xudu.culturaltravelbackend.utils;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;

/**
 * @className: SendSMSCode
 * @description: TODO
 * @author: xudu
 * @create: 2024-06-18
 */
public class SMSUtil {



    public static String sendSMSCode(String phone) {
        //生产环境请求地址：app.cloopen.com
        String serverIp = "app.cloopen.com";
        //请求端口
        String serverPort = "8883";
        //主账号,登陆云通讯网站后,可在控制台首页看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN
        String accountSId = "2c94811c8cd4da0a01902a8a9d41443b";
        String accountToken = "35dd568d967646de82c8fb609881b5ce";
        //请使用管理控制台中已创建应用的APPID
        String appId = "2c94811c8cd4da0a01902a8a9eac4442";

        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(serverIp, serverPort);
        sdk.setAccount(accountSId, accountToken);
        sdk.setAppId(appId);

        sdk.setBodyType(BodyType.Type_JSON);

        //String to = phone;  // 仅可以测试官网中填入的测试号码
        String templateId= "1";
        // 测试开发支持的文案如下:【云通讯】您的验证码是{1}，请于{2}分钟内正确输入。其中{1}和{2}为短信模板参数。
        // 随机一个验证码验证码
        // 生成验证码
        //int code = RandomUtils.nextInt(1000, 9999);

        String code = String.valueOf((int)((Math.random() * 9 + 1) * 1000));

        //datas中第一个数据是验证码，第二个数据是过期时间
        String[] datas = {code, "5"};

        //String subAppend="1234";  //可选 扩展码，四位数字 0~9999
        //String reqId= UUID.randomUUID().toString();  //可选 第三方自定义消息id，最大支持32位英文数字，同账号下同一自然天内不允许重复
        //HashMap<String, Object> result = sdk.sendTemplateSMS(to,templateId,datas);

        sdk.sendTemplateSMS(phone,templateId,datas);

        //将验证码存储在redis,过期时间300s
        //RedisUtil.setRedisContent(phone, code, 180L);
        return code;
    }
}
