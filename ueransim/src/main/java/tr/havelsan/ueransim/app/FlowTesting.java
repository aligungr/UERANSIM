package tr.havelsan.ueransim.app;

import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;

import java.util.Arrays;
import java.util.Scanner;

public class FlowTesting {

    public static void main(String[] args) throws Exception {
        /*var h1 = "00150029000003001b0009000001105000000001006600100000000075000001100000100809afaf0015400120".toUpperCase();

        var h2 = Utils.byteArrayToHexString(
                Ngap.perEncode(UeUtils.createNgSetupRequest())
        );

        var v1 = Ngap.perDecode(NGAP_PDU.class, Utils.hexStringToByteArray(h1));
        var v2 = Ngap.perDecode(NGAP_PDU.class, Utils.hexStringToByteArray(h2));


        if ((1 + 1 + "").toString() != null)
            return;

*/


        Console.println(Color.BLUE, "Select a flow:");

        var flowNames = Backend.getFlowNames().toArray(new String[0]);
        Arrays.sort(flowNames);

        for (int i = 0; i < flowNames.length; i++) {
            Console.print(Color.BLUE, i + 1 + ") ");
            Console.println(flowNames[i]);
        }

        Console.print(Color.BLUE, "Selection: ");

        var scanner = new Scanner(System.in);
        int selection = scanner.nextInt();

        var flowName = flowNames[selection - 1];
        var flow = Backend.getFlowType(flowName);
        flow.getConstructor().newInstance().start();
    }
}
