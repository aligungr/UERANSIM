package com.runsim.backend.app.sim;

import com.runsim.backend.nas.core.messages.NasMessage;

public class SimulationStep {
    public final String ngapType;
    public final NasMessage nasMessage;

    public SimulationStep(String ngapType, NasMessage nasMessage) {
        this.ngapType = ngapType;
        this.nasMessage = nasMessage;
    }
}
