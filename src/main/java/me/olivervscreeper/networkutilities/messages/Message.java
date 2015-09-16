package me.olivervscreeper.networkutilities.messages;

import me.olivervscreeper.networkutilities.game.players.GamePlayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author OliverVsCreeper
 */
public class Message {

  private String PREFIX;
  private List<UUID> recipients = new ArrayList<UUID>();

  private int titleIn = 2;
  private int titleOut = 15;
  private int titleStay = 60;

  public static final String BLANK = "%m";
  public static final String NETWORK = "&8[&bNetworkUtilities&8] &7%m";
  public static final String INFO = "&8[&2Info&8] &7%m";
  public static final String WARNING = "&8[&cWarning&8] &c%m";

  /**
   * Default constructor for the Message class
   *
   * @param PREFIX The prefix to apply to the message upon sending
   */
  public Message(String PREFIX) {
    this.PREFIX = PREFIX;
  }

  /**
   * Adds a recipient to the recipient list
   *
   * @param player Recipient to add
   * @return Its own instance of Message, to allow easy method chaining in creation
   */
  public Message addRecipient(Player player) {
    recipients.add(player.getUniqueId());
    return this;
  }

  /**
   * Adds a recipient to the recipient list
   *
   * @param player Recipient to add
   * @return Its own instance of Message, to allow easy method chaining in creation
   */
  public Message addRecipient(GamePlayer player) {
    recipients.add(player.getPlayer().getUniqueId());
    return this;
  }

  /**
   * Adds a recipient to the recipient list
   *
   * @param player Recipient to add
   * @return Its own instance of Message, to allow easy method chaining in creation
   */
  public Message addRecipient(String player) {
    recipients.add(Bukkit.getPlayerExact(player).getUniqueId());
    return this;
  }

  /**
   * Adds a recipient to the recipient list
   *
   * @param players Recipient to add
   * @return Its own instance of Message, to allow easy method chaining in creation
   */
  public Message addRecipient(List<Player> players) {
    for (Player player : players) {
      recipients.add(player.getUniqueId());
    }
    return this;
  }

  /**
   * Sets the timing packet information to be sent along with Titles and Subtitles upon sending of
   * messages
   *
   * @param in Fade in time of the title, in ticks
   * @param stay Time in which the title stays on the screen, in ticks
   * @param out Fade out time of the title, in ticks
   * @return
   */
  public Message setTimings(int in, int stay, int out) {
    titleIn = in;
    titleStay = stay;
    titleOut = out;
    return this;
  }

  /**
   * Sends a message using the Message instance and a default MessageDisplay, which is currently the
   * chat
   *
   * @param message Message to be sent
   */
  public void send(String message) {
    message = ChatColor.translateAlternateColorCodes('&', PREFIX.replace("%m", message));
    for (UUID uniqueUserIdentifier : recipients) {
      Player player = Bukkit.getPlayer(uniqueUserIdentifier);
      player.sendMessage(message.replace("%p", player.getName()));
    }
    recipients.clear();
  }

  /**
   * Sends a message using the Message instance
   *
   * @param message Message to be sent
   * @param display Where the message should be shown
   */
  public void send(String message, MessageDisplay display) {
    message = ChatColor.translateAlternateColorCodes('&', PREFIX.replace("%m", message));
    for (UUID uniqueUserIdentifier : recipients) {
      Player player = Bukkit.getPlayer(uniqueUserIdentifier);
      String newMessage = message.replace("%p", player.getName());
      switch (display) {
        case CHAT:
          player.sendMessage(newMessage);
          break;
        case ACTIONBAR:
          MessageUtils.sendActionbar(player, message, titleIn, titleStay, titleOut);
          break;
        case TITLE:
          MessageUtils.sendTitle(player, message, titleIn, titleStay, titleOut);
          break;
        case SUBTITLE:
          MessageUtils.sendSubtitle(player, message, titleIn, titleStay, titleOut);
          break;
      }
    }
    recipients.clear();
  }

  /**
   * Sends a message using the Message instance
   *
   * @param message Message to be sent
   * @param display Where the message should be shown
   * @param sound Sound to be played to recipients
   */
  public void send(String message, MessageDisplay display, Sound sound) {
    message = ChatColor.translateAlternateColorCodes('&', PREFIX.replace("%m", message));
    for (UUID uniqueUserIdentifier : recipients) {
      Player player = Bukkit.getPlayer(uniqueUserIdentifier);
      String newMessage = message.replace("%p", player.getName());
      switch (display) {
        case CHAT:
          player.sendMessage(newMessage);
          break;
        case ACTIONBAR:
          MessageUtils.sendActionbar(player, message, titleIn, titleStay, titleOut);
          break;
        case TITLE:
          MessageUtils.sendTitle(player, message, titleIn, titleStay, titleOut);
          break;
        case SUBTITLE:
          MessageUtils.sendSubtitle(player, message, titleIn, titleStay, titleOut);
          break;
      }
      player.playSound(player.getLocation(), sound, 10, 1);
    }
    recipients.clear();
  }

}
