package me.olivervscreeper.networkutilities.utils;

import junit.framework.TestCase;

/**
 * @author OliverVsCreeper
 */
public class DataUtilsTest extends TestCase {

    public void testSaveObjectToPath(){
        System.out.println("* DataUtilsTest: testSaveObjectToPath()");

        String object = "Unit Testing is something I now find exciting!";
        DataUtils.saveObjectToPath(object, "unitTest");
        String result = DataUtils.loadObjectFromPath("unitTest");

        assertEquals(object, result);
    }
}