package com.example.template.test;


import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TestBasic {

    @Test
    public void test() {
        String url = "E:/pictures/fffgdsf/a2719ff1-4cc0-486c-95cf-eab5529bf14f.jpg";
        int i = url.indexOf("/");
        String substring = url.substring(i);
        System.out.println(substring);
    }

    @Test
    public void tset2() throws UnsupportedEncodingException {

    }

}
