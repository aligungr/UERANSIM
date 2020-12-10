/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.cli;

import picocli.CommandLine;
import tr.havelsan.ueransim.app.common.cli.CmdGnbCreate;
import tr.havelsan.ueransim.app.common.cli.CmdMessage;
import tr.havelsan.ueransim.app.common.cli.CmdUeCreate;

import java.io.File;

public class CliOpt {

    public static CmdMessage msg;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @CommandLine.Command(/*name = "??",*/
            subcommands = {
                    GnbCreateCommand.class,
                    UeCreateCommand.class,
            }
    )
    public static class RootCommand {
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @CommandLine.Command(
            name = "ue-create",
            description = "Create and initialize a new UE",
            sortOptions = false
    )
    public static class UeCreateCommand implements Runnable {
        @CommandLine.Option(
                names = {"-c", "--config"},
                description = "Use the specified config file for the new UE. If no files are provided, default " +
                        "UE configuration of selected profile is used."
        )
        private File configFile;

        @CommandLine.Option(
                names = {"-i", "--imsi"},
                description = "Use specified SUPI/IMSI number instead of default one."
        )
        private String imsi;

        @CommandLine.Option(
                names = {"-K", "--key"},
                description = "Use specified KEY instead of default one."
        )
        private String key;

        @CommandLine.Option(
                names = {"-P", "--op"},
                description = "Use specified OP instead of default one."
        )
        private String op;

        public void run() {
            if (imsi != null && imsi.startsWith("imsi-"))
                imsi = imsi.substring("imsi-".length());

            var msg = new CmdUeCreate();
            msg.configFile = configFile != null ? configFile.getAbsolutePath() : null;
            msg.imsi = imsi;
            msg.key = key;
            msg.op = op;

            CliOpt.msg = msg;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @CommandLine.Command(
            name = "gnb-create",
            description = "Create and initialize a new GNB"
    )
    public static class GnbCreateCommand implements Runnable {
        public void run() {
            msg = new CmdGnbCreate();
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
