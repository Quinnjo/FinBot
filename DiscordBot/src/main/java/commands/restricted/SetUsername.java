package commands.restricted;

import java.util.Arrays;

import commands.RestrictedCommand;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class SetUsername extends RestrictedCommand {
	
	public SetUsername() {
		String[] names = {"setusername", "username", "setname"};
		aliases = Arrays.asList(names);
	}
	
	@Override
	public String getInfo() {
		return super.getInfo() + "<new username>";
	}
	
	@Override
	public boolean test(MessageReceivedEvent event) {
		return super.test(event)  && args.size() > 1;
	}

	@Override
	public void run(MessageReceivedEvent event) {
		event.getClient().changeUsername(args.get(1));
	}

}
