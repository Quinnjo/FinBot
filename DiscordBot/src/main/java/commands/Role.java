package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.BotUtils;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IRole;

public class Role extends Command {

	public Role() {
		restricted = false;
		aliases = new ArrayList<>(0);
		aliases.add("role");
	}

	@Override
	public boolean test(MessageReceivedEvent event) {
		return aliases.contains(args.get(0)) && args.size() > 1;
	}

	@Override
	public void run(MessageReceivedEvent event) {

		// acceptable roles to self-add
		String[] r = { "PUBG", "Fortnite", "CS:GO", "Voltz", "RL", "FTB", "Tekkit" };
		List<String> roles = Arrays.asList(r);

		// gets role that user input
		IRole role = event.getGuild().getRolesByName(args.get(1)).get(0);

		// denies if role is not an acceptable role
		if (!roles.contains(role.getName())) {
			BotUtils.sendMessage(event.getChannel(), "You cannot add this role to yourself");
			return;
		}

		// adds/removes role based on whether user has role already
		if (event.getAuthor().hasRole(role)) {
			event.getAuthor().removeRole(role);
			BotUtils.sendMessage(event.getChannel(), "Removed role " + role.getName() + " from "
					+ event.getAuthor().getNicknameForGuild(event.getGuild()));
		} else {
			event.getAuthor().addRole(role);
			BotUtils.sendMessage(event.getChannel(),
					"Added role " + role.getName() + " to " + event.getAuthor().getNicknameForGuild(event.getGuild()));
		}

	}

}
