package me.olivervscreeper.networkutilities;

import junit.framework.TestCase;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NULoggerTest extends TestCase {

    public void testGetTimePrefix(){
        System.out.println("* NULoggerTest: testGetTimePrefix()");

        NULogger logger = new NULogger(false);

        String time = logger.getTimePrefix();
        SimpleDateFormat sdf = new SimpleDateFormat("[HH:mm:ss]");
        Date date = sdf.parse(time, new ParsePosition(0));

        assertEquals(date.getSeconds(), new Date().getSeconds());
        assertEquals(date.getMinutes(), new Date().getMinutes());
        assertEquals(date.getHours(), new Date().getHours());

        logger.log("Test", "Unit Testing, here we go...");
        assertTrue(logger.log.contains("Unit Testing, here we go..."));
    }

}