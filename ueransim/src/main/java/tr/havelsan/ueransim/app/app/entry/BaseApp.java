/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.entry;

import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.console.Console;
import tr.havelsan.ueransim.utils.jcolor.AnsiPalette;

class BaseApp {

    private static boolean initialized = false;

    static void main(String[] args) {
        if (initialized)
            return;

        initialized = true;

        String path = System.getProperty("user.dir") + "/build/";

        System.load(path + "libcrypto-native.so");
        System.load(path + "libngap-native.so");
        System.load(path + "libapp-native.so");

        String version = Utils.readAllText(path + "version").trim();

        Console.println(AnsiPalette.PAINT_LOG_SUCCESS, "UERANSIM v%s", version);
    }
}
