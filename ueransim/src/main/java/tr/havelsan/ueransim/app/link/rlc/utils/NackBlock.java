/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.link.rlc.utils;

public class NackBlock {
    public int nackSn;
    public int soStart;

    // (The special SOend value "1111111111111111" is used to indicate that the missing portion
    // of the RLC SDU includes all bytes to the last byte of the RLC SDU.)
    public int soEnd;

    public int nackRange;
}
