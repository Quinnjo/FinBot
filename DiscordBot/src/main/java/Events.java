import sx.blah.discord.api.*;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class Events {
	
	@EventSubscriber
	public void onMessageRecieved(MessageReceivedEvent event) {
		if (event.getMessage().getContent().startsWith(BotUtils.BOT_PREFIX + "test")) {
			BotUtils.sendMessage(event.getChannel(), "test command");
		}
	}
}
