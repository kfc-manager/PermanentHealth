package kalle.com.permanenthealth.events;

import kalle.com.permanenthealth.PermanentHealth;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {

    private PermanentHealth plugin;

    public PlayerDeath(PermanentHealth plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        double maxHealth = player.getMaxHealth();
        if (maxHealth <= 2.0) {
            return;
        }
        player.setMaxHealth(maxHealth-PermanentHealth.healthLoss);
    }

}
