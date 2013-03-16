package com.picaflow.api.odnoklassniki;

import java.security.MessageDigest;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * */
public class RequestURLBuilder {
    private String accessToken;
    private String secretKey;
    private String applicationKey;

    private TreeMap<String, String> requestParams = new TreeMap<String, String>();
    private MessageDigest msgDigest;

    public RequestURLBuilder(String accessToken, String secretKey, String applicationKey) throws Exception {
        this.accessToken = accessToken;
        this.secretKey = secretKey;
        this.applicationKey = applicationKey;

        msgDigest = MessageDigest.getInstance("MD5");
    }

    public void clear() {
        msgDigest.reset();
        requestParams.clear();
    }

    public void addParam(String name, String value) {
        requestParams.put(name, value);
    }

    public String build() {
        StringBuilder result = new StringBuilder();
        result.append("access_token=" + accessToken);
        StringBuilder requestParamsComposed = new StringBuilder();
        addParam("application_key",applicationKey);
        String digestSecret = md5(accessToken + secretKey);
        for (Map.Entry<String, String> entry : requestParams.entrySet()) {
            String entryStr = entry.getKey() + "=" + entry.getValue();
            requestParamsComposed.append(entryStr);
            result.append("&");
            result.append(entryStr);
        }
        msgDigest.reset();
        String digest = md5(requestParamsComposed.toString()+digestSecret);
        result.append("&");
        result.append("sig=" + digest);
        return result.toString();
    }

    public String md5(String md5) {
        msgDigest.reset();
        byte[] array = msgDigest.digest(md5.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
        }
        return sb.toString();
    }
}
