package main;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

public class MessageHandler {
	
	
	public static void timeout(MessageReceivedEvent event) {
		new TimerBuilder(event, 600000);
	}
	
	public static void pinMessage(IMessage message) {
		message.getChannel().pin(message);
	}
}
