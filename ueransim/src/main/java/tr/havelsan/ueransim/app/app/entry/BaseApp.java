/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.entry;

import picocli.CommandLine;
import tr.havelsan.ueransim.utils.Constants;
import tr.havelsan.ueransim.utils.FileUtils;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.console.Console;
import tr.havelsan.ueransim.utils.jcolor.AnsiPalette;

import java.util.concurrent.atomic.AtomicReference;

class BaseApp {

    private static boolean initialized = false;

    static void main(String[] args, boolean isAgent) {
        if (initialized)
            return;
        initialized = true;

        String path = System.getProperty("user.dir") + "/build/";

        System.load(path + "libcrypto-native.so");
        System.load(path + "libngap-native.so");
        System.load(path + "librrc-native.so");
        System.load(path + "libapp-native.so");

        readLocalVersion(path + "version");
        FileUtils.initialize(path + "version");

        if (isAgent) {
            readParams(args);
        }

        if (isAgent) {
            Console.println(AnsiPalette.PAINT_LOG_SUCCESS, "UERANSIM v%s", Constants.VERSION);

            ////// /*String remoteVersion = Utils.downloadString("https://raw.githubusercontent.com/aligungr/UERANSIM/master/misc/version", 750);
            ////// if (remoteVersion == null || localVersion.trim().equals(remoteVersion.trim())) {
            //////     Console.println(AnsiPalette.PAINT_LOG_SUCCESS, "UERANSIM v%s", localVersion.trim());
            ////// } else {
            //////     Console.println(AnsiPalette.PAINT_LOG_WARNING, "UERANSIM v%s / WARNING: version v%s is available.", localVersion.trim(), remoteVersion.trim());
            //////     Utils.sleep(1500);
            ////// }*/
        }
    }

    private static void readParams(String[] args) {
        var commandRef = new AtomicReference<AgentParams>();
        new CommandLine(new AgentParams(commandRef::set)).execute(args);
        var command = commandRef.get();
        if (command == null) {
            System.exit(1);
            return;
        }

        Constants.NO_ROUTE_CONFIG = command.noRouteConfig;
    }

    private static void readLocalVersion(String path) {
        Constants.VERSION = Utils.readAllText(path).trim();

        var sub = Constants.VERSION.split("\\.");
        Constants.MAJOR = Integer.parseInt(sub[0]);
        Constants.MINOR = Integer.parseInt(sub[1]);
        Constants.PATCH = Integer.parseInt(sub[2]);
    }
}
