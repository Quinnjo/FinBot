import sx.blah.discord.api.*;
import sx.blah.discord.util.DiscordException;

public class Main {
	private static final String token = "NDQ5NjkxMjYyNTM5Mzk5MTY4.Des4Bw.m0F8LeLGxD1x_BDqQay6PD5z-o0";
	
	public static void main(String[] args) {
		
		IDiscordClient client = BotUtils.getDiscordClient(token);
		
		client.getDispatcher().registerListener(new Events());
		
		client.login();

	}
	

}
