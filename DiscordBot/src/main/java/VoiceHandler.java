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
		// do nothing if user already has voice channel role
		if (event.getUser().hasRole(event.getGuild().getRolesByName(event.getVoiceChannel().getName()).get(0))) {
			return;
		}

		// add role to user
		event.getUser().addRole(event.getGuild().getRolesByName(event.getVoiceChannel().getName()).get(0));
	}

	/*
	 * Removes role from the user that left the corresponding voice channel
	 */
	@EventSubscriber
	public void onUserVoiceChannelLeaveEvent(UserVoiceChannelLeaveEvent event) {

		// do nothing if user already lacks voice channel role
		if (!event.getUser().hasRole(event.getGuild().getRolesByName(event.getVoiceChannel().getName()).get(0))) {
			return;
		}

		// remove role from user
		event.getUser().removeRole(event.getGuild().getRolesByName(event.getVoiceChannel().getName()).get(0));
	}

	/*
	 * Removes old role from user and adds new role to user for each voice channel
	 */
	@EventSubscriber
	public void onUserVoiceChannelMoveEvent(UserVoiceChannelMoveEvent event) {

		// remove the role if user has OLD voice channel role
		if (event.getUser().hasRole(event.getGuild().getRolesByName(event.getOldChannel().getName()).get(0))) {
			event.getUser().removeRole(event.getGuild().getRolesByName(event.getOldChannel().getName()).get(0));
		}

		// add the role if user lacks NEW voice channel role
		if (!event.getUser().hasRole(event.getGuild().getRolesByName(event.getNewChannel().getName()).get(0))) {
			event.getUser().addRole(event.getGuild().getRolesByName(event.getNewChannel().getName()).get(0));
		}

	}

	@EventSubscriber
	public void onVoiceChannelUpdateEvent(VoiceChannelUpdateEvent event) {

		// stop if AFK channel is edited
		if (event.getGuild().getAFKChannel().equals(event.getNewVoiceChannel())) {
			return;
		}

		// change role name to new voice channel name
		event.getGuild().getRolesByName(event.getOldVoiceChannel().getName()).get(0)
				.changeName(event.getNewVoiceChannel().getName());
	}

}
