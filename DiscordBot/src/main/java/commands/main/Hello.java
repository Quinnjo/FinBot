package commands.main;

import java.util.Arrays;

import commands.Command;
import main.BotUtils;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class Hello extends Command {
	
	public Hello() {
		String[] names = {"hello", "hi"};
		aliases = Arrays.asList(names);
	}

	@Override
	public void run(MessageReceivedEvent event) {
		BotUtils.sendMessage(event.getChannel(), "Hi " + event.getAuthor().getNicknameForGuild(event.getGuild()) + "!");
	}	

}
