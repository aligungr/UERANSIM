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

import tr.havelsan.ueransim.app.api.sys.INodeMessagingListener;
import tr.havelsan.ueransim.app.api.sys.Simulation;
import tr.havelsan.ueransim.app.core.BaseSimContext;
import tr.havelsan.ueransim.app.core.GnbSimContext;
import tr.havelsan.ueransim.app.core.UeSimContext;
import tr.havelsan.ueransim.app.core.nodes.GnbNode;
import tr.havelsan.ueransim.app.core.nodes.UeNode;
import tr.havelsan.ueransim.app.events.ue.UeCommandEvent;
import tr.havelsan.ueransim.app.mts.MtsInitializer;
import tr.havelsan.ueransim.app.structs.Supi;
import tr.havelsan.ueransim.app.structs.UeConfig;
import tr.havelsan.ueransim.app.testing.*;
import tr.havelsan.ueransim.mts.ImplicitTypedObject;
import tr.havelsan.ueransim.mts.MtsContext;
import tr.havelsan.ueransim.nas.impl.messages.*;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupFailure;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupRequest;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupResponse;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.console.BaseConsole;
import tr.havelsan.ueransim.utils.console.Console;
import tr.havelsan.ueransim.utils.console.Logging;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {

    private final MtsContext defaultMts;
    private final MtsContext testingMts;
    private final AppConfig app;
    private final BaseConsole loadTestConsole;
    private final ImplicitTypedObject testCases;
    private final ImplicitTypedObject loadTesting;
    private ArrayList<UeSimContext> ueContexts;

    public Program() throws Exception {
        this.defaultMts = new MtsContext();
        this.testingMts = new MtsContext();

        MtsInitializer.initDefaultMts(defaultMts);
        MtsInitializer.initTestingMts(testingMts);

        this.app = new AppConfig(defaultMts);

        this.loadTestConsole = new BaseConsole();

        initLogging();

        var testing = (ImplicitTypedObject) testingMts.decoder.decode("config/testing.yaml");

        loadTesting = (ImplicitTypedObject) testing.get("load-testing");
        testCases = (ImplicitTypedObject) testing.get("test-cases");

        initialize();
    }

    public static void main(String[] args) throws Exception {
        new Program().runUserPrompt();
    }

    public static void fail(Throwable t) {
        t.printStackTrace();
        Logging.error(Tag.SYSTEM, "%s", t);
        System.exit(1);
    }

    private void initLogging() {
        new File("logs").mkdir();

        final String logFile = "logs/app.log";
        final String loadTestFile = "logs/loadtest.log";

        Console.println(AnsiPalette.PAINT_IMPORTANT_WARNING, "WARNING: All default logs are written to: %s", logFile);
        Console.println(AnsiPalette.PAINT_IMPORTANT_WARNING, "WARNING: All load testing logs are written to: %s", loadTestFile);

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

    private void initialize() throws Exception {
        var numberOfUe = loadTesting.getInt("number-of-UE");

        var simContext = app.createSimContext(new NodeMessagingListener());

        var gnbContext = app.createGnbSimContext(simContext, app.createGnbConfig());
        Simulation.registerGnb(simContext, gnbContext);
        GnbNode.run(gnbContext);

        // todo: ensure gnbs are good to go
        Thread.sleep(1500);

        ueContexts = new ArrayList<>();
        for (int i = 0; i < numberOfUe; i++) {
            var ref = app.createUeConfig();
            var imsiNumber = Utils.padLeft(new BigInteger(ref.supi.value).add(BigInteger.valueOf(i)).toString(), 15, '0');
            var supi = new Supi("imsi", imsiNumber).toString();
            var config = new UeConfig(ref.snn, ref.key, ref.op, ref.amf, ref.imei, Supi.parse(supi),
                    ref.smsOverNasSupported, ref.requestedNssai, ref.dnn);

            var ueContext = app.createUeSimContext(simContext, config);

            Simulation.registerUe(simContext, ueContext);
            UeNode.run(ueContext);

            Simulation.connectUeToGnb(ueContext, gnbContext);
            ueContexts.add(ueContext);
        }
    }

    public void runUserPrompt() throws Exception {
        Thread.sleep(250);

        Console.println(AnsiPalette.PAINT_DIVIDER, "=============================================================================");

        var testCases = Utils.streamToList(this.testCases.getParameters().entrySet().stream());

        Console.println(AnsiPalette.PAINT_INPUT, "List of possible tests:");
        for (int i = 0; i < testCases.size(); i++) {
            Console.print(AnsiPalette.PAINT_INPUT, (i + 1) + ") ");
            Console.println(null, testCases.get(i).getKey());
        }

        Console.print(AnsiPalette.PAINT_INPUT, "Selection: ");

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

        runTest(testCases.get(number - 1).getKey());
    }

    public String[] testCaseNames() {
        return testCases.getParameters().keySet().toArray(new String[0]);
    }

    public void runTest(String testName) throws Exception {
        var testObjects = (Object[]) testCases.get(testName);
        if (testObjects == null) {
            throw new RuntimeException("test case not found: " + testName);
        }

        var testCommands = new TestCommand[testObjects.length];
        for (int i = 0; i < testCommands.length; i++) {
            testCommands[i] = (TestCommand) testObjects[i];
        }
        runTest(testName, testCommands);
    }

    private void runTest(String testName, TestCommand[] testCommands) throws Exception {
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

    private class NodeMessagingListener implements INodeMessagingListener {
        private final Map<Integer, Long> ngSetupTimers = new HashMap<>();
        private final Map<String, Long> registrationTimers = new HashMap<>();
        private final Map<String, Long> authenticationTimers = new HashMap<>();
        private final Map<String, Long> securityModeControlTimers = new HashMap<>();
        private final Map<String, Long> phase1Timers = new HashMap<>();
        private final Map<String, Long> phase2Timers = new HashMap<>();
        private final Map<String, Long> phase3Timers = new HashMap<>();
        private final Map<String, Long> deregistrationTimers = new HashMap<>();

        @Override
        public void onSend(BaseSimContext<?> ctx, Object message) {
            if (message instanceof NGAP_NGSetupRequest) {
                int gnbId = ((GnbSimContext) ctx).config.gnbId;
                ngSetupTimers.put(gnbId, System.currentTimeMillis());
            } else if (message instanceof RegistrationRequest) {
                String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
                registrationTimers.put(supi, System.currentTimeMillis());
                phase1Timers.put(supi, System.currentTimeMillis());
            } else if (message instanceof AuthenticationResponse) {
                String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
                phase2Timers.put(supi, System.currentTimeMillis());

                long delta = System.currentTimeMillis() - authenticationTimers.get(supi);
                loadTestConsole.println(null, "\u2714 [Authentication (UE/RAN)] [ue: %s] [%d ms]", supi, delta);
            } else if (message instanceof SecurityModeComplete) {
                String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
                phase3Timers.put(supi, System.currentTimeMillis());

                long delta = System.currentTimeMillis() - securityModeControlTimers.get(supi);
                loadTestConsole.println(null, "\u2714 [Security Mode Control (UE/RAN)] [ue: %s] [%d ms]", supi, delta);
            } else if (message instanceof DeRegistrationRequestUeOriginating) {
                String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
                deregistrationTimers.put(supi, System.currentTimeMillis());
            }
        }

        @Override
        public void onReceive(BaseSimContext<?> ctx, Object message) {
            if (message instanceof NGAP_NGSetupFailure) {
                int gnbId = ((GnbSimContext) ctx).config.gnbId;
                long delta = System.currentTimeMillis() - ngSetupTimers.get(gnbId);
                loadTestConsole.println(null, "\u2718 [NGSetup] [gnbId: %d] [%d ms]", gnbId, delta);
            } else if (message instanceof NGAP_NGSetupResponse) {
                int gnbId = ((GnbSimContext) ctx).config.gnbId;
                long delta = System.currentTimeMillis() - ngSetupTimers.get(gnbId);
                loadTestConsole.println(null, "\u2714 [NGSetup] [gnbId: %d] [%d ms]", gnbId, delta);
            } else if (message instanceof RegistrationReject) {
                String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
                long delta = System.currentTimeMillis() - registrationTimers.get(supi);
                loadTestConsole.println(null, "\u2718 [Registration] [ue: %s] [%d ms]", supi, delta);
            } else if (message instanceof RegistrationAccept) {
                String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
                long delta = System.currentTimeMillis() - registrationTimers.get(supi);
                loadTestConsole.println(null, "\u2714 [Registration] [ue: %s] [%d ms]", supi, delta);

                long delta2 = System.currentTimeMillis() - phase3Timers.get(supi);
                loadTestConsole.println(null, "\u2714 [Phase 3 (Network)] [SecurityModeControl-RegistrationAccept] [ue: %s] [%d ms]", supi, delta2);
            } else if (message instanceof AuthenticationRequest) {
                String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
                authenticationTimers.put(supi, System.currentTimeMillis());

                long delta = System.currentTimeMillis() - phase1Timers.get(supi);
                loadTestConsole.println(null, "\u2714 [Phase 1 (Network)] [Registration-Authentication] [ue: %s] [%d ms]", supi, delta);
            } else if (message instanceof SecurityModeCommand) {
                String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
                securityModeControlTimers.put(supi, System.currentTimeMillis());

                long delta = System.currentTimeMillis() - phase2Timers.get(supi);
                loadTestConsole.println(null, "\u2714 [Phase 2 (Network)] [Authentication-SecurityModeControl] [ue: %s] [%d ms]", supi, delta);
            } else if (message instanceof DeRegistrationAcceptUeOriginating) {
                String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
                long delta = System.currentTimeMillis() - deregistrationTimers.get(supi);
                loadTestConsole.println(null, "\u2714 [De-Registration] [ue: %s] [%d ms]", supi, delta);
            }
        }
    }
}