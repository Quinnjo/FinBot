package commands;

import java.util.ArrayList;

import main.BotUtils;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class Ping extends Command {
	
	public Ping() {
		restricted = false;
		aliases = new ArrayList<>(0);
		aliases.add("ping");
	}

	@Override
	public boolean test(MessageReceivedEvent event) {
		return aliases.contains(args.get(0));
	}

	@Override
	public void run(MessageReceivedEvent event) {
		BotUtils.sendMessage(event.getChannel(), "pong!");
	}

}
