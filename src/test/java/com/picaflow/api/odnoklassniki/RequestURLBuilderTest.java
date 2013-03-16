package com.picaflow.api.odnoklassniki;

import org.junit.Test;

/**
 *
 */
public class RequestURLBuilderTest {
    private String accessToken = "6ripa.01-81av2a60048731e69351d3k5dbsc";
    private String secretKey = "3FD794D05C0B9B5918D4235E";
    private String applicationKey = "CBACGPLKABABABABA";

    @Test
    public void test() throws Exception{
        testUserInfo();
        testGetFriends();
        testGetPhotos();
    }

    public void testUserInfo() throws Exception{
        String url = "http://api.odnoklassniki.ru/api/users/getCurrentUser";
        RequestURLBuilder builder = new RequestURLBuilder(accessToken, secretKey, applicationKey);
        String params = builder.build();
        System.out.println(url + "?" + params);
    }

    public void testGetFriends() throws Exception {
        String url = "http://api.odnoklassniki.ru/api/friends/get";
        RequestURLBuilder builder = new RequestURLBuilder(accessToken, secretKey, applicationKey);
        //builder.addParam("uid", "447349106");
        String params = builder.build();
        System.out.println(url + "?" + params);
    }

    public void testGetPhotos() throws Exception {
        String url = "http://api.odnoklassniki.ru/api/photos/getUserPhotos";
        RequestURLBuilder builder = new RequestURLBuilder(accessToken, secretKey, applicationKey);
        //builder.addParam("uid", "447349106");
        String params = builder.build();
        System.out.println(url + "?" + params);
    }
}
