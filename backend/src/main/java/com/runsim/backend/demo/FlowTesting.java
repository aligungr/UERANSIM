package com.runsim.backend.demo;

import com.runsim.backend.flows.TestFlow;

public class FlowTesting {
    public static void main(String[] args) throws Exception {
        var flow = new TestFlow();
        flow.start();
    }
}
