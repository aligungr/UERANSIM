/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.tester;

import tr.havelsan.ueransim.app.app.AppConfig;
import tr.havelsan.ueransim.app.app.UeRanSim;
import tr.havelsan.ueransim.app.app.monitor.MonitorTask;
import tr.havelsan.ueransim.app.common.Supi;
import tr.havelsan.ueransim.app.common.configs.ProcTestConfig;
import tr.havelsan.ueransim.app.common.configs.UeConfig;
import tr.havelsan.ueransim.app.common.enums.EConnType;
import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.console.Log;

import java.math.BigInteger;
import java.util.*;

public class ProcedureTester extends MonitorTask {

    // Initialization state variables
    private static final int INIT_STATE__WAITING_GNB = 1;
    private static final int INIT_STATE__WAITING_UE = 2;
    private static final int INIT_STATE__INIT_DONE = 3;

    // General fields
    private final AppConfig appConfig;
    private UeRanSim sim;
    private UUID gnbId;
    private List<UUID> ueIds;
    private Map<UUID, UeTester> ueTesters;
    private ProcTestConfig procTestConfig;

    // Fields related to initialization
    private Runnable onInit;
    private int initState;
    private HashSet<UUID> waitingUes;

    public ProcedureTester(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public static String[] testCases() {
        return new String[]{"initial-registration", "periodic-registration",
                "de-registration", "pdu-session-establishment"};
    }

    public void init(UeRanSim ueransim, ProcTestConfig procTestConfig, Runnable onInit) {
        this.sim = ueransim;
        this.ueIds = new ArrayList<>();
        this.procTestConfig = procTestConfig;
        this.onInit = onInit;
        this.ueTesters = new HashMap<>();

        gnbId = sim.createGnb(appConfig.createGnbConfig());
        initState = INIT_STATE__WAITING_GNB;
    }

    @Override
    public void onConnected(BaseSimContext ctx, EConnType connType) {
        if (initState == INIT_STATE__WAITING_GNB) {
            if (ctx.ctxId.equals(gnbId) && connType == EConnType.SCTP) {
                // gNB is ready, now create the UEs.
                for (int i = 0; i < procTestConfig.numberOfUe; i++) {
                    ueIds.add(sim.createUe(createUeConfig(i)));
                }

                this.waitingUes = new HashSet<>(ueIds);

                initState = INIT_STATE__WAITING_UE;
            }
        }

        if (initState == INIT_STATE__WAITING_UE) {
            if (connType == EConnType.UE_MR_GNB) {
                this.waitingUes.remove(ctx.ctxId);

                if (this.waitingUes.isEmpty()) {
                    Utils.sleep(100);
                    initState = INIT_STATE__INIT_DONE;
                    Log.success(Tag.SYS, "All UE and gNBs are initialized.");
                    onInit.run();
                }
            }
        }

        if (initState == INIT_STATE__INIT_DONE) {
            var ueTester = ueTesters.get(ctx.ctxId);
            if (ueTester != null) {
                ueTester.onConnected(ctx, connType);
            }
        }
    }

    @Override
    public void onSend(BaseSimContext ctx, Object message) {
        if (initState != INIT_STATE__INIT_DONE)
            return;

        var ueTester = ueTesters.get(ctx.ctxId);
        if (ueTester != null) {
            ueTester.onSend(ctx, message);
        }
    }

    @Override
    public void onReceive(BaseSimContext ctx, Object message) {
        if (initState != INIT_STATE__INIT_DONE)
            return;

        var ueTester = ueTesters.get(ctx.ctxId);
        if (ueTester != null) {
            ueTester.onReceive(ctx, message);
        }
    }

    @Override
    public void onSwitched(BaseSimContext ctx, Enum<?> state) {
        if (initState != INIT_STATE__INIT_DONE)
            return;

        var ueTester = ueTesters.get(ctx.ctxId);
        if (ueTester != null) {
            ueTester.onSwitched(ctx, state);
        }
    }

    private UeConfig createUeConfig(int index) {
        var ref = appConfig.createUeConfig();
        var imsiNumber = Utils.padLeft(new BigInteger(ref.supi.value).add(BigInteger.valueOf(index)).toString(), 15, '0');
        var supi = new Supi("imsi", imsiNumber).toString();
        return new UeConfig(ref.key, ref.op, ref.amf, ref.imei, Supi.parse(supi), ref.plmn,
                ref.smsOverNasSupported, ref.requestedNssai, ref.dnn);
    }

    public void startTestCase(String name) {
        // Using push because this method may be called outside of this task's thread.
        push(() -> ueIds.forEach(id -> startTestCase(id, name)));
    }

    public void startTestCase(UUID ueId, String testName) {
        // Using push because this method may be called outside of this task's thread.
        push(() -> {
            if (initState != INIT_STATE__INIT_DONE)
                return;

            var ctx = sim.findUe(ueId);
            if (ctx == null) {
                Log.error(Tag.SYS, "UE not found for predefined procedure test.");
                return;
            }

            UeTester ueTester;
            switch (testName) {
                case "initial-registration":
                    ueTester = new InitialRegistrationTester(this, ctx, procTestConfig);
                    break;
                case "periodic-registration":
                    ueTester = new PeriodicRegistrationTester(this, ctx, procTestConfig);
                    break;
                case "de-registration":
                    ueTester = new DeRegistrationTester(this, ctx, procTestConfig);
                    break;
                case "pdu-session-establishment":
                    ueTester = new PduSessionEstablishmentTester(this, ctx, procTestConfig);
                    break;
                default:
                    Log.error(Tag.SYS, "Invalid predefined procedure test: \"%s\"", testName);
                    return;
            }

            ueTesters.put(ueId, ueTester);
            ueTester.onStart();
        });
    }
}
