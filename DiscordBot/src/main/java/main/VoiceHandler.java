package main;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.voice.VoiceChannelUpdateEvent;
import sx.blah.discord.handle.impl.events.guild.voice.user.UserVoiceChannelEvent;
import sx.blah.discord.handle.impl.events.guild.voice.user.UserVoiceChannelLeaveEvent;
import sx.blah.discord.handle.impl.events.guild.voice.user.UserVoiceChannelMoveEvent;

/*
 * This class is meant to add, remove, and change roles that correspond to voice channels.
 */
public class VoiceHandler {

	/*
	 * Adds role to the user that joined the corresponding voice channel
	 */
	@EventSubscriber
	public void onUserVoiceChannelJoinEvent(UserVoiceChannelEvent event) {
		System.out.println(event.getUser().getName() + " (" + event.getUser().getNicknameForGuild(event.getGuild())
				+ ") joined voice channel " + event.getVoiceChannel().getName());

		// do nothing if user already has voice channel role
		if (event.getUser().hasRole(event.getGuild().getRolesByName(event.getVoiceChannel().getName()).get(0))) {
			System.out.println("User already had role");
			return;
		}

		// add role to user
		event.getUser().addRole(event.getGuild().getRolesByName(event.getVoiceChannel().getName()).get(0));
		System.out.println("Added role to user");
	}

	/*
	 * Removes role from the user that left the corresponding voice channel
	 */
	@EventSubscriber
	public void onUserVoiceChannelLeaveEvent(UserVoiceChannelLeaveEvent event) {
		System.out.println(event.getUser().getName() + " (" + event.getUser().getNicknameForGuild(event.getGuild())
				+ ") left voice channel " + event.getVoiceChannel().getName());

		// do nothing if user already lacks voice channel role
		if (!event.getUser().hasRole(event.getGuild().getRolesByName(event.getVoiceChannel().getName()).get(0))) {
			System.out.println("User did not have role");
			return;
		}

		// sleep for half a second
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// remove role from user
		event.getUser().removeRole(event.getGuild().getRolesByName(event.getVoiceChannel().getName()).get(0));
		System.out.println("Removed role from user");
	}

	/*
	 * Removes old role from user and adds new role to user for each voice channel
	 */
	@EventSubscriber
	public void onUserVoiceChannelMoveEvent(UserVoiceChannelMoveEvent event) {
		System.out.println(event.getUser().getName() + " (" + event.getUser().getNicknameForGuild(event.getGuild())
				+ ") moved from voice channel " + event.getOldChannel().getName() + " to "
				+ event.getNewChannel().getName());

		// remove the role if user has OLD voice channel role
		if (event.getUser().hasRole(event.getGuild().getRolesByName(event.getOldChannel().getName()).get(0))) {
			event.getUser().removeRole(event.getGuild().getRolesByName(event.getOldChannel().getName()).get(0));
			System.out.println("Removed old role from user");
		}

		// add the role if user lacks NEW voice channel role
		if (!event.getUser().hasRole(event.getGuild().getRolesByName(event.getNewChannel().getName()).get(0))) {
			event.getUser().addRole(event.getGuild().getRolesByName(event.getNewChannel().getName()).get(0));
			System.out.println("Added new role to user");
		}

	}

	@EventSubscriber
	public void onVoiceChannelUpdateEvent(VoiceChannelUpdateEvent event) {
		System.out.println("Voice channel " + event.getOldVoiceChannel().getName() + "'s name was changed to "
				+ event.getNewVoiceChannel().getName());

		// change role name to new voice channel name
		event.getGuild().getRolesByName(event.getOldVoiceChannel().getName()).get(0)
				.changeName(event.getNewVoiceChannel().getName());
	}

}
