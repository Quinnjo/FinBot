package commands.restricted;

import commands.Command;
import commands.ICommand;
import commands.RestrictedCommand;
import main.CommandHandler;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class AddAlias extends RestrictedCommand {

	@Override
	public String getInfo() {
		return super.getInfo() + "<command> <alias>";
	}

	@Override
	public boolean test(MessageReceivedEvent event) {
		return super.test(event) && args.size() > 2;
	}

	@Override
	public void run(MessageReceivedEvent event) {

		// cycle through all commands, check if the command name is right.
		// this will only work with Command objects
		for (ICommand command : CommandHandler.getCommands()) {
			if (command.getAliases().contains(args.get(1))) {
				((Command) command).addAlias(args.get(2));
				return;
			}
		}

	}

}
