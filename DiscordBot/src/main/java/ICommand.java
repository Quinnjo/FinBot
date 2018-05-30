//This interface contains the methods that a Command object must implement
public interface ICommand {
  
  //returns true if command should be run
  boolean test(MessageReceivedEvent event);
  
  //returns true if the command needs admin permissions, false if anyone can use it
  boolean needsPermissions();
  
  //returns an List of all the ways a command can be called
  List<String> getAliases();
  
  //runs command
  void run(MessageReceivedEvent event);
  

}
