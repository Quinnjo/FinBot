import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import sx.blah.discord.api.*;

public class Main {

	public static void main(String[] args) {
		String token = "";
		try {
			Scanner reader = new Scanner(new File("token.txt").getAbsoluteFile());
			String[] line = reader.nextLine().split("=");
			token = line[1];
			reader.close();
			System.out.println(token);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		//create a client
		IDiscordClient client = BotUtils.getDiscordClient(token);

		//register event listeners
		client.getDispatcher().registerListener(new CommandHandler());
		client.getDispatcher().registerListener(new VoiceHandler());

		client.login();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
