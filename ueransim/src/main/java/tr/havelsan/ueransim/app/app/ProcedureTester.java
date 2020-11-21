/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app;

import tr.havelsan.ueransim.app.app.listeners.INodeConnectionListener;
import tr.havelsan.ueransim.app.app.listeners.INodeMessagingListener;
import tr.havelsan.ueransim.app.common.Supi;
import tr.havelsan.ueransim.app.common.configs.UeConfig;
import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.console.Log;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class ProcedureTester implements INodeConnectionListener, INodeMessagingListener {

    // Initialization state variables
    private static final int INIT_STATE__WAITING_GNB = 1;
    private static final int INIT_STATE__WAITING_UE = 2;
    private static final int INIT_STATE__INIT_DONE = 3;

    // General fields
    private AppConfig appConfig;
    private UeRanSim sim;
    private UUID gnbId;
    private List<UUID> ueIds;

    // Fields related to initialization
    private int initState;
    private HashSet<UUID> waitingUes;

    // Fields related to testing params
    private int numberOfUe;

    public void start(UeRanSim ueRanSim, int numberOfUe) {
        this.appConfig = new AppConfig();
        this.sim = ueRanSim;
        this.ueIds = new ArrayList<>();
        this.numberOfUe = numberOfUe;

        gnbId = sim.createGnb(appConfig.createGnbConfig());
        initState = INIT_STATE__WAITING_GNB;
    }

    @Override
    public void onConnected(BaseSimContext ctx, Type connectionType) {
        if (initState == INIT_STATE__WAITING_GNB) {
            if (ctx.ctxId.equals(gnbId) && connectionType == Type.SCTP) {
                // gNB is ready, now create the UEs.
                for (int i = 0; i < numberOfUe; i++) {
                    ueIds.add(sim.createUe(createUeConfig(i)));
                }

                this.waitingUes = new HashSet<>(ueIds);

                initState = INIT_STATE__WAITING_UE;
            }
        }

        if (initState == INIT_STATE__WAITING_UE) {
            if (connectionType == Type.UE_MR_GNB) {
                this.waitingUes.remove(ctx.ctxId);

                if (this.waitingUes.isEmpty()) {
                    Utils.sleep(100);
                    initState = INIT_STATE__INIT_DONE;
                    Log.success(Tag.SYSTEM, "All nodes are initialized.");
                }
            }
        }
    }

    @Override
    public void onSend(BaseSimContext ctx, Object message) {
        if (initState != INIT_STATE__INIT_DONE)
            return;

    }

    @Override
    public void onReceive(BaseSimContext ctx, Object message) {
        if (initState != INIT_STATE__INIT_DONE)
            return;
    }

    private UeConfig createUeConfig(int index) {
        var ref = appConfig.createUeConfig();
        var imsiNumber = Utils.padLeft(new BigInteger(ref.supi.value).add(BigInteger.valueOf(index)).toString(), 15, '0');
        var supi = new Supi("imsi", imsiNumber).toString();
        return new UeConfig(ref.key, ref.op, ref.amf, ref.imei, Supi.parse(supi), ref.plmn,
                ref.smsOverNasSupported, ref.requestedNssai, ref.dnn);
    }
}
