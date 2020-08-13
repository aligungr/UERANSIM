package tr.havelsan.ueransim;

import tr.havelsan.ueransim.api.sys.Simulation;
import tr.havelsan.ueransim.core.GnbSimContext;
import tr.havelsan.ueransim.core.UeSimContext;
import tr.havelsan.ueransim.core.nodes.GnbNode;
import tr.havelsan.ueransim.core.nodes.UeNode;
import tr.havelsan.ueransim.events.EventParser;
import tr.havelsan.ueransim.events.gnb.GnbEvent;
import tr.havelsan.ueransim.events.ue.UeEvent;
import tr.havelsan.ueransim.mts.ImplicitTypedObject;
import tr.havelsan.ueransim.mts.MtsConstruct;
import tr.havelsan.ueransim.mts.MtsDecoder;
import tr.havelsan.ueransim.mts.MtsInitializer;
import tr.havelsan.ueransim.structs.GnbConfig;
import tr.havelsan.ueransim.structs.GnbUeContext;
import tr.havelsan.ueransim.structs.UeConfig;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;
import tr.havelsan.ueransim.utils.Json;
import tr.havelsan.ueransim.utils.Utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadTest {

    public static void main(String[] args) {
        MtsInitializer.initMts();
        AppConfig.initialize();
        initLogging();

        var simContext = AppConfig.createSimContext();

        final int COUNT = 100;

        var gnbs = new ArrayList<GnbSimContext>();
        var ues = new ArrayList<UeSimContext>();

        for (int i = 0; i < COUNT; i++) {
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

            var supi = ref.supi.toString().substring(0, ref.supi.toString().length() - String.valueOf(COUNT).length());
            supi += Utils.padLeft(i + "", String.valueOf(COUNT).length(), '0');

            var config = new UeConfig(ref.snn, ref.key.toHexString(), ref.op.toHexString(), ref.amf.toHexString(), ref.imei, supi, ref.smsOverNasSupported, ref.requestedNssai, ref.userLocationInformationNr, new String(ref.dnn.data.toByteArray(), StandardCharsets.US_ASCII));
            ues.add(AppConfig.createUeSimContext(simContext, config));
        }

        for (var ue : ues) {
            Simulation.registerUe(simContext, ue);
            UeNode.run(ue);
        }

        for (int i = 0; i < COUNT; i++) {
            Simulation.connectUeToGnb(ues.get(i), gnbs.get(i));
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

        Console.println(Color.YELLOW_BOLD_BRIGHT, "WARNING: All logs are written to: %s", logFile);
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
    }
}
