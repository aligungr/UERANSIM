package com.runsim.backend.utils.octets;

/**
 * Represents 1-octet or 8-bit unsigned integer
 */
public class Octet extends OctetN {

    public Octet(int value) {
        super(value, 1);
    }
}
