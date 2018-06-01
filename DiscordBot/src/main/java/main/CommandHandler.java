package main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import commands.*;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class CommandHandler {
	
	private static ICommand[] arr = {
			new Hello(),
			new Logout(),
			new Ping(),
			new SetAvatar(),
			new SetUsername(),
			new Role()
			};
	private static List<ICommand> commandList = Arrays.asList(arr);

	// prints command and author to console
	private static void commandLogger(MessageReceivedEvent event, String command) {
		System.out.println(event.getAuthor().getName() + " (" + event.getAuthor().getNicknameForGuild(event.getGuild())
				+ ") used command \"" + command + "\"");
	}
	
	@EventSubscriber
	public void onMessageRecieved(MessageReceivedEvent event) {

		// splits message into all arguments
		String[] message = event.getMessage().getContent().split(" ");

		// stops if message is blank or does not start with prefix
		if (message.length == 0 || !message[0].startsWith(BotUtils.BOT_PREFIX)) {
			return;
		}

		// create List of args
		List<String> args = new ArrayList<String>(Arrays.asList(message));
		
		//remove bot prefix
		args.set(0, args.get(0).substring(BotUtils.BOT_PREFIX.length()));
		
		for (ICommand command : commandList) {
			if (command.withArgs(args).test(event)) {
				commandLogger(event, command.getAliases().get(0));
				command.run(event);
				return;
			}
		}

	}
	
	public static List<ICommand> getCommands() {
		return commandList;
	}
}
