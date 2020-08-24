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
import tr.havelsan.ueransim.app.core.nodes.GnbNode;
import tr.havelsan.ueransim.app.core.nodes.UeNode;
import tr.havelsan.ueransim.app.events.EventParser;
import tr.havelsan.ueransim.app.events.gnb.GnbEvent;
import tr.havelsan.ueransim.app.events.ue.UeEvent;
import tr.havelsan.ueransim.mts.ImplicitTypedObject;
import tr.havelsan.ueransim.mts.MtsDecoder;
import tr.havelsan.ueransim.app.mts.MtsInitializer;
import tr.havelsan.ueransim.utils.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        MtsInitializer.initMts();
        AppConfig.initialize();
        initLogging();

        var simContext = AppConfig.createSimContext(null);

        var gnbContext = AppConfig.createGnbSimContext(simContext, (ImplicitTypedObject) MtsDecoder.decode(AppConfig.PROFILE + "gnb.yaml"));
        Simulation.registerGnb(simContext, gnbContext);
        GnbNode.run(gnbContext);

        var ueContext = AppConfig.createUeSimContext(simContext, (ImplicitTypedObject) MtsDecoder.decode(AppConfig.PROFILE + "ue.yaml"));
        Simulation.registerUe(simContext, ueContext);
        UeNode.run(ueContext);

        Simulation.connectUeToGnb(ueContext, gnbContext);

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
                    gnbContext.pushEvent((GnbEvent) event);
                } else if (event instanceof UeEvent) {
                    ueContext.pushEvent((UeEvent) event);
                }
                System.out.println("Event pushed.");
            }
        }
    }

    public static void fail(Throwable t) {
        t.printStackTrace();
        Logging.error(Tag.SYSTEM, "%s", t);
        System.exit(1);
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