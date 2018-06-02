package commands;
import java.util.*;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

//This interface contains the methods that a Command object must implement
public interface ICommand {
  
  //returns true if command should be run
  boolean test(MessageReceivedEvent event);
  
  //returns an List of all the ways a command can be called
  List<String> getAliases();
  
  //runs command
  void run(MessageReceivedEvent event);
  
  //changes args
  ICommand withArgs(List<String> args);
  
  //returns command parameters
  String getInfo();
  
}
