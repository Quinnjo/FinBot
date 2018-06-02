package commands.restricted;

import java.util.Arrays;

import commands.RestrictedCommand;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class Logout extends RestrictedCommand {
	
	public Logout() {
		String[] names = {"logout", "logoff", "shutdown", "kill"};
		aliases = Arrays.asList(names);
	}
	
	@Override
	public void run(MessageReceivedEvent event) {
		event.getClient().logout();
	}

}
