package commands;

import java.util.Arrays;

import main.BotUtils;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class Logout extends Command {
	
	public Logout() {
		restricted = true;
		String[] names = {"logout", "logoff", "shutdown", "kill"};
		aliases = Arrays.asList(names);
	}

	@Override
	public boolean test(MessageReceivedEvent event) {
		return aliases.contains(args.get(0)) && BotUtils.isAdmin(event);
	}

	@Override
	public void run(MessageReceivedEvent event) {
		event.getClient().logout();
	}

}
