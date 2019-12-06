package com.runsim.backend.app.sim;

import com.runsim.backend.nas.core.messages.NasMessage;

public class SimulationStep {
    public final NasMessage nas;

    public SimulationStep(NasMessage nas) {
        this.nas = nas;
    }
}
