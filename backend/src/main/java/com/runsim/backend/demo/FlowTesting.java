package com.runsim.backend.demo;

import com.runsim.backend.flows.RegistrationFlow;

public class FlowTesting {
    public static void main(String[] args) throws Exception {
        var flow = new RegistrationFlow();
        flow.start();
    }
}
