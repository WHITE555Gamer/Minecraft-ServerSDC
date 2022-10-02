package com.github.white555gamer.serversdc;

import com.github.white555gamer.serversdc.assets.commands.ServerSDCC;
import org.bukkit.plugin.java.JavaPlugin;

public final class ServerSDC extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("ServerSDC Enabled...");
        getServer().broadcastMessage("ServerSDC Enabled...");

        getCommand("serversdc").setExecutor(new ServerSDCC());

    }

    @Override
    public void onDisable() {
        getLogger().info("ServerSDC Disabled...");
        getServer().broadcastMessage("ServerSDC Disabled...");
    }

    @Override
    public void onLoad() {
        getLogger().info("ServerSDC Loaded...");
        getServer().broadcastMessage("ServerSDC Loaded...");


    }
}
