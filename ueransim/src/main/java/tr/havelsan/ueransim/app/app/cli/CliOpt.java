/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.cli;

import picocli.CommandLine;
import tr.havelsan.ueransim.app.common.cli.CmdMessage;
import tr.havelsan.ueransim.app.common.cli.CmdUeCreate;

public class CliOpt {

    public static CmdMessage msg;

    @CommandLine.Command(
            //name = "??",
            subcommands = {
                    UeCommand.class,
            })
    public static class RootCommand implements Runnable {
        @Override
        public void run() {
            System.err.println("slm__");
        }
    }

    @CommandLine.Command(
            name = "ue",
            subcommands = {
                    UeCreateCommand.class,
                    UeDeleteCommand.class,
            }
    )
    public static class UeCommand implements Runnable {

        @Override
        public void run() {
            System.err.println("slm");
        }
    }

    @CommandLine.Command(
            name = "create"
    )
    public static class UeCreateCommand implements Runnable {

        @Override
        public void run() {
            var cmd = new CmdUeCreate();
            msg = cmd;
        }
    }

    @CommandLine.Command(
            name = "delete"
    )
    public static class UeDeleteCommand implements Runnable {

        @Override
        public void run() {
            System.err.println("slm3");
        }
    }
}
