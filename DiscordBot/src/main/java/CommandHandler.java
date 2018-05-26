import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sx.blah.discord.api.*;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.util.Image;

public class CommandHandler {
	
	@EventSubscriber
	public void onMessageRecieved(MessageReceivedEvent event) {
		
		//splits message into all arguments
		String[] message = event.getMessage().getContent().split(" ");
		
		//stops if message is blank or does not start with prefix
		if (message.length == 0 || !message[0].startsWith(BotUtils.BOT_PREFIX)) {
			return;
		}
		
		
		String command = message[0].substring(1);
		
		//create List of the remaining args
		List<String> args = new ArrayList<String>(Arrays.asList(message));
		args.remove(0);
		
		//interprets command
		switch (command) {
		case "ping":
			BotUtils.sendMessage(event.getChannel(), "pong!");
			break;
		case "setusername":
		case "username":
			if (args.size() > 1) {
				BotUtils.sendMessage(event.getChannel(), "Invalid Args!");
				return;
			}
			event.getClient().changeUsername(args.get(0));
			break;
		case "setavatar":
		case "avatar":
			if (args.size() != 2) {
				BotUtils.sendMessage(event.getChannel(), "Enter an image url and an image type (png, jpg)");
				return;
			}
			event.getClient().changeAvatar(Image.forUrl(args.get(1), args.get(0)));
			break;
		case "logout":
		case "shutdown":
			event.getClient().logout();
			break;
		}
		
		
		/*
		if (event.getMessage().getContent().startsWith(BotUtils.BOT_PREFIX)) {
			CommandHandler.interpret(event, event.getMessage().getContent().substring(BotUtils.BOT_PREFIX.length()));
		}
		*/
	}
}
