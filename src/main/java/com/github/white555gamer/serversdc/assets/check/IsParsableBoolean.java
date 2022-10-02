package com.github.white555gamer.serversdc.assets.check;

import org.bukkit.GameMode;

public class IsParsableBoolean {

    public static boolean IsParsableBooleanBoolean(String string) {

        switch (string) {
            case "true":
            case "false":
                return true;
            default:
                return false;

        }
    }

    public static boolean IsParsableGamemodeBoolean(String string) {

        switch (string) {
            case "survival":
            case "creative":
            case "adventure":
            case "spectator":
                return true;
            default:
                return false;

        }
    }
}
