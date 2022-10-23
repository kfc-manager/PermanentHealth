# PermanentHealth

A plugin requested by u/Extra-Ad3247 on r/MinecraftPlugins. This plugin sets the maximum health of players initially to 12 hearts. Each time a player dies he looses one heart permanently. Players can craft the item "Heart Gain", which restores one heart permanently.

## Configuration

NOTE: Make sure to reload the server after making changes to the config!

When the plugin gets loaded for the first time on the server, the file **PermanentHealth/settings.yml** gets generated. In this file you can configure the recipe of the added item, how much hearts a player looses per death and how much health a player initially has. You can find the names of the ingredients with this link: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html . Make sure that you do not make any typos. If the item is not recognized by the plugin the recipe will be restored to default. You can set how much health a player looses when he dies by the line "Health Loss" and how much health a player initially has by the line "Initial Health". Make sure that you only use **positive doubles** as numbers, otherwise the values will be restored to default.

## Item

The item "Heart Gain" will be added to the game. It is an apple and can be consumed, which means that it only can be used when the player is hungry. After a player consumed the item he will gain one permanent heart. By default the item can be crafted like in this image:

