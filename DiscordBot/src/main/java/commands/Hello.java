package commands;

import java.util.Arrays;

import main.BotUtils;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class Hello extends Command {
	
	public Hello() {
		restricted = false;
		String[] names = {"hello", "hi"};
		aliases = Arrays.asList(names);
	}

	@Override
	public void run(MessageReceivedEvent event) {
		BotUtils.sendMessage(event.getChannel(), "Hi " + event.getAuthor().getNicknameForGuild(event.getGuild()) + "!");
	}

	@Override
	public boolean test(MessageReceivedEvent event) {
		return aliases.contains(args.get(0));
	}

	

}
