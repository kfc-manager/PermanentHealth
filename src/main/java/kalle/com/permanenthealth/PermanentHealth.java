package kalle.com.permanenthealth;

import kalle.com.permanenthealth.config.SettingsConfig;
import kalle.com.permanenthealth.events.PlayerDeath;
import kalle.com.permanenthealth.items.HeartGain;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class PermanentHealth extends JavaPlugin {

    public static double initialHealth;
    public static double healthLoss;

    @Override
    public void onEnable() {
        SettingsConfig settings = new SettingsConfig(this);
        settings.setup();
        healthLoss = SettingsConfig.getHealthLoss();
        initialHealth = SettingsConfig.getInitialHealth();
        registerEvents();
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "PermanentHealth has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "PermanentHealth has been disabled!");
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerDeath(this), this);
        getServer().getPluginManager().registerEvents(new HeartGain(this), this);
    }

}
