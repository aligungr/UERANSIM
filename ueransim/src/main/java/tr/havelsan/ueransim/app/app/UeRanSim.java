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

import tr.havelsan.ueransim.app.common.Supi;
import tr.havelsan.ueransim.app.common.configs.UeConfig;
import tr.havelsan.ueransim.app.common.itms.IwUeTestCommand;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.common.testcmd.*;
import tr.havelsan.ueransim.app.gnb.GnbNode;
import tr.havelsan.ueransim.app.gnb.app.GnbAppTask;
import tr.havelsan.ueransim.app.ue.UeNode;
import tr.havelsan.ueransim.app.utils.MtsInitializer;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.mts.ImplicitTypedObject;
import tr.havelsan.ueransim.mts.MtsContext;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.console.BaseConsole;
import tr.havelsan.ueransim.utils.console.Console;
import tr.havelsan.ueransim.utils.console.Log;
import tr.havelsan.ueransim.utils.console.Logger;
import tr.havelsan.ueransim.utils.jcolor.AnsiPalette;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;

public class UeRanSim {

    private final MtsContext defaultMts;
    private final MtsContext testingMts;
    private final AppConfig app;
    private final BaseConsole loadTestConsole;
    private final ImplicitTypedObject testCases;
    private final ImplicitTypedObject loadTesting;
    private ArrayList<UeSimContext> ueContexts;
    private SimulationContext simCtx;

    public UeRanSim() {
        this.defaultMts = new MtsContext();
        this.testingMts = new MtsContext();

        MtsInitializer.initDefaultMts(defaultMts);
        MtsInitializer.initTestingMts(testingMts);

        this.app = new AppConfig(defaultMts, this);

        this.loadTestConsole = new BaseConsole();

        initLogging();

        testingMts.setTypeKeyword("@cmd");
        var testing = (ImplicitTypedObject) testingMts.decoder.decode("config/testing.yaml");

        loadTesting = (ImplicitTypedObject) testing.get("load-testing");
        testCases = (ImplicitTypedObject) testing.get("test-cases");

        initialize();
    }

    private void initLogging() {
        new File("logs").mkdir();

        AppConfig.loggingToFile(Logger.GLOBAL, "global", true);
        Log.registerLogger(Thread.currentThread(), Logger.GLOBAL);

        final String globalLogFile = "logs/global.log";
        final String loadTestFile = "logs/loadtest.log";

        Console.println(AnsiPalette.PAINT_IMPORTANT_WARNING, "WARNING: All global logs are written to: %s", globalLogFile);
        Console.println(AnsiPalette.PAINT_IMPORTANT_WARNING, "WARNING: All logs of UEs and gNBs are written to their own log files: logs/*");
        Console.println(AnsiPalette.PAINT_IMPORTANT_WARNING, "WARNING: All load testing logs are written to: %s", loadTestFile);

        loadTestConsole.setStandardPrintEnabled(false);
        loadTestConsole.addPrintHandler(str -> {
            final Path path = Paths.get(loadTestFile);
            try {
                Files.write(path, str.getBytes(StandardCharsets.UTF_8),
                        Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        loadTestConsole.printDiv();
    }

    private void initialize() {
        var numberOfUe = loadTesting.getInt("number-of-UE");

        simCtx = new SimulationContext(Arrays.asList(new LoadTestMessagingListener(loadTestConsole)));

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
        return testCases.getParameters().keySet().toArray(new String[0]);
    }

    public void runTest(String testName) {
        var testObjects = (Object[]) testCases.get(testName);
        if (testObjects == null) {
            throw new RuntimeException("test case not found: " + testName);
        }

        var testCommands = new TestCmd[testObjects.length];
        for (int i = 0; i < testCommands.length; i++) {
            testCommands[i] = (TestCmd) testObjects[i];
        }
        runTest(testName, testCommands);
    }

    private void runTest(String testName, TestCmd[] testCmds) {
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