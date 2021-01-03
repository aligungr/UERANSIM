/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.app.cli;

import tr.havelsan.ueransim.app.app.AppConfig;
import tr.havelsan.ueransim.app.app.UeRanSim;
import tr.havelsan.ueransim.app.common.Supi;
import tr.havelsan.ueransim.app.common.cli.*;
import tr.havelsan.ueransim.app.common.configs.GnbConfig;
import tr.havelsan.ueransim.app.common.configs.UeConfig;
import tr.havelsan.ueransim.app.common.info.GnbStatusInfo;
import tr.havelsan.ueransim.app.common.info.UeStatusInfo;
import tr.havelsan.ueransim.app.common.nts.*;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.gnb.app.GnbAppTask;
import tr.havelsan.ueransim.app.ue.app.UeAppTask;
import tr.havelsan.ueransim.app.utils.MtsInitializer;
import tr.havelsan.ueransim.mts.ImplicitTypedObject;
import tr.havelsan.ueransim.mts.MtsContext;
import tr.havelsan.ueransim.nts.NtsId;
import tr.havelsan.ueransim.nts.nts.NtsTask;
import tr.havelsan.ueransim.utils.Json;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Consumer;

public class CliTask extends NtsTask {

    private final AppConfig appConfig;
    private final UeRanSim ueransim;
    private final ServerTask serverTask;

    public CliTask(AppConfig appConfig, UeRanSim ueransim) {
        this.appConfig = appConfig;
        this.ueransim = ueransim;
        this.serverTask = new ServerTask(this);
    }

    @Override
    protected void main() {
        serverTask.start();

        while (true) {
            var msg = take();

            if (msg instanceof IwCliClientMessage) {
                var client = ((IwCliClientMessage) msg).client;
                var data = ((IwCliClientMessage) msg).data;

                var cmd = CliUtils.decodeCmdPdu(data, s -> sendCmd(client, new CmdErrorIndication(s)));
                receiveCmd(client, cmd);
            }
        }
    }

    private void sendCmd(UUID client, CmdMessage message) {
        serverTask.push(new IwCliServerMessage(client, CliUtils.encodeCmdPdu(message)));
    }

    private void receiveCmd(UUID client, CmdMessage message) {
        try {
            if (message instanceof CmdEcho) {
                sendCmd(client, message);
            } else if (message instanceof CmdUeCreate) {
                receiveUeCreate(client, (CmdUeCreate) message);
            } else if (message instanceof CmdGnbCreate) {
                receiveGnbCreate(client, (CmdGnbCreate) message);
            } else if (message instanceof CmdUeList) {
                receiveUeList(client, (CmdUeList) message);
            } else if (message instanceof CmdUeStatus) {
                receiveUeStatus(client, (CmdUeStatus) message);
            } else if (message instanceof CmdGnbList) {
                receiveGnbList(client, (CmdGnbList) message);
            } else if (message instanceof CmdGnbStatus) {
                receiveGnbStatus(client, (CmdGnbStatus) message);
            } else if (message instanceof CmdSessionCreate) {
                receiveSessionCreate(client, (CmdSessionCreate) message);
            } else if (message instanceof CmdUePing) {
                receiveUePing(client, (CmdUePing) message);
            } else if (message instanceof CmdUeDeRegistration) {
                receiveUeDeregister(client, (CmdUeDeRegistration) message);
            }
        } catch (Exception e) {
            sendCmd(client, new CmdErrorIndication(e.getMessage()));
        }
    }

    private void receiveGnbCreate(UUID client, CmdGnbCreate cmd) {
        GnbConfig refConfig;

        if (cmd.configFile != null) {
            var mts = new MtsContext();
            MtsInitializer.initDefaultMts(mts);

            refConfig = mts.constructor.construct(GnbConfig.class,
                    ((ImplicitTypedObject) mts.decoder.decode(cmd.configFile)), true);
        } else {
            refConfig = appConfig.createGnbConfig();
        }

        var config = new GnbConfig(
                cmd.id != 0 ? cmd.id : refConfig.gnbId,
                refConfig.tac,
                refConfig.nci.toBinaryString(),
                refConfig.plmn,
                refConfig.amfConfigs,
                refConfig.ignoreStreamIds,
                refConfig.ngapIp,
                refConfig.gtpIp,
                refConfig.nssais
        );

        ueransim.createGnb(config);
        sendCmd(client, new CmdTerminate(0, "gNB created with id: %s.", config.gnbId));
    }

    private void receiveUeCreate(UUID client, CmdUeCreate cmd) {
        UeConfig refConfig;

        if (cmd.configFile != null) {
            var mts = new MtsContext();
            MtsInitializer.initDefaultMts(mts);

            refConfig = mts.constructor.construct(UeConfig.class,
                    ((ImplicitTypedObject) mts.decoder.decode(cmd.configFile)), true);
        } else {
            refConfig = appConfig.createUeConfig();
        }

        var config = new UeConfig(
                cmd.key != null ? new OctetString(cmd.key) : refConfig.key,
                cmd.op != null ? new OctetString(cmd.op) : refConfig.op,
                refConfig.amf,
                refConfig.imei,
                cmd.imsi != null ? new Supi("imsi", cmd.imsi) : refConfig.supi,
                refConfig.plmn,
                refConfig.requestedNssai,
                refConfig.dnn
        );

        ueransim.createUe(config);
        sendCmd(client, new CmdTerminate(0, "UE created: %s.", config.supi));
    }

