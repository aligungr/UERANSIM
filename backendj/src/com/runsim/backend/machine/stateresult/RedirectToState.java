package com.runsim.backend.machine.stateresult;

public class RedirectToState extends StateResult {
    private final String redirectState;

    public RedirectToState(String redirectState) {
        this.redirectState = redirectState;
    }

    public String getRedirectState() {
        return redirectState;
    }
}
