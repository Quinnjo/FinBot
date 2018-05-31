package main;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;

import static java.lang.System.*;

public class BotUtils {

	// constants
	public static String BOT_PREFIX = "~";
	private static long ADMIN_ID = 197157674947837952L;
	
	public static boolean isAdmin(MessageReceivedEvent event) {
		return event.getAuthor().hasRole(event.getClient().getRoleByID(ADMIN_ID));
	}

	// creates a new client
	static IDiscordClient getDiscordClient(String token) {
		return new ClientBuilder().withToken(token).build();
	}

	public static void sendMessage(IChannel channel, String message) {

		RequestBuffer.request(() -> {
			try {
				channel.sendMessage(message);
			} catch (DiscordException e) {
				err.println("The message could not be sent.");
				e.printStackTrace();
			}
		});

	}
}
