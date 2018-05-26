import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.Image;

public class CommandHandler {

	@EventSubscriber
	public void onMessageRecieved(MessageReceivedEvent event) {

		// splits message into all arguments
		String[] message = event.getMessage().getContent().split(" ");

		// stops if message is blank or does not start with prefix
		if (message.length == 0 || !message[0].startsWith(BotUtils.BOT_PREFIX)) {
			return;
		}

		String command = message[0].substring(1);

		// create List of the remaining args
		List<String> args = new ArrayList<String>(Arrays.asList(message));
		args.remove(0);

		// local variables to make code easier to read
		IDiscordClient client = event.getClient();
		IChannel channel = event.getChannel();
		IUser user = event.getAuthor();

		// each command is a case; some commands have aliases.
		switch (command) {
		case "ping":
			BotUtils.sendMessage(channel, "pong!");
			break;
		case "setusername":
		case "username":
			if (!user.hasRole(client.getRoleByID(197157674947837952L))) {
				BotUtils.sendMessage(channel, "You don't have permissions!");
				return;
			}
			if (args.size() > 1) {
				BotUtils.sendMessage(channel, "Invalid Args!");
				return;
			}
			client.changeUsername(args.get(0));
			break;
		case "setavatar":
		case "avatar":
			if (!user.hasRole(client.getRoleByID(197157674947837952L))) {
				BotUtils.sendMessage(channel, "You don't have permissions!");
				return;
			}
			if (args.size() != 2) {
				BotUtils.sendMessage(channel, "Enter an image url and an image type (png, jpg)");
				return;
			}
			client.changeAvatar(Image.forUrl(args.get(1), args.get(0)));
			break;
		case "logout":
		case "shutdown":
			client.logout();
			break;
		case "hello":
			BotUtils.sendMessage(channel, "Hi " + user.getName());
			break;

		}

	}

}
