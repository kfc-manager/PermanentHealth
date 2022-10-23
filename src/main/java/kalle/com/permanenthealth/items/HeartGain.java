package kalle.com.permanenthealth.items;

import kalle.com.permanenthealth.PermanentHealth;
import kalle.com.permanenthealth.config.SettingsConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class HeartGain implements Listener {

    private static PermanentHealth plugin;

    private static ItemStack item = new ItemStack(Material.APPLE, 1);

    public HeartGain(PermanentHealth plugin) {
        this.plugin = plugin;
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Heart Gain");
        meta.addEnchant(Enchantment.DURABILITY, 0, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "By consuming this you will receive");
        lore.add(ChatColor.GRAY + "an additional permanent heart!");
        meta.setLore(lore);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(plugin,"heart_gain");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("123", "456", "789");
        Material[] ingredients = SettingsConfig.getRecipe();
        recipe.setIngredient('1',ingredients[0]);
        recipe.setIngredient('2',ingredients[1]);
        recipe.setIngredient('3',ingredients[2]);
        recipe.setIngredient('4',ingredients[3]);
        recipe.setIngredient('5',ingredients[4]);
        recipe.setIngredient('6',ingredients[5]);
        recipe.setIngredient('7',ingredients[6]);
        recipe.setIngredient('8',ingredients[7]);
        recipe.setIngredient('9',ingredients[8]);
        Bukkit.addRecipe(recipe);
    }

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent event) {
        if (event.getItem() == null) {
            return;
        }
        if (event.getItem().isSimilar(item)){
            Player player = event.getPlayer();
            player.setMaxHealth(player.getMaxHealth() + 2.0);
        }
    }

}
