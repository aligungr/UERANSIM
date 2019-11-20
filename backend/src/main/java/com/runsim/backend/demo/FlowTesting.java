package com.runsim.backend.demo;

import com.runsim.backend.App;
import com.runsim.backend.utils.Color;
import com.runsim.backend.utils.Console;

import java.util.Arrays;
import java.util.Scanner;

public class FlowTesting {

    public static void main(String[] args) throws Exception {
        Console.println(Color.BLUE, "Select a flow:");

        var flowNames = App.getFlowNames().toArray(new String[0]);
        Arrays.sort(flowNames);

        for (int i = 0; i < flowNames.length; i++) {
            Console.print(Color.BLUE, i + 1 + ") ");
            Console.println(Color.RESET, flowNames[i]);
        }

        Console.print(Color.BLUE, "Selection: ");

        var scanner = new Scanner(System.in);
        int selection = scanner.nextInt();

        var flowName = flowNames[selection - 1];
        var flow = App.getFlowType(flowName);
        flow.getConstructor().newInstance().start();
    }
}
