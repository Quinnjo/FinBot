package commands;

import java.util.Arrays;

import main.BotUtils;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.util.Image;

public class SetAvatar extends Command {
	
	public SetAvatar() {
		restricted = true;
		String[] names = {"setavatar", "avatar"};
		aliases = Arrays.asList(names);
	}

	@Override
	public boolean test(MessageReceivedEvent event) {
		return aliases.contains(args.get(0)) && BotUtils.isAdmin(event) && args.size() > 2;
	}

	@Override
	public void run(MessageReceivedEvent event) {
		//BotUtils.sendMessage(event.getChannel(), "Enter an image url and an image type (png, jpg)");
		event.getClient().changeAvatar(Image.forUrl(args.get(2), args.get(1)));
	}

}
