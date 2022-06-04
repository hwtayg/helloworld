package com.learn.log;

public class Test2 {

    private static String a = get("a");

    private static String get(String input) {
        System.out.println("get run");
        return input + ".";
    }

    static {
        System.out.println("static block");
    }

    {
        System.out.println("normal block");
    }
    public Test2() {
        System.out.println("constructor run");
    }
}
