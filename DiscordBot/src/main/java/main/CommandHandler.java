package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commands.ICommand;
import commands.main.Hello;
import commands.main.Help;
import commands.main.Ping;
import commands.main.Role;
import commands.restricted.Logout;
import commands.restricted.SetAvatar;
import commands.restricted.SetUsername;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IEmoji;
import sx.blah.discord.util.RequestBuffer;

public class CommandHandler {

	private static ICommand[] arr = { new Hello(), new Logout(), new Ping(), new SetAvatar(), new SetUsername(),
			new Role(), new Help() };
	private static List<ICommand> commandList = Arrays.asList(arr);

	// prints command and author to console
	private static void commandLogger(MessageReceivedEvent event, String command) {
		System.out.println(event.getAuthor().getName() + " (" + event.getAuthor().getNicknameForGuild(event.getGuild())
				+ ") used command \"" + command + "\"");
	}

	private static void reactionAdder(MessageReceivedEvent event) {

		// new map of emoji keywords
		String[] keywords = { "jeb", "malik", "peter" };
		IEmoji[] emojis = { event.getGuild().getEmojiByName("Jeb2"), event.getGuild().getEmojiByName("TrueObama"),
				event.getGuild().getEmojiByName("Peter") };

		Pattern p;
		Matcher m;

		for (int i = 0; i < keywords.length; i++) {
			final int index = i;
			p = Pattern.compile(keywords[index], Pattern.CASE_INSENSITIVE);
			m = p.matcher(event.getMessage().getContent());
			if (m.find()) {
				RequestBuffer.request(() -> {
					event.getMessage().addReaction(emojis[index]);
				});
			}
		}
	}

	@EventSubscriber
	public void onMessageRecieved(MessageReceivedEvent event) {

		reactionAdder(event);

		// splits message into all arguments
		String[] message = event.getMessage().getContent().split(" ");

		// stops if message is blank or does not start with prefix
		if (message.length == 0 || !message[0].startsWith(BotUtils.BOT_PREFIX)) {
			return;
		}

		// create List of args
		List<String> args = new ArrayList<String>(Arrays.asList(message));

		// remove bot prefix
		args.set(0, args.get(0).substring(BotUtils.BOT_PREFIX.length()));

		for (ICommand command : commandList) {
			if (command.withArgs(args).test(event)) {
				commandLogger(event, command.getAliases().get(0));
				command.run(event);
				return;
			}
		}

	}

	public static List<ICommand> getCommands() {
		return commandList;
	}
}
