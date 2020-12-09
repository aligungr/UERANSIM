/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.cli;

import picocli.CommandLine;
import tr.havelsan.ueransim.app.common.cli.CmdGnbCreate;
import tr.havelsan.ueransim.app.common.cli.CmdMessage;
import tr.havelsan.ueransim.app.common.cli.CmdUeCreate;

public class CliOpt {

    public static CmdMessage msg;

    @CommandLine.Command(/*name = "??",*/ subcommands = {UeCommand.class, GnbCommand.class,})
    public static class RootCommand {
    }

    @CommandLine.Command(name = "ue", subcommands = {UeCreateCommand.class, UeDeleteCommand.class,})
    public static class UeCommand {
    }

    @CommandLine.Command(name = "create")
    public static class UeCreateCommand implements Runnable {
        public void run() {
            msg = new CmdUeCreate();
        }
    }

    @CommandLine.Command(name = "delete")
    public static class UeDeleteCommand implements Runnable {
        public void run() {
        }
    }

    @CommandLine.Command(name = "gnb", subcommands = {GnbCreateCommand.class,})
    public static class GnbCommand {
    }

    @CommandLine.Command(name = "create")
    public static class GnbCreateCommand implements Runnable {
        public void run() {
            msg = new CmdGnbCreate();
        }
    }
}
