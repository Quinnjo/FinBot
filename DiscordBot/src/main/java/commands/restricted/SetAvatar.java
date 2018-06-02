package commands.restricted;

import java.util.Arrays;

import commands.RestrictedCommand;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.util.Image;

public class SetAvatar extends RestrictedCommand {
	
	public SetAvatar() {
		String[] names = {"setavatar", "avatar"};
		aliases = Arrays.asList(names);
	}

	@Override
	public String getInfo() {
		return super.getInfo() + "<image type> <image url>";
	}
	
	@Override
	public boolean test(MessageReceivedEvent event) {
		return super.test(event) && args.size() > 2;
	}

	@Override
	public void run(MessageReceivedEvent event) {
		event.getClient().changeAvatar(Image.forUrl(args.get(2), args.get(1)));
	}

}
