package com.runsim.backend.demo;

import com.runsim.backend.protocols.nas.NASDecoder;
import com.runsim.backend.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class FlowTesting {
    private static String enumify(String display) {
        String res = display.replace(" ", "_");
        if (res.contains("(") && !res.endsWith(")")) res += ")";

        String replacing = ".,-_+/*&/\\";

        for (int i = 0; i < replacing.length(); i++)
            res = res.replace(replacing.charAt(i) + "", "_");
        res = res.replace("\"", "");
        res = res.replace("'", "");
        res = res.replace(":", "");
        res = res.replace("!", "");
        res = res.toUpperCase(Locale.ENGLISH);

        while (res.contains("__"))
            res = res.replace("__", "_");
        if (res.contains("("))
            res = res.substring(0, res.indexOf("("));
        if (res.contains("["))
            res = res.substring(0, res.indexOf("["));

        if (res.startsWith("_"))
            res = res.substring(1);
        if (res.endsWith("_"))
            res = res.substring(0, res.length() - 1);

        if (res.length() > 0 && Character.isDigit(res.charAt(0))) {
            int start = 0;
            while (start < res.length()) {
                if (!Character.isDigit(res.charAt(start)))
                    break;
                start++;
            }
            String numPart = res.substring(0, start);
            String rem = res.substring(start);
            if (rem.length() > 0 && rem.charAt(0) != '_')
                rem = '_' + rem;
            res = readNumber(numPart) + rem;
        }

        return res;
    }

    private static String readNumber(String numPart) {
        String[] cc = new String[]{
                "ZERO", "ONE", "TWO", "THREE", "FOR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"
        };
        var sb = new StringBuilder();
        for (int i = 0; i < numPart.length(); i++) {
            int c = numPart.charAt(i) - '0';
            sb.append(cc[c]);
        }
        return sb.toString();
    }

    private static String enumifyAll(Object[][] objs) {
        String[] names = new String[objs.length];
        int[] values = new int[objs.length];

        for (int i = 0; i < objs.length; i++) {
            names[i] = (String) objs[i][1];
            values[i] = (int) objs[i][0];
        }

        var res = new ArrayList<String>();
        var totalCounts = new HashMap<String, Integer>();
        var currentCounts = new HashMap<String, Integer>();

        for (String line : names) {
            String enumedName = enumify(line);
            if (totalCounts.containsKey(enumedName)) {
                totalCounts.put(enumedName, totalCounts.get(enumedName) + 1);
            } else {
                totalCounts.put(enumedName, 1);
            }
        }

        for (String line : names) {
            String enumedName = enumify(line);
            if (currentCounts.containsKey(enumedName)) {
                currentCounts.put(enumedName, currentCounts.get(enumedName) + 1);
            } else {
                currentCounts.put(enumedName, 1);
            }

            int current = currentCounts.get(enumedName);
            if (totalCounts.get(enumedName) == 1) {
                res.add(enumedName);
            } else {
                res.add(enumedName + current);
            }
        }

        var sb = new StringBuilder();
        for (int i = 0; i < objs.length; i++) {
            sb.append("public static final XXX " + res.get(i) + " = new XXX(" + values[i] + ", \"" + names[i] + "\");").append("\n");
        }

        return sb.toString().trim();
    }

    public static void main(String[] args) throws Exception {
        /*Object[][] xxxx = {
        };



        var s = (enumifyAll(xxxx));*/
        // NAS PDU
        var test = "7e005778003002010030320100000305000864955b0fe729127b00000000000000000b05000069f5f2af9798323126ef3cf8896a8c4b";
        var data = Utils.hexStringToByteArray(test);
        var pdu = new NASDecoder(data).decodeNAS();
        System.out.println("-");

        /*var flow = new RegistrationFlow();
        flow.start();*/
    }
}
