package kalle.com.permanenthealth.events;

import kalle.com.permanenthealth.PermanentHealth;
import kalle.com.permanenthealth.config.PlayerDataConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (PlayerDataConfig.joined(player)) {
            return;
        }
        player.setMaxHealth(PermanentHealth.initialHealth);
    }

}
