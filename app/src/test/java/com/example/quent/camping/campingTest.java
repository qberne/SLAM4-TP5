package com.example.quent.camping;

import junit.framework.TestCase;

import java.util.Date;

/**
 * Created by quent on 29/11/2016.
 */

public class campingTest extends TestCase{

    public campingTest (String testMethodName) {
        super(testMethodName);
    }

    public void testTosing() throws Exception {
        Date d = new Date();
        Camping camp = new Camping();
        camp.addClient(new Client("fds", "sdfs", d, "4565312"));
        camp.addClient(new Client("fds", "sdfs", d, "4565312"));
        System.out.print(camp.toString());
    }

}
