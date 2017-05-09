package com.yiyexy.sender;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;

/**
 * <p>Created on 2017/5/8.</p>
 *
 * @author stormma
 * @description: 短信验证码发送器
 */
@Component
public class MessageSender {

    public static String batchSend(String url, String un, String pw, String phone, String msg,
                                   String rd, String ex) throws Exception {
        HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
        GetMethod method = new GetMethod();
        try {
            URI base = new URI(url, false);
            method.setURI(new URI(base, "send", false));
            method.setQueryString(new NameValuePair[]{
                    new NameValuePair("un", un),
                    new NameValuePair("pw", pw),
                    new NameValuePair("phone", phone),
                    new NameValuePair("rd", rd),
                    new NameValuePair("msg", msg),
                    new NameValuePair("ex", ex),
            });
            int result = client.executeMethod(method);
            if (result == HttpStatus.SC_OK) {
                InputStream in = method.getResponseBodyAsStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = in.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                return URLDecoder.decode(baos.toString(), "UTF-8");
            } else {
                throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
            }
        } finally {
            method.releaseConnection();
        }
    }
}
