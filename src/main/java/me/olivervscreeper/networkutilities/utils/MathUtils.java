package me.olivervscreeper.networkutilities.utils;

/**
 * Created on 28/11/2014.
 *
 * @author OliverVsCreeper
 */
public class MathUtils {

  /**
   * Simple converter which switches seconds into ticks. Generally useful for increasing
   * readability of delayed tasks
   *
   * @param seconds seconds to convert into ticks
   * @return Amount of ticks the input is equivalent to
   */
  public static int secondsToTick(int seconds) {
    return seconds * 20;
  }

  /**
   * Simple converter which switches ticks into seconds. Generally useful for increasing the
   * readability of delayed tasks
   *
   * @param ticks ticks to convert into seconds
   * @return Amount of seconds the input is equivalent to
   */
  public static int ticksToSeconds(int ticks) {
    return ticks / 20;
  }

}
