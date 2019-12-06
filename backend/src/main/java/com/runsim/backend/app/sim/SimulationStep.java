package com.runsim.backend.app.sim;

import com.google.gson.JsonElement;
import com.runsim.backend.nas.core.messages.NasMessage;

public class SimulationStep {
    public final String ngapType;
    public final Object[] ngapIEs;
    public final NasMessage nasMessage;

    public SimulationStep(String ngapType, JsonElement[] ngapIEs, NasMessage nasMessage) {
        this.ngapType = ngapType;
        this.ngapIEs = ngapIEs;
        this.nasMessage = nasMessage;
    }
}
