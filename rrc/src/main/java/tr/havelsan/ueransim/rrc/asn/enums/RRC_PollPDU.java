/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_PollPDU extends AsnEnumerated {
    public static final RRC_PollPDU P4 = new RRC_PollPDU(0);
    public static final RRC_PollPDU P8 = new RRC_PollPDU(1);
    public static final RRC_PollPDU P16 = new RRC_PollPDU(2);
    public static final RRC_PollPDU P32 = new RRC_PollPDU(3);
    public static final RRC_PollPDU P64 = new RRC_PollPDU(4);
    public static final RRC_PollPDU P128 = new RRC_PollPDU(5);
    public static final RRC_PollPDU P256 = new RRC_PollPDU(6);
    public static final RRC_PollPDU P512 = new RRC_PollPDU(7);
    public static final RRC_PollPDU P1024 = new RRC_PollPDU(8);
    public static final RRC_PollPDU P2048 = new RRC_PollPDU(9);
    public static final RRC_PollPDU P4096 = new RRC_PollPDU(10);
    public static final RRC_PollPDU P6144 = new RRC_PollPDU(11);
    public static final RRC_PollPDU P8192 = new RRC_PollPDU(12);
    public static final RRC_PollPDU P12288 = new RRC_PollPDU(13);
    public static final RRC_PollPDU P16384 = new RRC_PollPDU(14);
    public static final RRC_PollPDU P20480 = new RRC_PollPDU(15);
    public static final RRC_PollPDU P24576 = new RRC_PollPDU(16);
    public static final RRC_PollPDU P28672 = new RRC_PollPDU(17);
    public static final RRC_PollPDU P32768 = new RRC_PollPDU(18);
    public static final RRC_PollPDU P40960 = new RRC_PollPDU(19);
    public static final RRC_PollPDU P49152 = new RRC_PollPDU(20);
    public static final RRC_PollPDU P57344 = new RRC_PollPDU(21);
    public static final RRC_PollPDU P65536 = new RRC_PollPDU(22);
    public static final RRC_PollPDU INFINITY = new RRC_PollPDU(23);
    public static final RRC_PollPDU SPARE8 = new RRC_PollPDU(24);
    public static final RRC_PollPDU SPARE7 = new RRC_PollPDU(25);
    public static final RRC_PollPDU SPARE6 = new RRC_PollPDU(26);
    public static final RRC_PollPDU SPARE5 = new RRC_PollPDU(27);
    public static final RRC_PollPDU SPARE4 = new RRC_PollPDU(28);
    public static final RRC_PollPDU SPARE3 = new RRC_PollPDU(29);
    public static final RRC_PollPDU SPARE2 = new RRC_PollPDU(30);
    public static final RRC_PollPDU SPARE1 = new RRC_PollPDU(31);

    private RRC_PollPDU(long value) {
        super(value);
    }
}

