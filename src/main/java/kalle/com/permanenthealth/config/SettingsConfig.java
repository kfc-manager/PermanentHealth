package kalle.com.permanenthealth.config;

import kalle.com.permanenthealth.PermanentHealth;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SettingsConfig {

    private static PermanentHealth plugin;

    private static FileConfiguration config;
    private static File file;

    private static Material[] recipe = new Material[9];

    public SettingsConfig(PermanentHealth plugin) {
        this.plugin = plugin;
    }

    public void setup() {
        file = new File(plugin.getDataFolder(), "settings.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
                config = YamlConfiguration.loadConfiguration(file);
                loadDefaultRecipe();
                loadDefaultHealthLoss();
                loadDefaultInitialHealth();
                plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[PermanentHealth] settings.yml file has been created!");
            } catch (IOException e) {
                plugin.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[PermanentHealth] CONFIG ERROR: could not create settings.yml file!");
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public static void save() {
        try {
            config.save(file);
            config = YamlConfiguration.loadConfiguration(file);
        } catch (IOException e) {
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[PermanentHealth] CONFIG ERROR: could not save settings.yml file!");
        }
    }

    private static void loadDefaultRecipe() {
        config.set("Recipe.Ingredient 1", "AIR");
        recipe[0] = Material.AIR;
        config.set("Recipe.Ingredient 2", "AIR");
        recipe[1] = Material.AIR;
        config.set("Recipe.Ingredient 3", "AIR");
        recipe[2] = Material.AIR;
        config.set("Recipe.Ingredient 4", "DIAMOND_BLOCK");
        recipe[3] = Material.DIAMOND;
        config.set("Recipe.Ingredient 5", "TOTEM_OF_UNDYING");
        recipe[4] = Material.AIR;
        config.set("Recipe.Ingredient 6", "DIAMOND_BLOCK");
        recipe[5] = Material.DIAMOND;
        config.set("Recipe.Ingredient 7", "AIR");
        recipe[6] = Material.AIR;
        config.set("Recipe.Ingredient 8", "NETHERITE_INGOT");
        recipe[7] = Material.DIAMOND;
        config.set("Recipe.Ingredient 9", "AIR");
        recipe[8] = Material.AIR;
        save();
    }

    private static void loadDefaultHealthLoss() {
        config.set("Health Loss", 2.0);
        save();
    }

    private static void loadDefaultInitialHealth() {
        config.set("Initial Health", 24.0);
        save();
    }


    public static Material[] getRecipe() {
        try {
            for (int i = 0; i < 9; i++) {
                String entry = config.getString("Recipe.Ingredient " + (i+1));
                Material material = Material.valueOf(entry);
                recipe[i] = material;
            }
        } catch (NullPointerException | IllegalArgumentException e) {
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[PermanentHealth] CONFIG ERROR: could not load recipe of settings.yml properly! (recipe restored to default)");
        }
        return recipe;
    }

    public static double getHealthLoss() {
        try {
            String entry = config.getString("Health Loss");
            double health = Double.parseDouble(entry);
            if (health < 0.0) {
                throw new IllegalArgumentException();
            }
            return health;
        } catch (NullPointerException | IllegalArgumentException e) {
            loadDefaultHealthLoss();
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[PermanentHealth] CONFIG ERROR: could not load health loss of settings.yml properly! (health loss restored to default)");
        }
        return 2.0;
    }

    public static double getInitialHealth() {
        try {
            String entry = config.getString("Initial Health");
            double health = Double.parseDouble(entry);
            if (health < 1.0) {
                throw new IllegalArgumentException();
            }
            return health;
        } catch (NullPointerException | IllegalArgumentException e) {
            loadDefaultInitialHealth();
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[PermanentHealth] CONFIG ERROR: could not load initial health of settings.yml properly! (initial health restored to default)");
        }
        return 24.0;
    }

}
