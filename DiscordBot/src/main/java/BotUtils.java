import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;

import static java.lang.System.*;

public class BotUtils {

	// command prefix
	public static String BOT_PREFIX = "~";
	public long adminID = 197157674947837952L;

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
