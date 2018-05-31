public abstract class Command implements ICommand {
  
  //static fields
  protected static List<String> aliases;
  private static boolean restricted;
  
  protected List<String> args;
  
  
  //abstract methods
  public abstract boolean test(MessageReceivedEvent event);
  public abstract void run();
  
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
