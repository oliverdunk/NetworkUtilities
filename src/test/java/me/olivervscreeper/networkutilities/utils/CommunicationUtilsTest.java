package me.olivervscreeper.networkutilities.utils;

import junit.framework.TestCase;

public class CommunicationUtilsTest extends TestCase {

    public void testGetNotice(){
        System.out.println("* CommunicationUtilsTest: testGetNotice()");
        assertNotNull(CommunicationUtils.getNotice());
    }
}