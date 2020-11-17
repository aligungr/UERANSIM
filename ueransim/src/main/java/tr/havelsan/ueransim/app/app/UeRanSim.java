/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app;

import tr.havelsan.ueransim.app.air.AirNode;
import tr.havelsan.ueransim.app.app.listeners.INodeMessagingListener;
import tr.havelsan.ueransim.app.common.Supi;
import tr.havelsan.ueransim.app.common.configs.LoadTestConfig;
import tr.havelsan.ueransim.app.common.configs.UeConfig;
import tr.havelsan.ueransim.app.common.itms.IwUeTestCommand;
import tr.havelsan.ueransim.app.common.simctx.AirSimContext;
import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.common.testcmd.*;
import tr.havelsan.ueransim.app.gnb.GnbNode;
import tr.havelsan.ueransim.app.gnb.app.GnbAppTask;
import tr.havelsan.ueransim.app.ue.UeNode;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.console.Log;

import java.math.BigInteger;
import java.util.*;

public class UeRanSim {

    private final AppConfig appConfig;
    private final LinkedHashMap<String, List<TestCmd>> testCases;
    private final LoadTestConfig loadTesting;
    private final HashMap<UUID, GnbSimContext> gnbMap;
    private final HashMap<UUID, UeSimContext> ueMap;
    private final AirSimContext airCtx;
    private final List<INodeMessagingListener> messagingListeners;

    UeRanSim(List<INodeMessagingListener> messagingListeners,
             LinkedHashMap<String, List<TestCmd>> testCases,
             LoadTestConfig loadTesting) {

        this.testCases = testCases;
        this.loadTesting = loadTesting;
        this.appConfig = new AppConfig();
        this.gnbMap = new HashMap<>();
        this.ueMap = new HashMap<>();
        this.messagingListeners = new ArrayList<>(messagingListeners);
        this.airCtx = AirNode.createContext(this);
        AirNode.run(airCtx);

        initialize();
    }

    private void initialize() {
        var numberOfUe = loadTesting.numberOfUes;

        var gnbContext = GnbNode.createContext(this, appConfig.createGnbConfig());
        gnbMap.put(gnbContext.ctxId, gnbContext);
        GnbNode.run(gnbContext);

        while (!((GnbAppTask) gnbContext.itms.findTask(ItmsId.GNB_TASK_APP)).isInitialSctpReady()) {
            // just wait until the gNB says my initial SCTP connection is ready.
            Utils.sleep(100);
        }

        for (int i = 0; i < numberOfUe; i++) {
            var ref = appConfig.createUeConfig();
            var imsiNumber = Utils.padLeft(new BigInteger(ref.supi.value).add(BigInteger.valueOf(i)).toString(), 15, '0');
            var supi = new Supi("imsi", imsiNumber).toString();
            var config = new UeConfig(ref.key, ref.op, ref.amf, ref.imei, Supi.parse(supi), ref.plmn,
                    ref.smsOverNasSupported, ref.requestedNssai, ref.dnn);

            var ueContext = UeNode.createContext(this, config);

            ueMap.put(ueContext.ctxId, ueContext);
            UeNode.run(ueContext);

            ueContext.connectedGnb = gnbContext.ctxId;
        }
    }

    public String[] testCaseNames() {
        return testCases.keySet().toArray(new String[0]);
    }

    public void runTest(String testName) {
        var testCmds = testCases.get(testName);
        if (testCmds == null) {
            throw new RuntimeException("test case not found: " + testName);
        }

        for (var command : testCmds) {
            if (command instanceof TestCmd_Sleep) {
                var cmd = (TestCmd_Sleep) command;
                if (cmd.duration > 1) {
                    Utils.sleep(1000);
                    Log.info(Tag.SYSTEM, "Waiting for user-defined sleep (%s s)", cmd.duration);
                    Utils.sleep((cmd.duration - 1) * 1000);
                } else {
                    Utils.sleep(cmd.duration * 1000);
                }
            } else if (command instanceof TestCmd_InitialRegistration) {
                ueMap.values().forEach(ue -> ue.itms.sendMessage(ItmsId.UE_TASK_APP, new IwUeTestCommand(command)));
            } else if (command instanceof TestCmd_PeriodicRegistration) {
                ueMap.values().forEach(ue -> ue.itms.sendMessage(ItmsId.UE_TASK_APP, new IwUeTestCommand(command)));
            } else if (command instanceof TestCmd_Deregistration) {
                ueMap.values().forEach(ue -> ue.itms.sendMessage(ItmsId.UE_TASK_APP, new IwUeTestCommand(command)));
            } else if (command instanceof TestCmd_PduSessionEstablishment) {
                ueMap.values().forEach(ue -> ue.itms.sendMessage(ItmsId.UE_TASK_APP, new IwUeTestCommand(command)));
            } else if (command instanceof TestCmd_Ping) {
                ueMap.values().forEach(ue -> ue.itms.sendMessage(ItmsId.UE_TASK_APP, new IwUeTestCommand(command)));
            }
        }
    }

    public UeSimContext findUe(UUID id) {
        synchronized (this) {
            return ueMap.get(id);
        }
    }

    public GnbSimContext findGnb(UUID id) {
        synchronized (this) {
            return gnbMap.get(id);
        }
    }

    public HashSet<UUID> allUes() {
        var res = new HashSet<UUID>();
        synchronized (this) {
            for (var item : ueMap.values()) {
                res.add(item.ctxId);
            }
        }
        return res;
    }

    public HashSet<UUID> allGnbs() {
        var res = new HashSet<UUID>();
        synchronized (this) {
            for (var item : gnbMap.values()) {
                res.add(item.ctxId);
            }
        }
        return res;
    }

    public AirSimContext getAirCtx() {
        return airCtx;
    }

    public void triggerOnSend(BaseSimContext ctx, Object msg) {
        for (var listener : messagingListeners) {
            listener.onSend(ctx, msg);
        }
    }

    public void triggerOnReceive(BaseSimContext ctx, Object msg) {
        for (var listener : messagingListeners) {
            listener.onReceive(ctx, msg);
        }
    }
}