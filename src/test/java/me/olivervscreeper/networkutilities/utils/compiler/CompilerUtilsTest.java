package me.olivervscreeper.networkutilities.utils.compiler;

import junit.framework.TestCase;
import me.olivervscreeper.networkutilities.utils.PasteUtils;

/**
 * @author OliverVsCreeper
 */
public class CompilerUtilsTest extends TestCase {

    public static boolean hasRun = false;

    public void testGenerateClass() throws Exception {
        CompilerClass test = (CompilerClass) CompilerUtils.generateClass(PasteUtils.getPaste("iwapibipin")).newInstance();
        test.run();
        assertTrue(hasRun);
    }

}