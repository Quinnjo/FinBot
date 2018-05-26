import java.io.File;
import sx.blah.discord.api.*;
import sx.blah.discord.util.Image;

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
		
		//delete these comments after implementing a username and avatar command
		//String url = client.getUserByID(150639897714098176L).getAvatarURL();
		//client.changeAvatar(Image.forUrl("png", url));
		client.changeUsername("Shep");
		out.println("success");
		// new File("C:/Users/Squinnjo/Downloads/frame_00_delay-0.03s.png")));
	}

}
