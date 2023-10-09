package me.chickenwizzard.veganarmy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityEvent;

import java.text.DecimalFormat;
import java.util.List;
public final class VeganArmy extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Hello World. The Plugin started.");

        VeganArmy veganArmy = this;
        getServer().getPluginManager().registerEvents(veganArmy, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        event.setJoinMessage("Welcome to the server " + event.getPlayer().getName() + " you big dummy!");
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {

        if (event.getEntity().getType() != EntityType.PLAYER && event.getEntity().getType().isAlive()) {
            if (event.getEntity().getKiller() != null && event.getEntity().getKiller() instanceof Player && event.getEntity() instanceof Animals) {
                // The killer is a player, so kill them
                event.getEntity().getKiller().setHealth(0.0);
            }
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity().getType() != EntityType.PLAYER){
            String entityName =  event.getEntity().getName();
            double damage = event.getDamage();
            DecimalFormat format = new DecimalFormat("##.00");
            String message = ("The " + entityName + " was damaged " + format.format(damage) + " damage. Don't kill it or the Vegan-Army will kill you.");
            Bukkit.broadcastMessage(message);
        }
    }

}