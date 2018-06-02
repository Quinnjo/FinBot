package commands.main;

import commands.Command;
import commands.ICommand;
import main.BotUtils;
import main.CommandHandler;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class Help extends Command {

	@Override
	public boolean test(MessageReceivedEvent event) {
		return super.test(event) && args.size() > 1;
	}

	@Override
	public void run(MessageReceivedEvent event) {
		for (ICommand command : CommandHandler.getCommands()) {
			if (command.getAliases().contains(args.get(1))) {
				BotUtils.sendMessage(event.getChannel(), command.getInfo());
				return;
			}
		}
	}

}
