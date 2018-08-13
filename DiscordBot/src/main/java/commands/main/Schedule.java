package commands.main;

import commands.Command;
import main.BotUtils;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

public class Schedule extends Command {

	@Override
	public String getInfo() {
		return super.getInfo() + "<name>\nOR\n" + super.getInfo() + "list (sends a pm with list of names)";
	}
	@Override
	public boolean test(MessageReceivedEvent event) {
		return super.test(event) && args.size() > 0;
	}
	
	private String getNames(MessageReceivedEvent event) {
		String result = "";
		for (IMessage message : event.getClient().getChannelByID(478598878241882123L).getPinnedMessages()) {
			result += message.getContent() + "\n";
		}
		return result;
	}
	
	@Override
	public void run(MessageReceivedEvent event) {
	
		//pm user list of all names
		if (args.get(1).equalsIgnoreCase("list")) {
			System.out.println("test");
			BotUtils.sendMessage(event.getClient().getOrCreatePMChannel(event.getAuthor()), "List of all names:\n" + getNames(event));
			return;
		}
		
		for (IMessage message : event.getClient().getChannelByID(478598878241882123L).getPinnedMessages()) {
			if (args.get(1).equalsIgnoreCase(message.getContent())) {
				BotUtils.sendMessage(event.getChannel(), message.getAttachments().get(0).getUrl());
				return;
			}
		}
	}

}
