import sx.blah.discord.api.*;

import static java.lang.System.*;

public class Main {
	private static final String token = "NDQ5NjkxMjYyNTM5Mzk5MTY4.Des4Bw.m0F8LeLGxD1x_BDqQay6PD5z-o0";

	public static void main(String[] args) {

		IDiscordClient client = BotUtils.getDiscordClient(token);

		client.getDispatcher().registerListener(new CommandHandler());

		client.login();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}

	}
}
