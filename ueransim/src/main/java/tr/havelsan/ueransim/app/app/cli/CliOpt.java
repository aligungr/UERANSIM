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
                names = {"-f", "--force-exact"},
                description = "Do not create a UE if specified configurations are not valid. (For example another " +
                        "UE with same IMSI already exists). Otherwise given configurations are slightly modified " +
                        "to create a unique UE."
        )
        private boolean exact;

        public void run() {
            msg = new CmdUeCreate();
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
