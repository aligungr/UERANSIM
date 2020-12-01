/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_CipheringAlgorithm extends RRC_Enumerated {

    public static final RRC_CipheringAlgorithm NEA0 = new RRC_CipheringAlgorithm("nea0");
    public static final RRC_CipheringAlgorithm NEA1 = new RRC_CipheringAlgorithm("nea1");
    public static final RRC_CipheringAlgorithm NEA2 = new RRC_CipheringAlgorithm("nea2");
    public static final RRC_CipheringAlgorithm NEA3 = new RRC_CipheringAlgorithm("nea3");
    public static final RRC_CipheringAlgorithm SPARE4 = new RRC_CipheringAlgorithm("spare4");
    public static final RRC_CipheringAlgorithm SPARE3 = new RRC_CipheringAlgorithm("spare3");
    public static final RRC_CipheringAlgorithm SPARE2 = new RRC_CipheringAlgorithm("spare2");
    public static final RRC_CipheringAlgorithm SPARE1 = new RRC_CipheringAlgorithm("spare1");

    protected RRC_CipheringAlgorithm(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "CipheringAlgorithm";
    }

    @Override
    public String getXmlTagName() {
        return "CipheringAlgorithm";
    }

}
