/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.entry;

import tr.havelsan.ueransim.app.app.AppBuilder;
import tr.havelsan.ueransim.app.app.AppConfig;
import tr.havelsan.ueransim.app.app.tester.ProcedureTester;
import tr.havelsan.ueransim.app.common.configs.ProcTestConfig;
import tr.havelsan.ueransim.app.utils.MtsInitializer;
import tr.havelsan.ueransim.mts.ImplicitTypedObject;
import tr.havelsan.ueransim.mts.MtsContext;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Console;
import tr.havelsan.ueransim.utils.console.Log;
import tr.havelsan.ueransim.utils.jcolor.AnsiPalette;

import java.util.Scanner;

public class FlowTestApp {

    private ProcedureTester procTester;
    private ProcTestConfig procTestConfig;

    public static void main(String[] args) {
        BaseApp.main(args);
        new FlowTestApp().main();
    }

    private void main() {
        var appConfig = new AppConfig();
        procTester = new ProcedureTester(appConfig);
        procTestConfig = createProcTestConfig();

        var ueransim = new AppBuilder()
                .addNodeListener(procTester)
                .build();

        procTester.start(ueransim, procTestConfig, this::onTesterInit);
    }

    private ProcTestConfig createProcTestConfig() {
        var testingMts = new MtsContext();
        testingMts.setKebabCaseDecoding(true);
        MtsInitializer.initTestingMts(testingMts);

        var ito = (ImplicitTypedObject) testingMts.decoder.decode("config/proc-testing.yaml");
        return testingMts.constructor.construct(ProcTestConfig.class, ito, true);
    }

    // TODO: WebApp may override this
    protected void onTesterInit() {
        Console.println(AnsiPalette.PAINT_DIVIDER, "-----------------------------------------------------------------------------");

        var testCases = procTester.testCases();

        Console.println(AnsiPalette.PAINT_INPUT, "List of pre-defined procedure tests:");
        for (int i = 0; i < testCases.length; i++) {
            Console.print(AnsiPalette.PAINT_INPUT, (i + 1) + ") ");
            Console.println(null, testCases[i]);
        }

        Console.println(AnsiPalette.PAINT_INPUT, "Selection: ");

        var scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            int number = -1;
            try {
                number = Integer.parseInt(line);
            } catch (Exception ignored) {
            }

            if (number < 1 || number > testCases.length) {
                System.err.println("Invalid selection: " + number);
            } else {
                Log.info(Tag.SYSTEM, "Starting predefined procedure test: \"%s\"", testCases[number - 1]);
                procTester.startTestCase(testCases[number - 1]);
            }
        }
    }
}
