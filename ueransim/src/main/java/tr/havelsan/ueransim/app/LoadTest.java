package tr.havelsan.ueransim.app;

import tr.havelsan.ueransim.app.api.sys.INodeMessagingListener;
import tr.havelsan.ueransim.app.api.sys.Simulation;
import tr.havelsan.ueransim.app.core.BaseSimContext;
import tr.havelsan.ueransim.app.core.GnbSimContext;
import tr.havelsan.ueransim.app.core.UeSimContext;
import tr.havelsan.ueransim.app.core.nodes.GnbNode;
import tr.havelsan.ueransim.app.core.nodes.UeNode;
import tr.havelsan.ueransim.app.events.EventParser;
import tr.havelsan.ueransim.app.events.gnb.GnbEvent;
import tr.havelsan.ueransim.app.events.ue.UeEvent;
import tr.havelsan.ueransim.mts.ImplicitTypedObject;
import tr.havelsan.ueransim.mts.MtsConstruct;
import tr.havelsan.ueransim.mts.MtsDecoder;
import tr.havelsan.ueransim.app.mts.MtsInitializer;
import tr.havelsan.ueransim.nas.impl.messages.*;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupFailure;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupRequest;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupResponse;
import tr.havelsan.ueransim.app.structs.GnbConfig;
import tr.havelsan.ueransim.app.structs.Supi;
import tr.havelsan.ueransim.app.structs.UeConfig;
import tr.havelsan.ueransim.utils.*;

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

public class LoadTest {

    private static final BaseConsole loadTestConsole = new BaseConsole();

    public static void main(String[] args) {
        MtsInitializer.initMts();
        AppConfig.initialize();
        initLogging();

        var simContext = AppConfig.createSimContext(new NodeMessagingListener());

        final int COUNT = 50;

        var gnbs = new ArrayList<GnbSimContext>();
        var ues = new ArrayList<UeSimContext>();

        for (int i = 0; i < 1; i++) {
            var ref = MtsConstruct.construct(GnbConfig.class, (ImplicitTypedObject) MtsDecoder.decode(AppConfig.PROFILE + "gnb.yaml"), true);

            var config = new GnbConfig(i + 1, ref.gnbPlmn, ref.amfConfigs, ref.supportedTAs, ref.ignoreStreamIds);
            gnbs.add(AppConfig.createGnbSimContext(simContext, config));
        }

        for (var gnb : gnbs) {
            Simulation.registerGnb(simContext, gnb);
            GnbNode.run(gnb);
        }

        for (int i = 0; i < COUNT; i++) {
            var ref = ((ImplicitTypedObject) MtsDecoder.decode(AppConfig.PROFILE + "ue.yaml")).asConstructed(UeConfig.class);

            var imsiNumber = Utils.padLeft(new BigInteger(ref.supi.value).add(BigInteger.valueOf(i)).toString(), 15, '0');
            var supi = new Supi("imsi", imsiNumber).toString();

            var config = new UeConfig(ref.snn, ref.key.toHexString(), ref.op.toHexString(), ref.amf.toHexString(), ref.imei, supi, ref.smsOverNasSupported, ref.requestedNssai, ref.userLocationInformationNr, new String(ref.dnn.data.toByteArray(), StandardCharsets.US_ASCII));
            ues.add(AppConfig.createUeSimContext(simContext, config));
        }

        for (var ue : ues) {
            Simulation.registerUe(simContext, ue);
            UeNode.run(ue);
        }

        for (int i = 0; i < COUNT; i++) {
            Simulation.connectUeToGnb(ues.get(i), gnbs.get(0));
        }

        var scanner = new Scanner(System.in);

        System.out.println("Possible events:" + Json.toJson(EventParser.possibleEvents()));
        while (true) {
            System.out.println("Enter event to execute:");
            String line = scanner.nextLine();
            var event = EventParser.parse(line);
            if (event == null) {
                System.out.println("Event not found: " + line);
            } else {
                if (event instanceof GnbEvent) {
                    gnbs.forEach(gnbSimContext -> gnbSimContext.pushEvent((GnbEvent) event));
                } else if (event instanceof UeEvent) {
                    ues.forEach(ueSimContext -> ueSimContext.pushEvent((UeEvent) event));
                }
                System.out.println("Event pushed.");
            }
        }
    }

    private static void initLogging() {
        final String logFile = "app.log";
        final String loadTestFile = "loadtest.log";

        Console.println(Color.YELLOW_BOLD_BRIGHT, "WARNING: All default logs are written to: %s", logFile);
        Console.println(Color.YELLOW_BOLD_BRIGHT, "WARNING: All load testing logs are written to: %s", loadTestFile);

        Console.setStandardPrintEnabled(false);
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

    private static class NodeMessagingListener implements INodeMessagingListener {
        private final Map<Integer, Long> ngSetupTimers = new HashMap<>();
        private final Map<String, Long> registrationTimers = new HashMap<>();
        private final Map<String, Long> authenticationTimers = new HashMap<>();
        private final Map<String, Long> securityModeControlTimers = new HashMap<>();
        private final Map<String, Long> phase1Timers = new HashMap<>();
        private final Map<String, Long> phase2Timers = new HashMap<>();
        private final Map<String, Long> phase3Timers = new HashMap<>();

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
                loadTestConsole.println(null, "\u2714     [Authentication (UE/RAN)] [ue: %s] [%d ms]", supi, delta);
            } else if (message instanceof SecurityModeComplete) {
                String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
                phase3Timers.put(supi, System.currentTimeMillis());

                long delta = System.currentTimeMillis() - securityModeControlTimers.get(supi);
                loadTestConsole.println(null, "\u2714     [Security Mode Control (UE/RAN)] [ue: %s] [%d ms]", supi, delta);
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
                loadTestConsole.println(null, "\u2714     [Phase 3 (Network)] [SecurityModeControl-RegistrationAccept] [ue: %s] [%d ms]", supi, delta2);
            } else if (message instanceof AuthenticationRequest) {
                String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
                authenticationTimers.put(supi, System.currentTimeMillis());

                long delta = System.currentTimeMillis() - phase1Timers.get(supi);
                loadTestConsole.println(null, "\u2714     [Phase 1 (Network)] [Registration-Authentication] [ue: %s] [%d ms]", supi, delta);
            } else if (message instanceof SecurityModeCommand) {
                String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
                securityModeControlTimers.put(supi, System.currentTimeMillis());

                long delta = System.currentTimeMillis() - phase2Timers.get(supi);
                loadTestConsole.println(null, "\u2714     [Phase 2 (Network)] [Authentication-SecurityModeControl] [ue: %s] [%d ms]", supi, delta);
            }
        }
    }
}
