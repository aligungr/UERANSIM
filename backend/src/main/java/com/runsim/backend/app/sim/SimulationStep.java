package com.runsim.backend.app.sim;

import com.runsim.backend.nas.core.messages.NasMessage;

public class SimulationStep {
    public final NasMessage nasMessage;

    public SimulationStep(NasMessage nasMessage) {
        this.nasMessage = nasMessage;
    }
}
