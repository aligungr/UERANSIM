package com.runsim.backend.web;

@FunctionalInterface
public interface ISender {
    void send(String type, Object element);
}
