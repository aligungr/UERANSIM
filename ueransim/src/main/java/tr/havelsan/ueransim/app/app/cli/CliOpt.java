/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.cli;

import picocli.CommandLine;
import tr.havelsan.ueransim.app.common.cli.*;

import java.io.File;

public class CliOpt {

    public static CmdMessage msg;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @CommandLine.Command(/*name = "??",*/
            subcommands = {
                    GnbCreateCommand.class,
                    UeCreateCommand.class,
                    UeListCommand.class,
                    UeStatusCommand.class,
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
                names = {"-k", "--key"},
                description = "Use specified KEY instead of default one."
        )
        private String key;

        @CommandLine.Option(
                names = {"-p", "--op"},
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
            name = "ue-list",
            description = "List all the UEs associated with this UERANSIM agent",
            sortOptions = false
    )
    public static class UeListCommand implements Runnable {
        public void run() {
            msg = new CmdUeList();
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @CommandLine.Command(
            name = "ue-status",
            description = "Dump some information about specified UE's general status",
            sortOptions = false
    )
    public static class UeStatusCommand implements Runnable {
        @CommandLine.Parameters(
                description = "IMSI number of the UE whose status will be displayed."
        )
        private String imsi;

        public void run() {
            msg = new CmdUeStatus(imsi);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @CommandLine.Command(
            name = "gnb-create",
            description = "Create and initialize a new GNB"
    )
    public static class GnbCreateCommand implements Runnable {
        @CommandLine.Option(
                names = {"-c", "--config"},
                description = "Use the specified config file for the new gNB. If no files are provided, default " +
                        "gNB configuration of selected profile is used."
        )
        private File configFile;

        @CommandLine.Option(
                names = {"-i", "--id"},
                description = "Use specified ID instead of default one."
        )
        private int id;

        public void run() {
            var msg = new CmdGnbCreate();
            msg.configFile = configFile != null ? configFile.getAbsolutePath() : null;
            msg.id = id;

            CliOpt.msg = msg;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
