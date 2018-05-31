package commands;

import java.util.Arrays;

import main.BotUtils;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class SetUsername extends Command {
	
	public SetUsername() {
		restricted = true;
		String[] names = {"setusername", "username", "setname"};
		aliases = Arrays.asList(names);
	}
	
	@Override
	public boolean test(MessageReceivedEvent event) {
		return aliases.contains(args.get(0)) && BotUtils.isAdmin(event) && args.size() > 1;
	}

	@Override
	public void run(MessageReceivedEvent event) {
		event.getClient().changeUsername(args.get(1));
	}

}
