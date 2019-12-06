package com.runsim.backend.app.sim;

public class SimulationFlow {
    public final SimulationConfig config;
    public final SimulationStep[] steps;

    public SimulationFlow(SimulationConfig config, SimulationStep[] steps) {
        this.config = config;
        this.steps = steps;
    }
}