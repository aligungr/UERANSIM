/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.link.rlc.utils;

public class MissingBlock {

    // Start SN and SO of the missing block
    public int snStart;
    public int soStart;

    // End SN and SO of the missing block
    public int snEnd;
    public int soEnd;

    // After finding this missing block, these SN and SO values indicate the resuming point of finding the
    // next missing block. If no resume to do, these values are set to -1.
    public int snNext;
    public int soNext;
}
