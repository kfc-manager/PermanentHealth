package kalle.com.permanenthealth.config;

import kalle.com.permanenthealth.PermanentHealth;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PlayerDataConfig {

    private static PermanentHealth plugin;

    private static FileConfiguration config;
    private static File file;

    public PlayerDataConfig(PermanentHealth plugin) {
        this.plugin = plugin;
    }

    public void setup() {
        file = new File(plugin.getDataFolder(), "playerdata.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
                config = YamlConfiguration.loadConfiguration(file);
                plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[PermanentHealth] playerdata.yml file has been created!");
            } catch (IOException e) {
                plugin.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[PermanentHealth] CONFIG ERROR: could not create playerdata.yml file!");
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public static void save() {
        try {
            config.save(file);
            config = YamlConfiguration.loadConfiguration(file);
        } catch (IOException e) {
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[PermanentHealth] CONFIG ERROR: could not save playerdata.yml file!");
        }
    }

    public static boolean joined(Player player) {
        String uuid = player.getUniqueId().toString();
        String entry = config.getString(uuid);
        if (entry == null) {
            config.set(uuid,"t");
            save();
            return false;
        }
       return true;
    }



}
