package com.runsim.backend.web;

@FunctionalInterface
public interface ISender {
    void send(Object message);
}
