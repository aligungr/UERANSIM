package tr.havelsan.ueransim.app;

import tr.havelsan.ueransim.app.app.UeRanSim;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.console.Console;
import tr.havelsan.ueransim.utils.jcolor.AnsiPalette;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var ueransim = new UeRanSim();

        Utils.sleep(250);

        Console.println(AnsiPalette.PAINT_DIVIDER, "-----------------------------------------------------------------------------");

        var testCases = ueransim.testCaseNames();

        Console.println(AnsiPalette.PAINT_INPUT, "List of possible tests:");
        for (int i = 0; i < testCases.length; i++) {
            Console.print(AnsiPalette.PAINT_INPUT, (i + 1) + ") ");
            Console.println(null, testCases[i]);
        }

        Console.println(AnsiPalette.PAINT_INPUT, "Selection: ");

        var scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        int number = -1;
        try {
            number = Integer.parseInt(line);
        } catch (Exception ignored) {
        }

        if (number < 1 || number > testCases.length) {
            System.err.println("Invalid selection: " + number);
            return;
        }

        ueransim.runTest(testCases[number - 1]);
    }
}
