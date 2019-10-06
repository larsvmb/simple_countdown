package org.countdown;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Timer;
import java.util.TimerTask;

public final class main extends JavaPlugin implements CommandExecutor {

    int count;
    int last_indi;
    Timer timer;
    public boolean timer_state;

    @Override
    public void onEnable() {

        System.out.println("Started!");

    }

    @Override
    public void onDisable() {

        System.out.println("Stopped!");

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){

            if(command.getName().equals("start")) {

                if(args.length > 0 && !timer_state) {

                    if(args[0].contains("m")) {

                        for (Player player : Bukkit.getOnlinePlayers()) {

                            player.sendMessage(ChatColor.YELLOW + args[0].replace("m", "") + " minute countdown started!");
                            player.sendMessage(ChatColor.RED + Integer.toString(Integer.parseInt(args[0].replace("m", ""))*60));

                        }

                        counter(Integer.parseInt(args[0].replace("m", ""))*60);

                    }else if(args[0].contains("s")) {

                        for (Player player : Bukkit.getOnlinePlayers()) {

                            player.sendMessage(ChatColor.YELLOW + args[0].replace("s", "") + " second countdown started!");
                            player.sendMessage(ChatColor.RED + args[0].replace("s", ""));

                        }

                        counter(Integer.parseInt(args[0].replace("s", "")));

                    }else{

                        for (Player player : Bukkit.getOnlinePlayers()) {

                            player.sendMessage(ChatColor.YELLOW + args[0] + " second countdown started!");
                            player.sendMessage(ChatColor.RED + args[0]);

                        }

                        counter(Integer.parseInt(args[0]));

                    }

                }

            }

        }

        if(command.getName().equals("stop")) {

            timer.cancel();

            for (Player player : Bukkit.getOnlinePlayers()) {

                player.sendMessage(ChatColor.RED + "Stopped!");

            }

            timer_state = false;

        }

        if(command.getName().equals("again")) {

            timer.cancel();
            for (Player player : Bukkit.getOnlinePlayers()) {

                player.sendMessage(ChatColor.YELLOW + "Restarted");
                player.sendMessage(ChatColor.RED + Integer.toString(last_indi));

            }
            counter(last_indi);

        }

        return true;

    }

    public void counter(int ind) {

        count = ind;
        last_indi = ind;

        timer = new Timer();

        TimerTask task = new TimerTask() {

            @Override
            public void run() {

                timer_state = true;


                if(count > 11 && count <= 51) {

                    --count;
                    if(count % 10 == 0) {

                        for (Player player : Bukkit.getOnlinePlayers()) {

                            player.sendMessage(ChatColor.AQUA + Integer.toString(count));

                        }

                    }

                }
                else if(count <= 11 && count > 1){

                    --count;

                    for (Player player : Bukkit.getOnlinePlayers()) {

                        player.sendMessage(ChatColor.LIGHT_PURPLE + Integer.toString(count));

                    }

                    if(count <= 3) {

                        for (Player player : Bukkit.getOnlinePlayers()) {

                            player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 7, 1);

                        }

                    }

                }
                else if(count > 51 && count < 150) {

                    --count;
                    if(count % 20 == 0) {

                        for (Player player : Bukkit.getOnlinePlayers()) {

                            player.sendMessage(ChatColor.AQUA + Integer.toString(count));

                        }

                    }

                }
                else if(count >= 150) {

                    --count;
                    if(count % 50 == 0) {

                        for (Player player : Bukkit.getOnlinePlayers()) {

                            player.sendMessage(ChatColor.AQUA + Integer.toString(count));

                        }

                    }

                }
                else{

                    for (Player player : Bukkit.getOnlinePlayers()) {

                        player.sendMessage(ChatColor.GREEN+"Finished!");

                    }
                    timer.cancel();
                    timer_state = false;
                    playFire();

                }

            }

        };

        timer.scheduleAtFixedRate(task, 1000, 1000);

    }

    void playFire() {

        for (Player player : Bukkit.getOnlinePlayers()) {

            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 10, 1);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 10, 1);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 10, 1);

        }

    }

}

