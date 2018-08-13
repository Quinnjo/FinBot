package main;

import java.util.Timer;
import java.util.TimerTask;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class TimerBuilder {

	private Timer t;
	MessageReceivedEvent event;
	
	public TimerBuilder(MessageReceivedEvent event, int seconds) {
		this.event = event;
		t = new Timer();
		t.schedule(new TimeoutTask(), seconds * 1000);
	}
	
	class TimeoutTask extends TimerTask {

		@Override
		public void run() {
			
			//remove Muted role
			event.getAuthor().removeRole(event.getClient().getRoleByID(474807212405948417L));
			
			t.cancel();
		}
		
	}
}
