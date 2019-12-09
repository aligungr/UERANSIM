package com.runsim.backend.app.sim;

public class SimulationFlow {
    public final SimulatorSetup setup;
    public final FlowConfig config;
    public final SimulationStep[] steps;
    public final int stepCount;
    public final String[] passOnReceive;
    public final String[] failOnReceive;

    public SimulationFlow(SimulatorSetup setup, FlowConfig config, SimulationStep[] steps, String[] passOnReceive, String[] failOnReceive) {
        this.setup = setup;
        this.config = config;
        this.steps = steps;
        this.stepCount = steps != null ? steps.length : 0;
        this.passOnReceive = passOnReceive == null ? new String[0] : passOnReceive;
        this.failOnReceive = failOnReceive == null ? new String[0] : failOnReceive;
    }
}