/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.cli.client;

import tr.havelsan.ueransim.app.common.cli.CmdEcho;
import tr.havelsan.ueransim.app.common.cli.CmdMessage;
import tr.havelsan.ueransim.utils.Json;

import java.util.Scanner;

public class CommandLineClient extends CliClient {

    @Override
    protected void main(String[] args) {
        var scanner = new Scanner(System.in);

        while (true) {
            if (scanner.next().equals("echo")) {
                send(new CmdEcho(scanner.nextLine()));
            }
        }
    }

    @Override
    protected void onReceive(CmdMessage cmd) {
        System.out.println(Json.toJson(cmd));
        // TODO
    }
}
