package com.runsim.backend.app.sim;

public class SimulationFlow {
    public final SimulatorSetup setup;
    public final FlowConfig config;
    public final SimulationStep[] steps;
    public final int stepCount;

    public SimulationFlow(SimulatorSetup setup, FlowConfig config, SimulationStep[] steps) {
        this.setup = setup;
        this.config = config;
        this.steps = steps;
        this.stepCount = steps != null ? steps.length : 0;
    }
}