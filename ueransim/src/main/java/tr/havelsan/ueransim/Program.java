package tr.havelsan.ueransim;

import tr.havelsan.ueransim.mts.MtsInitializer;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;

public class Program {

    public static void main(String[] args) {
        MtsInitializer.initMts();
        Console.addPrintHandler(str -> {

        });
        Console.println(Color.BLUE, "dsadassad");
    }
}
