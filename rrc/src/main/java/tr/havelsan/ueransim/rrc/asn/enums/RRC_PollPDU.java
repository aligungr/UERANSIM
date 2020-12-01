/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_PollPDU extends RRC_Enumerated {

    public static final RRC_PollPDU P4 = new RRC_PollPDU("p4");
    public static final RRC_PollPDU P8 = new RRC_PollPDU("p8");
    public static final RRC_PollPDU P16 = new RRC_PollPDU("p16");
    public static final RRC_PollPDU P32 = new RRC_PollPDU("p32");
    public static final RRC_PollPDU P64 = new RRC_PollPDU("p64");
    public static final RRC_PollPDU P128 = new RRC_PollPDU("p128");
    public static final RRC_PollPDU P256 = new RRC_PollPDU("p256");
    public static final RRC_PollPDU P512 = new RRC_PollPDU("p512");
    public static final RRC_PollPDU P1024 = new RRC_PollPDU("p1024");
    public static final RRC_PollPDU P2048 = new RRC_PollPDU("p2048");
    public static final RRC_PollPDU P4096 = new RRC_PollPDU("p4096");
    public static final RRC_PollPDU P6144 = new RRC_PollPDU("p6144");
    public static final RRC_PollPDU P8192 = new RRC_PollPDU("p8192");
    public static final RRC_PollPDU P12288 = new RRC_PollPDU("p12288");
    public static final RRC_PollPDU P16384 = new RRC_PollPDU("p16384");
    public static final RRC_PollPDU P20480 = new RRC_PollPDU("p20480");
    public static final RRC_PollPDU P24576 = new RRC_PollPDU("p24576");
    public static final RRC_PollPDU P28672 = new RRC_PollPDU("p28672");
    public static final RRC_PollPDU P32768 = new RRC_PollPDU("p32768");
    public static final RRC_PollPDU P40960 = new RRC_PollPDU("p40960");
    public static final RRC_PollPDU P49152 = new RRC_PollPDU("p49152");
    public static final RRC_PollPDU P57344 = new RRC_PollPDU("p57344");
    public static final RRC_PollPDU P65536 = new RRC_PollPDU("p65536");
    public static final RRC_PollPDU INFINITY = new RRC_PollPDU("infinity");
    public static final RRC_PollPDU SPARE8 = new RRC_PollPDU("spare8");
    public static final RRC_PollPDU SPARE7 = new RRC_PollPDU("spare7");
    public static final RRC_PollPDU SPARE6 = new RRC_PollPDU("spare6");
    public static final RRC_PollPDU SPARE5 = new RRC_PollPDU("spare5");
    public static final RRC_PollPDU SPARE4 = new RRC_PollPDU("spare4");
    public static final RRC_PollPDU SPARE3 = new RRC_PollPDU("spare3");
    public static final RRC_PollPDU SPARE2 = new RRC_PollPDU("spare2");
    public static final RRC_PollPDU SPARE1 = new RRC_PollPDU("spare1");

    protected RRC_PollPDU(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "PollPDU";
    }

    @Override
    public String getXmlTagName() {
        return "PollPDU";
    }

}
