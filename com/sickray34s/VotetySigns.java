/*code by sickray34s (A.K.A) Kevin Connelly
 * 1.7.2 version 
 * beta stage
 * 
*/
package com.sickray34s;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.vexsoftware.votifier.model.VotifierEvent;




public class VotetySigns extends JavaPlugin implements Listener {
    CustomConfig Votetext;
	ArrayList<String> a = new ArrayList<String>();
	 
	  int i = 0;
	  DateFormat dateFormat;
	  SimpleDateFormat sdf = new SimpleDateFormat("h:mma");
	  Calendar cal;
		public void onEnable(){
			 getLogger().info("onEnable has been invoked!");	  
			 getServer().getPluginManager().registerEvents(this, this);
			 
			 cal = Calendar.getInstance();
			 this.Votetext = new CustomConfig(this, "VoteCommand.yml");
			 this.Votetext.getConfig().options().copyDefaults(true);
			 this.Votetext.saveConfig();
		}
		    public void onDisable(){
			getLogger().info("onDisable has been invoked!");
		
		}
		

	    @EventHandler(priority=EventPriority.HIGHEST)
	    public void onVotifierEvent(VotifierEvent event) {
	       i++;
	       a.add(event.getVote().getUsername());
	       
	    }
	    
	    @EventHandler(priority=EventPriority.HIGHEST)
	    public void Signit(SignChangeEvent sign) {
	        Player player = sign.getPlayer();
	        if (sign.getLine(0).equalsIgnoreCase("[votetysign]")){
	        	if(player.hasPermission("VotetySigns.create") || player.isOp()){
	        sign.setLine(0, ChatColor.BLUE + "[VotetySign]");
	        player.sendMessage(ChatColor.BLUE + "VotetySign Made");
	                        sign.setLine(1, "Click to Vote");
	    		            sign.setLine(2, "Click Left Info");
	        	}
	                 
	        }
	    }
	    
	    @EventHandler(priority=EventPriority.HIGHEST)
	    public void onInventoryClick(PlayerInteractEvent event) {
			if(event.getAction().equals(Action.LEFT_CLICK_BLOCK)){
		 event.getClickedBlock();
		
		if(event.getClickedBlock().getType().equals(Material.WALL_SIGN) || event.getClickedBlock().getType().equals(Material.SIGN) || event.getClickedBlock().getType().equals(Material.SIGN_POST))
		{
		     Player p = (Player) event.getPlayer();
		     Sign sign = (Sign) event.getClickedBlock().getState();
		     
		     if(sign.getLine(0).contains(ChatColor.BLUE + "[VotetySign]")){
			 Calendar cal2 = Calendar.getInstance();
			  
		     p.sendMessage(ChatColor.GOLD + "From: " + sdf.format(cal.getTime()));
		     p.sendMessage(ChatColor.GOLD + "To" );
		     p.sendMessage(ChatColor.GOLD + "Now: " +  sdf.format(cal2.getTime()));
		     p.sendMessage(ChatColor.GOLD +"This server currently has : " + ChatColor.RED + i + ChatColor.BLUE +" Votes");
		     if(a.toString().contains(p.getDisplayName().toString())){
		    	int times = Collections.frequency(a, p.getDisplayName());
		    	    p.sendMessage(ChatColor.GOLD + "You currently have: " + times + " Votes");
		    	   return;
		    	 }
		    
		     else if(!a.toString().contains(p.getDisplayName().toString())){
		    		   p.sendMessage(ChatColor.BLUE + "You Have not Voted");
		    	   }
		    }
		        }
			}

		    
			else if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
				if(event.getClickedBlock().getType().equals(Material.WALL_SIGN) || event.getClickedBlock().getType().equals(Material.SIGN) || event.getClickedBlock().getType().equals(Material.SIGN_POST))
				{
					Sign sign = (Sign) event.getClickedBlock().getState();
					if(sign.getLine(0).contains(ChatColor.BLUE + "[VotetySign]")){
	              this.getServer().dispatchCommand(event.getPlayer(), this.Votetext.getConfig().getString("VoteCommand").toString());
	      } 
					}
	    }
	    }
}

		 


	       
	    
		    	    
		    	    
		    
		    	
		    	
		    	
		    	
		
		

		

