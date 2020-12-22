/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.cli;

import picocli.CommandLine;
import tr.havelsan.ueransim.app.app.entry.ClientApp;
import tr.havelsan.ueransim.app.common.cli.*;

import java.io.File;

public class CliOpt {

    public static CmdMessage msg;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @CommandLine.Command(name = "nr-cli",
            subcommands = {
                    GnbCreateCommand.class,
                    UeCreateCommand.class,
                    GnbListCommand.class,
                    UeListCommand.class,
                    GnbStatusCommand.class,
                    UeStatusCommand.class,
                    SessionCreateCommand.class,
                    UePingCommand.class,
                    UeDeRegistrationCommand.class,
            },
            mixinStandardHelpOptions = true,
            versionProvider = ClientApp.VersionProvider.class
    )
    public static class RootCommand {
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @CommandLine.Command(
            name = "ue-create",
            description = "Create and initialize a new UE",
            sortOptions = false,
            mixinStandardHelpOptions = true,
            versionProvider = ClientApp.VersionProvider.class
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
            sortOptions = false,
            mixinStandardHelpOptions = true,
            versionProvider = ClientApp.VersionProvider.class
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
            sortOptions = false,
            mixinStandardHelpOptions = true,
            versionProvider = ClientApp.VersionProvider.class
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
            description = "Create and initialize a new GNB",
            sortOptions = false,
            mixinStandardHelpOptions = true,
            versionProvider = ClientApp.VersionProvider.class
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

    @CommandLine.Command(
            name = "gnb-list",
            description = "List all the gNBs associated with this UERANSIM agent",
            sortOptions = false,
            mixinStandardHelpOptions = true,
            versionProvider = ClientApp.VersionProvider.class
    )
    public static class GnbListCommand implements Runnable {
        public void run() {
            msg = new CmdGnbList();
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @CommandLine.Command(
            name = "gnb-status",
            description = "Dump some information about specified gNB's general status",
            sortOptions = false,
            mixinStandardHelpOptions = true,
            versionProvider = ClientApp.VersionProvider.class
    )
    public static class GnbStatusCommand implements Runnable {
        @CommandLine.Parameters(
                description = "ID of the gNB whose status will be displayed."
        )
        private int id;

        public void run() {
            msg = new CmdGnbStatus(id);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @CommandLine.Command(
            name = "session-create",
            description = "Trigger a PDU session establishment for a specified UE",
            sortOptions = false,
            mixinStandardHelpOptions = true,
            versionProvider = ClientApp.VersionProvider.class
    )
    public static class SessionCreateCommand implements Runnable {
        @CommandLine.Parameters(
                description = "IMSI of the UE that will trigger PDU session establishment."
        )
        private String ueImsi;

        public void run() {
            msg = new CmdSessionCreate(ueImsi);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @CommandLine.Command(
            name = "ue-ping",
            description = "Trigger a ping request for the specified UE",
            sortOptions = false,
            mixinStandardHelpOptions = true,
            versionProvider = ClientApp.VersionProvider.class
    )
    public static class UePingCommand implements Runnable {
        @CommandLine.Parameters(
                description = "IMSI number of the UE that will trigger ping request.",
                index = "0"
        )
        private String ueImsi;

        @CommandLine.Parameters(
                description = "Destination address of the ping request. (NOTE: address resolution is performed locally.)",
                index = "1"
        )
        private String address;

        @CommandLine.Option(
                names = {"-c", "--count"},
                description = "Send specified number of consequential ping requests. Typically an integer in range [1, 10] is used.",
                defaultValue = "1",
                showDefaultValue = CommandLine.Help.Visibility.ALWAYS
        )
        private int count;

        @CommandLine.Option(
                names = {"-t", "--timeout"},
                description = "Use specified timeout value instead of default one (in seconds).",
                defaultValue = "3",
                showDefaultValue = CommandLine.Help.Visibility.ALWAYS
        )
        private int timeoutSec;

        public void run() {
            var msg = new CmdUePing();
            msg.ueImsi = ueImsi;
            msg.address = address;
            msg.count = count;
            msg.timeoutSec = timeoutSec;

            CliOpt.msg = msg;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @CommandLine.Command(
            name = "ue-deregister",
            description = "Trigger a de-registration for the specified UE",
            sortOptions = false,
            mixinStandardHelpOptions = true,
            versionProvider = ClientApp.VersionProvider.class
    )
    public static class UeDeRegistrationCommand implements Runnable {
        @CommandLine.Parameters(
                description = "IMSI number of the UE that will trigger de-registration.",
                index = "0"
        )
        private String ueImsi;

        @CommandLine.Option(
                names = {"-s"},
                description = "Use switch-off indication in de-registration request."
        )
        private boolean isSwitchOff;

        public void run() {
            var msg = new CmdUeDeRegistration();
            msg.ueImsi = ueImsi;
            msg.isSwitchOff = isSwitchOff;

            CliOpt.msg = msg;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
