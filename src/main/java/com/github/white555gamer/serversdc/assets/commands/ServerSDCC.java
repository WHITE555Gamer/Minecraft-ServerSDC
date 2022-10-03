package com.github.white555gamer.serversdc.assets.commands;

import org.bukkit.GameMode;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.github.white555gamer.serversdc.assets.check.IsParsableBoolean.IsParsableBooleanBoolean;
import static com.github.white555gamer.serversdc.assets.check.IsParsableBoolean.IsParsableGamemodeBoolean;
import static com.github.white555gamer.serversdc.assets.details.version.ServerSDCVersion;
import static com.github.white555gamer.serversdc.assets.messages.TemplateMsg.IncorrectArg;
import static com.github.white555gamer.serversdc.assets.messages.TemplateMsg.MissingArg;
import static org.bukkit.Bukkit.*;

public class ServerSDCC implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {

            sender.sendMessage(MissingArg);
            return true;

        } else if (args.length == 1) {

            switch (args[0]) {

                case "reloadall":
                    sender.sendMessage("Reload Task will Start.");
                    getServer().reloadWhitelist();
                    getServer().reloadData();
                    getServer().reload();
                    break;

                case "resourcepackdata":
                    sender.sendMessage("Server Resource Pack Data:\n" +
                            "  Resource Pack is Required: " + getServer().isResourcePackRequired() + "\n" +
                            "  Resource Pack: " + getServer().getResourcePack() + "\n" +
                            "  Resource Pack Hash: " + getServer().getResourcePackHash() + "\n" +
                            "  Resource Pack Prompt: " + getServer().getResourcePackPrompt());
                    break;

                case "serverdata":
                    sender.sendMessage("Server Data:\n" +
                            "  Server Name: " + getServer().getName() + "\n" +
                            "  Server Hash Code: " + getServer().hashCode() + "\n" +
                            "  Server is Hardcore: " + getServer().isHardcore() + "\n" +
                            "  Server Default GameMode: " + getServer().getDefaultGameMode() + "\n" +
                            "  Server Has White List: " + getServer().hasWhitelist() + "\n" +
                            "  Server is White List Enforced: " + getServer().isWhitelistEnforced() + "\n" +
                            "  Server Players (Online/Max/WhiteListed): " + getServer().getOnlinePlayers().size() + " / " +
                                    getServer().getMaxPlayers() + " / " + getServer().getWhitelistedPlayers().size() + "\n" +
                            "  Server Shut Down Message: " + getServer().getShutdownMessage() + "\n" +
                            "  Server Motd: " + getServer().getMotd());
                    break;

                case "servernetwork":
                    sender.sendMessage("Server Network:\n" +
                            "  Server IP & Port: " + getServer().getIp() + ":" + getServer().getPort() + "\n" +
                            "  Server Connection Throttle: " + getServer().getConnectionThrottle() + "\n" +
                            "  Server Idle Time Out: " + getServer().getIdleTimeout() + "\n" +
                            "  Server Online Mode: " + getServer().getOnlineMode());
                    break;

                case "serverupdatefolder":
                    sender.sendMessage("Server Update Folder:\n" +
                            "  Server Update Folder: " + getServer().getUpdateFolder() + "\n" +
                            "  Server Update Folder File: " + getServer().getUpdateFolderFile().getName());
                    break;

                case "serverversion":
                    sender.sendMessage("Server Version:\n" +
                            "  Server Minecraft Version: " + getServer().getVersion() + "\n" +
                            "  Server Bukkit Version: " + getServer().getBukkitVersion() + "\n" +
                            "  ServerSDC Version: " + ServerSDCVersion);
                    break;

                case "serverworlddata":
                    String WorldsList = " ";
                    for (int i = 0; i < getServer().getWorlds().size(); i++) {
                        if (i == 0) {
                            WorldsList = getServer().getWorlds().get(i).getName();
                        } else {
                            WorldsList = WorldsList + "," + getServer().getWorlds().get(i).getName();
                        }
                    }
                    sender.sendMessage("Server World Data:\n" +
                            "  Server Allows Flight: " + getServer().getAllowFlight() + "\n" +
                            "  Server Allows Nether: " + getServer().getAllowNether() + "\n" +
                            "  Server Allows End: " + getServer().getAllowEnd() + "\n" +
                            "  Server Spawn Radius: " + getServer().getSpawnRadius() + "\n" +
                            "  Server Max World Size: " + getServer().getMaxWorldSize() + "\n" +
                            "  Server World Type: " + getServer().getWorldType() + "\n" +
                            "  Server Worlds List: " + WorldsList);
                    break;

                default:
                    sender.sendMessage(MissingArg);
                    break;
            }
            return true;

        } else if (args.length == 2) {

            switch (args[0]) {

                case "setdefaultgamemode":
                    if (IsParsableGamemodeBoolean(args[1])) {
                        getServer().setDefaultGameMode(GameMode.valueOf(args[1]));
                        sender.sendMessage("Default GameMode has been set to " + args[1]);
                    } else {
                        sender.sendMessage(IncorrectArg);
                    }
                    break;

                case "setidletimeout":
                    try {
                        Integer.parseInt(args[1]);
                    } catch (NumberFormatException e) {
                        sender.sendMessage(IncorrectArg);
                        return true;
                    }
                    getServer().setIdleTimeout(Integer.parseInt(args[1]));
                    sender.sendMessage("Idle Time Out has been set to " + args[1]);
                    break;

                case "setwhitelistenforced":
                    if (IsParsableBooleanBoolean(args[1])) {
                        getServer().setWhitelistEnforced(Boolean.parseBoolean(args[1]));
                        sender.sendMessage("White List Enforced has been set to " + args[1]);
                    } else {
                        sender.sendMessage(IncorrectArg);
                    }
                    break;

                default:
                    sender.sendMessage(MissingArg);
                    break;
            }
            return true;

        } else {

            sender.sendMessage(MissingArg);
            return true;

        }

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("serversdc")) {

            if (args.length == 1) {
                if (args[0].length() == 0) {
                    return Arrays.asList("setdefaultgamemode","setidletimeout","setwhitelistenforced","reloadall",
                            "resourcepackdata","serverdata","servernetwork","serverupdatefolder","serverversion","serverworlddata");
                } else {

                    if ("se".startsWith(args[0])) {
                        return Arrays.asList("setdefaultgamemode","setidletimeout","setwhitelistenforced",
                                "serverdata","servernetwork","serverupdatefolder","serverversion","serverworlddata");
                    } else if ("set".startsWith(args[0])) {
                        return Arrays.asList("setdefaultgamemode","setidletimeout","setwhitelistenforced");
                    } else if ("setdefaultgamemode".startsWith(args[0])) {
                        return Collections.singletonList("setdefaultgamemode");
                    } else if ("setidletimeout".startsWith(args[0])) {
                        return Collections.singletonList("setidletimeout");
                    } else if ("setwhitelistenforced".startsWith(args[0])) {
                        return Collections.singletonList("setwhitelistenforced");
                    } else if ("re".startsWith(args[0])) {
                        return Arrays.asList("reload","reloadall");
                    } else if ("reloadall".startsWith(args[0])) {
                        return Collections.singletonList("reloadall");
                    } else if ("resourcepackdata".startsWith(args[0])) {
                        return Collections.singletonList("resourcepackdata");
                    } else if ("server".startsWith(args[0])) {
                        return Arrays.asList("serverdata","servernetwork","serverupdatefolder","serverversion","serverworlddata");
                    } else if ("serverdata".startsWith(args[0])) {
                        return Collections.singletonList("serverdata");
                    } else if ("servernetwork".startsWith(args[0])) {
                        return Collections.singletonList("servernetwork");
                    } else if ("serverupdatefolder".startsWith(args[0])) {
                        return Collections.singletonList("serverupdatefolder");
                    } else if ("serverversion".startsWith(args[0])) {
                        return Collections.singletonList("serverversion");
                    } else if ("serverworlddata".startsWith(args[0])) {
                        return Collections.singletonList("serverworlddata");
                    }

                }
            } else if (args.length == 2) {

                if (args[1].length() == 0 && args[0].startsWith("setdefaultgamemode")) {
                    return Arrays.asList("survival","creative","adventure","spectator");
                } else {

                    if (args[0].startsWith("setdefaultgamemode") && "survival".startsWith(args[1])) {
                        return Collections.singletonList("survival");
                    } else if (args[0].startsWith("setdefaultgamemode") && "creative".startsWith(args[1])) {
                        return Collections.singletonList("creative");
                    } else if (args[0].startsWith("setdefaultgamemode") && "adventure".startsWith(args[1])) {
                        return Collections.singletonList("adventure");
                    } else if (args[0].startsWith("setdefaultgamemode") && "spectator".startsWith(args[1])) {
                        return Collections.singletonList("spectator");
                    }

                }

                if (args[1].length() == 0 && args[0].startsWith("setwhitelistenforced")) {
                    return Arrays.asList("true","false");
                } else {

                    if (args[0].startsWith("setwhitelistenforced") && "true".startsWith(args[1])) {
                        return Collections.singletonList("true");
                    } else if (args[0].startsWith("setwhitelistenforced") && "false".startsWith(args[1])) {
                        return Collections.singletonList("false");
                    }

                }

            }


        }
        return null;
    }
}
