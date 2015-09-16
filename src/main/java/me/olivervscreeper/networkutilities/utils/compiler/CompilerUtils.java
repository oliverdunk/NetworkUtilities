package me.olivervscreeper.networkutilities.utils.compiler;


import me.olivervscreeper.networkutilities.NetworkUtilities;
import me.olivervscreeper.networkutilities.utils.DataUtils;
import me.olivervscreeper.networkutilities.utils.PasteUtils;
import org.bukkit.Bukkit;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by OliverVsCreeper on 20/04/2015.
 * @author Inspired by the work of Jonas Balsfulland, but with many modifications and changes.
 *
 * This is essentially a very dangerous feature. In order to use it, your server must be running in a JDK, rather
 * than the JRE, and you will need full NetworkUtilities permissions.
 */
public class CompilerUtils {

    private static ClassLoader cl;

    public static synchronized boolean runString(String ID) {
        NetworkUtilities.logger.log("CompilerUtils", "Attempting to run Haste ID: " + ID);
        new File("plugins/NetworkUtilities/compiler/").mkdirs();

        String paste = PasteUtils.getPaste(ID);
        while(paste.trim().isEmpty()) paste = PasteUtils.getPaste(ID);
        Class classFile = generateClass(paste);
        if (classFile == null) return false; //Failure
        try {
            CompilerClass compilerClass = (CompilerClass) classFile.newInstance();
            compilerClass.run();
            NetworkUtilities.logger.log("CompilerUtils", "Successfully ran Haste ID: " + ID);
        } catch (Exception e) {
            NetworkUtilities.logger.log("CompilerUtils", "Error running Haste ID: " + ID);
            return false; //Failure
        }
        return true; //Success
    }

    public static Class generateClass(String paste) {
        try {
            String fileName = getClassName(paste);
            File classFile = new File("plugins/NetworkUtilities/compiler/" + fileName + ".class");
            File javaFile = new File("plugins/NetworkUtilities/compiler/" + fileName + ".java");
            classFile.mkdirs();
            classFile.delete();
            javaFile.delete();

            DataUtils.saveStringToPath(paste, "plugins/NetworkUtilities/compiler/" + fileName + ".java");

            if (ToolProvider.getSystemJavaCompiler() == null) return null;

            //Generate class file
            generateClass(new File("plugins/NetworkUtilities/compiler/" + fileName + ".java"));

            //Process class file
            URL url = new File("plugins/NetworkUtilities/compiler/").toURI().toURL();
            cl = new URLClassLoader(new URL[]{url}, NetworkUtilities.class.getClassLoader());
            return cl.loadClass(fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void generateClass(File file) throws URISyntaxException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        List<String> optionList = new ArrayList();
        String classes = buildClassPath(new String[]{getJar(Bukkit.class).getAbsolutePath(), getPluginDirectory().getAbsolutePath() + "/*"});
        optionList.addAll(Arrays.asList(new String[] { "-classpath", classes }));
        File[] files1 = new File[1];
        files1[0] = file;
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> compilationUnits1 = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(files1));
        compiler.getTask(null, fileManager, null, optionList, null, compilationUnits1).call();
        file.delete();
    }

    private static File getPluginDirectory() throws URISyntaxException {
        File file = getJar(CompilerUtils.class);
        return new File(file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - file.getName().length()));
    }

    public static String getClassName(String string) {
        List<String> words = Arrays.asList(string.split(" "));
        Iterator wordIterator = words.iterator();
        while (wordIterator.hasNext())
            if (wordIterator.next().equals("class")) return ((String) wordIterator.next()).replace("{", "");
        return null;
    }

    private static String buildClassPath(String... paths) {
        StringBuilder sb = new StringBuilder();
        for (String path : paths) {
            if (path.endsWith("*")) {
                path = path.substring(0, path.length() - 1);
                File pathFile = new File(path);
                for (File file : pathFile.listFiles()) {
                    if (!(file.isFile()) && (file.getName().endsWith(".jar"))) continue;
                    sb.append(path);
                    sb.append(file.getName());
                    sb.append(System.getProperty("path.separator"));
                }
            } else {
                sb.append(path);
                sb.append(System.getProperty("path.separator"));
            }
        }
        return sb.toString();
    }

    public static File getJar(Class aclass) throws URISyntaxException {
        return new File(aclass.getProtectionDomain().getCodeSource().getLocation().toURI());
    }

}