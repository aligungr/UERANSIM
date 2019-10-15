package com.runsim.backend.demo;

import com.runsim.backend.Constants;
import com.runsim.backend.MachineContext;
import com.runsim.backend.MachineController;
import com.runsim.backend.machines.RegistrationFlow;

public class AppServer {
    public static void main(String[] args) throws Exception {
        var controller = new MachineController(RegistrationFlow.class, Constants.AMF_HOST, Constants.AMF_PORT);
        controller.run();
    }
}