    private void receiveUeList(UUID client, CmdUeList cmd) {
        var ueNameList = new ArrayList<String>();

        for (var ueId : ueransim.allUes()) {
            var ctx = ueransim.findUe(ueId);
            if (ctx == null)
                continue;
            ueNameList.add(ctx.nodeName);
        }

        var sb = new StringBuilder();
        sb.append("List of UE devices:\n");

        for (var name : ueNameList) {
            sb.append("    ");
            sb.append("- ");
            sb.append(name);
            sb.append("\n");
        }

        if (ueNameList.isEmpty()) {
            sb.append("    - No UEs are available.\n");
        }

        sb.append("\nTotal number of UE: ");
        sb.append(ueNameList.size());

        sendCmd(client, new CmdTerminate(0, sb.toString().trim()));
    }

    private void receiveGnbList(UUID client, CmdGnbList message) {
        var gnbNameList = new ArrayList<String>();

        for (var ueId : ueransim.allGnbs()) {
            var ctx = ueransim.findGnb(ueId);
            if (ctx == null)
                continue;
            gnbNameList.add(ctx.nodeName);
        }

        var sb = new StringBuilder();
        sb.append("List of gNBs:\n");

        for (var name : gnbNameList) {
            sb.append("    ");
            sb.append("- ");
            sb.append(name);
            sb.append("\n");
        }

        if (gnbNameList.isEmpty()) {
            sb.append("    - No gNBs are available.\n");
        }

        sb.append("\nTotal number of gNBs: ");
        sb.append(gnbNameList.size());

        sendCmd(client, new CmdTerminate(0, sb.toString().trim()));
    }

    private boolean tryToFindUe(String imsi, UeSimContext[] outCtx, UeAppTask[] outAppTask) {
        var nodeName = "ue-" + imsi;

        var ctxId = ueransim.findContextIdByNodeName(nodeName);
        if (ctxId == null) {
            return false;
        }

        var ctx = ueransim.findUe(ctxId);
        if (ctx == null) {
            return false;
        }

        var appTask = ctx.nts.findTask(NtsId.UE_TASK_APP, UeAppTask.class);

        outCtx[0] = ctx;
        outAppTask[0] = appTask;
        return true;
    }

    private boolean tryToFindGnb(int gnbId, GnbSimContext[] outCtx, GnbAppTask[] outAppTask) {
        var nodeName = "gnb-" + gnbId;

        var ctxId = ueransim.findContextIdByNodeName(nodeName);
        if (ctxId == null) {
            return false;
        }

        var ctx = ueransim.findGnb(ctxId);
        if (ctx == null) {
            return false;
        }

        var appTask = ctx.nts.findTask(NtsId.GNB_TASK_APP, GnbAppTask.class);
        outCtx[0] = ctx;
        outAppTask[0] = appTask;
        return true;
    }

    private void receiveUeStatus(UUID client, CmdUeStatus message) {
        var ctx = new UeSimContext[1];
        var appTask = new UeAppTask[1];

        if (!tryToFindUe(message.imsi, ctx, appTask)) {
            sendCmd(client, new CmdErrorIndication("No UE found with the IMSI %s", message.imsi));
            return;
        }

        Consumer<UeStatusInfo> consumerFunc = ueStatusInfo
                -> sendCmd(client, new CmdTerminate(0, Utils.convertJsonToYaml(Json.toJson(ueStatusInfo))));

        appTask[0].push(new IwUeStatusInfoRequest(this, consumerFunc));
    }

    private void receiveGnbStatus(UUID client, CmdGnbStatus message) {
        var ctx = new GnbSimContext[1];
        var appTask = new GnbAppTask[1];

        if (!tryToFindGnb(message.id, ctx, appTask)) {
            sendCmd(client, new CmdErrorIndication("No gNB found with the ID %s", message.id));
            return;
        }

        Consumer<GnbStatusInfo> consumerFunc = gnbStatusInfo
                -> sendCmd(client, new CmdTerminate(0, Utils.convertJsonToYaml(Json.toJson(gnbStatusInfo))));

        appTask[0].push(new IwGnbStatusInfoRequest(this, consumerFunc));
    }

    private void receiveSessionCreate(UUID client, CmdSessionCreate message) {
        var ctx = new UeSimContext[1];
        var appTask = new UeAppTask[1];

        if (!tryToFindUe(message.ueImsi, ctx, appTask)) {
            sendCmd(client, new CmdErrorIndication("No UE found with the IMSI %s", message.ueImsi));
            return;
        }

        appTask[0].push(new IwUeExternalCommand(message));
        sendCmd(client, new CmdTerminate(0, "PDU session establishment has been triggered."));
    }

    private void receiveUePing(UUID client, CmdUePing message) {
        var ctx = new UeSimContext[1];
        var appTask = new UeAppTask[1];

        if (!tryToFindUe(message.ueImsi, ctx, appTask)) {
            sendCmd(client, new CmdErrorIndication("No UE found with the IMSI %s", message.ueImsi));
            return;
        }

        appTask[0].push(new IwUeExternalCommand(message));
        sendCmd(client, new CmdTerminate(0, "Ping request has been triggered."));
    }

    private void receiveUeDeregister(UUID client, CmdUeDeRegistration message) {
        var ctx = new UeSimContext[1];
        var appTask = new UeAppTask[1];

        if (!tryToFindUe(message.ueImsi, ctx, appTask)) {
            sendCmd(client, new CmdErrorIndication("No UE found with the IMSI %s", message.ueImsi));
            return;
        }

        appTask[0].push(new IwUeExternalCommand(message));
        sendCmd(client, new CmdTerminate(0, "De-registration has been triggered."));
    }
}
