package me.olivervscreeper.networkutilities.utils;

import junit.framework.TestCase;

public class PasteUtilsTest extends TestCase {

    public void testPaste(){
        System.out.println("* PasteUtilsTest: testPaste()");
        String result = PasteUtils.paste("Unit Testing is all the rave!");
        assertNotNull(result);
    }

    public void testGetPasteURL(){
        System.out.println("* PasteUtilsTest: testGetPasteURL()");
        assertNotNull(PasteUtils.getPasteURL());
    }

}