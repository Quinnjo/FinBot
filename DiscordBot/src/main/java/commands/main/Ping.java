package commands.main;

import commands.Command;
import main.BotUtils;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class Ping extends Command {

	@Override
	public void run(MessageReceivedEvent event) {
		BotUtils.sendMessage(event.getChannel(), "pong!");
	}

}
