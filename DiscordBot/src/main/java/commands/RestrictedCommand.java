package commands;

import main.BotUtils;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public abstract class RestrictedCommand extends Command {
	
	//return true or false if user is an admin
	@Override
	public boolean test(MessageReceivedEvent event) {
		return super.test(event) && BotUtils.isAdmin(event);
	}
	
	@Override
	public String getInfo() {
		return "(Admin  Only)\n" + super.getInfo();
	}

}
