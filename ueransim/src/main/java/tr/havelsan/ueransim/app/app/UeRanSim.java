/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package tr.havelsan.ueransim.app.app;

import tr.havelsan.ueransim.app.app.listeners.INodeMessagingListener;
import tr.havelsan.ueransim.app.common.Supi;
import tr.havelsan.ueransim.app.common.configs.LoadTestConfig;
import tr.havelsan.ueransim.app.common.configs.UeConfig;
import tr.havelsan.ueransim.app.common.itms.IwUeTestCommand;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.common.testcmd.*;
import tr.havelsan.ueransim.app.gnb.GnbNode;
import tr.havelsan.ueransim.app.gnb.app.GnbAppTask;
import tr.havelsan.ueransim.app.ue.UeNode;
import tr.havelsan.ueransim.app.utils.MtsInitializer;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.mts.MtsContext;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.console.Log;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class UeRanSim {

    private final MtsContext defaultMts;
    private final AppConfig app;
    private final List<INodeMessagingListener> messagingListeners;
    private final LinkedHashMap<String, List<TestCmd>> testCases;
    private final LoadTestConfig loadTesting;

    private ArrayList<UeSimContext> ueContexts;
    private SimulationContext simCtx;

    UeRanSim(List<INodeMessagingListener> messagingListeners,
             LinkedHashMap<String, List<TestCmd>> testCases,
             LoadTestConfig loadTesting) {

        this.defaultMts = new MtsContext();
        this.messagingListeners = messagingListeners;
        this.testCases = testCases;
        this.loadTesting = loadTesting;

        MtsInitializer.initDefaultMts(defaultMts);

        this.app = new AppConfig(defaultMts, this);

        initialize();
    }

    private void initialize() {
        var numberOfUe = loadTesting.numberOfUes;

        simCtx = new SimulationContext(Utils.merge(Arrays.asList(), messagingListeners));

        var gnbContext = app.createGnbSimContext(simCtx, app.createGnbConfig());
        Simulation.registerGnb(simCtx, gnbContext);
        GnbNode.run(gnbContext);

        while (!((GnbAppTask) gnbContext.itms.findTask(ItmsId.GNB_TASK_APP)).isInitialSctpReady()) {
            // just wait until the gNB says my initial SCTP connection is ready.
            Utils.sleep(100);
        }

        ueContexts = new ArrayList<>();
        for (int i = 0; i < numberOfUe; i++) {
            var ref = app.createUeConfig();
            var imsiNumber = Utils.padLeft(new BigInteger(ref.supi.value).add(BigInteger.valueOf(i)).toString(), 15, '0');
            var supi = new Supi("imsi", imsiNumber).toString();
            var config = new UeConfig(ref.snn, ref.key, ref.op, ref.amf, ref.imei, Supi.parse(supi),
                    ref.smsOverNasSupported, ref.requestedNssai, ref.dnn);

            var ueContext = app.createUeSimContext(simCtx, config);

            Simulation.registerUe(simCtx, ueContext);
            UeNode.run(ueContext);

            Simulation.connectUeToGnb(ueContext, gnbContext);
            ueContexts.add(ueContext);
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
                ueContexts.forEach(ue -> ue.itms.sendMessage(ItmsId.UE_TASK_APP, new IwUeTestCommand(command)));
            } else if (command instanceof TestCmd_PeriodicRegistration) {
                ueContexts.forEach(ue -> ue.itms.sendMessage(ItmsId.UE_TASK_APP, new IwUeTestCommand(command)));
            } else if (command instanceof TestCmd_Deregistration) {
                ueContexts.forEach(ue -> ue.itms.sendMessage(ItmsId.UE_TASK_APP, new IwUeTestCommand(command)));
            } else if (command instanceof TestCmd_PduSessionEstablishment) {
                ueContexts.forEach(ue -> ue.itms.sendMessage(ItmsId.UE_TASK_APP, new IwUeTestCommand(command)));
            } else if (command instanceof TestCmd_Ping) {
                ueContexts.forEach(ue -> ue.itms.sendMessage(ItmsId.UE_TASK_APP, new IwUeTestCommand(command)));
            }
        }
    }

    public SimulationContext getSimCtx() {
        return simCtx;
    }
}