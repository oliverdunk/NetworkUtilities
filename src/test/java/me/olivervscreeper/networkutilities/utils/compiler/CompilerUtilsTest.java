package me.olivervscreeper.networkutilities.utils.compiler;

import junit.framework.TestCase;
import me.olivervscreeper.networkutilities.utils.PasteUtils;

import java.io.*;

/**
 * @author OliverVsCreeper
 */
public class CompilerUtilsTest extends TestCase {

    public static boolean hasRun = false;

    public void testGenerateClass() throws Exception {
        System.out.println("* CompilerUtilsTest: testGenerateClass()");
        CompilerClass test = (CompilerClass) CompilerUtils.generateClass(loadFile()).newInstance();
        test.run();
        assertTrue(hasRun);
    }

    public String loadFile(){
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        File file = new File("src/test/java/me/olivervscreeper/networkutilities/utils/compiler/CompilerUtilsTestRunnable.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String code = "";
            while(reader.ready()){
                code = code + reader.readLine();
            }
            return code;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}