package com.data;

/**
 * Created by deshan on 4/4/2018.
 */
public class Bank {
    private static String testStaticMock;

    static {
        testStaticMock = "inside static";
    }

    private String greeting;

    public Bank(String greeting) {
        this.greeting = greeting;
    }

    public static Bank getInstance() {
        return new Bank("...........Welcome............");
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
