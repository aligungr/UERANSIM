/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.entry;

import tr.havelsan.ueransim.app.app.cli.client.CommandLineClient;

public class ClientApp {

    public static void main(String[] args) {
        BaseApp.main(args, false);
        new CommandLineClient().start(args);
    }
}
