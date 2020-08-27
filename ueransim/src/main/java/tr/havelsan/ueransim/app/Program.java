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

package tr.havelsan.ueransim.app;

import tr.havelsan.ueransim.app.api.sys.Simulation;
import tr.havelsan.ueransim.app.core.UeSimContext;
import tr.havelsan.ueransim.app.core.nodes.GnbNode;
import tr.havelsan.ueransim.app.core.nodes.UeNode;
import tr.havelsan.ueransim.app.events.ue.UeCommandEvent;
import tr.havelsan.ueransim.app.events.ue.UeEvent;
import tr.havelsan.ueransim.app.mts.MtsInitializer;
import tr.havelsan.ueransim.app.structs.Supi;
import tr.havelsan.ueransim.app.structs.UeConfig;
import tr.havelsan.ueransim.app.testing.*;
import tr.havelsan.ueransim.mts.ImplicitTypedObject;
import tr.havelsan.ueransim.mts.MtsContext;
import tr.havelsan.ueransim.utils.Console;
import tr.havelsan.ueransim.utils.Logging;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.Utils;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {

    private final MtsContext defaultMts;
    private final MtsContext testingMts;
    private final AppConfig app;

    public Program() {
        this.defaultMts = new MtsContext();
        this.testingMts = new MtsContext();

        MtsInitializer.initDefaultMts(defaultMts);
        MtsInitializer.initTestingMts(testingMts);

        this.app = new AppConfig(defaultMts);

        initLogging();
    }

    public static void main(String[] args) throws Exception {
        new Program().run();
    }

    public static void fail(Throwable t) {
        t.printStackTrace();
        Logging.error(Tag.SYSTEM, "%s", t);
        System.exit(1);
    }

    private static void initLogging() {
        final String logFile = "app.log";

        Console.setStandardPrintEnabled(true);
        Console.addPrintHandler(str -> {
            final Path path = Paths.get(logFile);
            try {
                Files.write(path, str.getBytes(StandardCharsets.UTF_8),
                        Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void run() throws Exception {
        var testing = (ImplicitTypedObject) testingMts.decoder.decode("config/testing.yaml");

        var loadTesting = (ImplicitTypedObject) testing.get("load-testing");
        var numberOfUe = loadTesting.getInt("number-of-UE");

        var testCases = Utils.streamToList(((ImplicitTypedObject) testing.get("test-cases")).getParameters().entrySet().stream());

        System.out.println("List of possible tests:");
        for (int i = 0; i < testCases.size(); i++) {
            System.out.println((i + 1) + ") " + testCases.get(i).getKey());
        }

        System.out.println("Selection: ");

        var scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        int number = -1;
        try {
            number = Integer.parseInt(line);
        } catch (Exception ignored) {
        }

        if (number < 1 || number > testCases.size()) {
            System.err.println("Invalid selection: " + number);
            return;
        }

        var testName = testCases.get(number - 1).getKey();
        var testObjects = (Object[]) testCases.get(number - 1).getValue();
        var testCommands = new TestCommand[testObjects.length];
        for (int i = 0; i < testCommands.length; i++) {
            testCommands[i] = (TestCommand) testObjects[i];
        }

        runTest(numberOfUe, testName, testCommands);
    }

    private void runTest(int numberOfUe, String testName, TestCommand[] testCommands) throws Exception {
        var simContext = app.createSimContext(null);

        var gnbContext = app.createGnbSimContext(simContext, app.createGnbConfig());
        Simulation.registerGnb(simContext, gnbContext);
        GnbNode.run(gnbContext);

        // todo: ensure gnbs are good to go
        Thread.sleep(2000);

        var ueContexts = new ArrayList<UeSimContext>();
        for (int i = 0; i < numberOfUe; i++) {
            var ref = app.createUeConfig();
            var imsiNumber = Utils.padLeft(new BigInteger(ref.supi.value).add(BigInteger.valueOf(i)).toString(), 15, '0');
            var supi = new Supi("imsi", imsiNumber).toString();
            var config = new UeConfig(ref.snn, ref.key.toHexString(), ref.op.toHexString(), ref.amf.toHexString(), ref.imei,
                    supi, ref.smsOverNasSupported, ref.requestedNssai, ref.userLocationInformationNr,
                    new String(ref.dnn.data.toByteArray(), StandardCharsets.US_ASCII));

            var ueContext = app.createUeSimContext(simContext, config);

            Simulation.registerUe(simContext, ueContext);
            UeNode.run(ueContext);

            Simulation.connectUeToGnb(ueContext, gnbContext);
            ueContexts.add(ueContext);
        }

        for (var command : testCommands) {
            if (command instanceof TestCommand_Sleep) {
                Thread.sleep(((TestCommand_Sleep) command).duration * 1000);
            } else if (command instanceof TestCommand_InitialRegistration) {
                ueContexts.forEach(ue -> ue.pushEvent(new UeCommandEvent(command)));
            } else if (command instanceof TestCommand_PeriodicRegistration) {
                ueContexts.forEach(ue -> ue.pushEvent(new UeCommandEvent(command)));
            } else if (command instanceof TestCommand_Deregistration) {
                ueContexts.forEach(ue -> ue.pushEvent(new UeCommandEvent(command)));
            } else if (command instanceof TestCommand_PduSessionEstablishment) {
                ueContexts.forEach(ue -> ue.pushEvent(new UeCommandEvent(command)));
            }
        }
    }
}