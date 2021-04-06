package com.xiao.wechat;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author 肖杰
 * @version 3.0
 * @ClassName WorkWechatCallbackUtils.java
 * @Description 企业微信回调测试类
 * @createTime 2021年01月12日 11:08:00
 */
@Slf4j
public class WorkWechatCallbackUtils {

    public static String suiteSecret = "";
    private static String token = "";
    private static String key = "";
    public static String appId = "";
    public static String corpId = "";

    public static String providerSecret = "";

    private static WXBizMsgCrypt crypt;

    static {
        try {
            crypt = new WXBizMsgCrypt(token, key, corpId);
        } catch (AesException e) {
            throw new RuntimeException("企业微信初始化解密类失败");
        }
    }

    public static String checkCallbackUrl(HttpServletRequest request) {
        String signature = request.getParameter("msg_signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        log.debug("===>start check {} callback url \n signature: {}, timestamp: {}, nonce: {}, echostr：{}",
                request.getRequestURI(),
                signature, timestamp, nonce, echostr);
        String decrypt = null;
        try {
            decrypt = crypt.VerifyURL(signature,timestamp,nonce,echostr);
        } catch (Exception e) {
            log.error("校验地址失败", e);
        }
        log.debug("decrypt: {}", decrypt);
        return decrypt;
    }

    /**
     * 获取接口回调的解密内容
     * @param request
     * @return
     */
    public static String getDecryptMsg(HttpServletRequest request) throws Exception {
        String signature = request.getParameter("msg_signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String content = HttpUtil.getString(request.getInputStream(), CharsetUtil.CHARSET_UTF_8, false);
        return Optional.ofNullable(content)
                .map(encrypt -> WorkWechatCallbackUtils.decryptMsg(signature, timestamp, nonce, encrypt)).orElse(null);
    }

    /**
     * 获取解密后的包数据
     * @param signature
     * @param timestamp
     * @param nonce
     * @param decryptMsg
     * @return
     */
    public static String decryptMsg(String signature, String timestamp, String nonce, String decryptMsg) {
        return Optional.ofNullable(decryptMsg)
                    .map(decrypt -> XmlUtil.xmlToMap(decrypt))
                    .map(map -> MapUtil.getStr(map, "ToUserName"))
                    .map(userId -> {
                        WXBizMsgCrypt wxBizMsgCrypt = null;
                        try {
                            wxBizMsgCrypt = new WXBizMsgCrypt(token, key, userId);
                        } catch (Exception e) {
                            log.error("校验地址解密内容失败", e);
                        }
                        return wxBizMsgCrypt;
                    }).map(wxBizMsgCrypt -> {
                        String msg = null;
                        try {
                            msg = wxBizMsgCrypt.DecryptMsg(signature, timestamp, nonce, decryptMsg);
                        } catch (Exception e) {
                            log.error("校验地址解密内容失败", e);
                        }
                        return msg;
                    }).orElse(null);
    }


}
