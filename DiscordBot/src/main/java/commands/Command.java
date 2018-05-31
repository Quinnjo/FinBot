package commands;
import java.util.List;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public abstract class Command implements ICommand {
  
  //static fields
  protected List<String> aliases;
  protected boolean restricted;
  protected List<String> args;
  
  
  //abstract methods
  public abstract boolean test(MessageReceivedEvent event);
  public abstract void run(MessageReceivedEvent event);
  
  public ICommand withArgs(List<String> args) {
	  this.args = args;
	  return this;
  }
  
  public void addAlias(String alias) {
    aliases.add(alias);
  }
  
  @Override
  public boolean isRestricted() {
    return restricted;
  }
  
  @Override
  public List<String> getAliases() {
    return aliases;
  }
  
}
