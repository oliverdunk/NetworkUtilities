package me.olivervscreeper.networkutilities.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 28/11/2014. Simple utility for managing lists.
 *
 * @author OliverVsCreeper
 */
@Deprecated
public class ListUtils {

  /**
   * Splits a string using a specified character sequence and converts the result from an Array to a
   * List.
   *
   * @param toSplit the string to split
   * @param splitBy the character sequence to split by
   * @return List the converted list of arguments
   *
   * @Deprecated Planned to be removed in the next release
   */
  @Deprecated
  public static List<String> splitString(String toSplit, String splitBy) {
    List<String> stringList = new ArrayList<String>();
    for (String string : toSplit.split(splitBy)) {
      stringList.add(string);
    }
    return stringList;
  }

}
