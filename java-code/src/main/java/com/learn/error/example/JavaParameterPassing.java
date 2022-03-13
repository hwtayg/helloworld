package com.learn.error.example;

import java.util.HashMap;
import java.util.Map;

public class JavaParameterPassing {

    public void main(String[] args) {
        JavaParameterPassing test = new JavaParameterPassing();
        test.test1();
    }

    public void test1() {
        Map<String, String> map = new HashMap();
        System.out.println(map);
        setNull(map);
        // 不是null，java按值传递
        System.out.println(map);
    }

    private void setNull(Map<String, String> map) {
        map = null;
    }

}
