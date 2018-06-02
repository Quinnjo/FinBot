package commands;

import java.util.ArrayList;
import java.util.List;

import main.BotUtils;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public abstract class Command implements ICommand {

	// static fields
	protected List<String> aliases;
	protected List<String> args;
	
	//default constructor
	public Command() {
		aliases = new ArrayList<>(0);
		System.out.println(getClass().getName().toLowerCase().substring(getClass().getName().lastIndexOf('.') + 1));
		aliases.add(getClass().getName().toLowerCase().substring(getClass().getName().lastIndexOf('.') + 1));
	}

	// abstract methods
	public abstract void run(MessageReceivedEvent event);

	public void addAlias(String alias) {
		aliases.add(alias);
	}
	
	@Override
	public boolean test(MessageReceivedEvent event) {
		return aliases.contains(args.get(0));
	}

	@Override
	public String getInfo() {
		return BotUtils.BOT_PREFIX + aliases.get(0) + " ";
	}
	
	@Override
	public ICommand withArgs(List<String> args) {
		this.args = args;
		return this;
	}

	@Override
	public List<String> getAliases() {
		return aliases;
	}

}
