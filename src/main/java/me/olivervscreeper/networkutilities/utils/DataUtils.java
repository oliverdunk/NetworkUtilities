package me.olivervscreeper.networkutilities.utils;

import me.olivervscreeper.networkutilities.NetworkUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created on 28/11/2014.
 *
 * @author OliverVsCreeper
 */
public class DataUtils {

  public static <T extends Object> void saveObjectToPath(T objectToSave, String pathToSaveTo) {
    try {
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathToSaveTo));
      oos.writeObject(objectToSave);
      oos.flush();
      oos.close();
    } catch (Exception ex) {
      NetworkUtilities
          .log("Attempted to save file to " + pathToSaveTo + " - sadly, an error occurred.");
    }
  }

  public static <T extends Object> T loadObjectFromPath(String pathToLoadFrom) {
    try {
      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathToLoadFrom));
      T result = (T) ois.readObject();
      ois.close();
      return result;
    } catch (Exception ex) {
      NetworkUtilities
          .log("Attempted to load file from " + pathToLoadFrom + " - sadly, an error occurred.");
      return null;
    }
  }

}
