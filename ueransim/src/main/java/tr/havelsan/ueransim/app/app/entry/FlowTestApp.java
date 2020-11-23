/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.entry;

import tr.havelsan.ueransim.app.app.AppBuilder;
import tr.havelsan.ueransim.app.app.AppConfig;
import tr.havelsan.ueransim.app.app.tester.ProcedureTester;
import tr.havelsan.ueransim.app.utils.ConfigUtils;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Console;
import tr.havelsan.ueransim.utils.console.Log;
import tr.havelsan.ueransim.utils.jcolor.AnsiPalette;

import java.util.Scanner;

public class FlowTestApp {

    private final ProcedureTester procTester;

    public FlowTestApp() {
        var appConfig = new AppConfig();
        var procTestConfig = ConfigUtils.createProcTestConfig();

        procTester = new ProcedureTester(appConfig);

        var ueransim = new AppBuilder()
                .addMonitor(procTester)
                .build();

        procTester.init(ueransim, procTestConfig, this::onTesterInit);
    }

    public static void main(String[] args) {
        BaseApp.main(args);
        new FlowTestApp();
    }

    // TODO: onTesterREinit tarzı bir şey yapılabilir, seçilen akış tamamen bittiyse her ue için mesela
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
                Log.info(Tag.SYS, "Starting predefined procedure test: \"%s\"", testCases[number - 1]);
                procTester.startTestCase(testCases[number - 1]);
            }
        }
    }
}
