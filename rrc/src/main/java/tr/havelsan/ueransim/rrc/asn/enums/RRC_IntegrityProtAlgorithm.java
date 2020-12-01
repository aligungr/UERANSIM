/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_IntegrityProtAlgorithm extends RRC_Enumerated {

    public static final RRC_IntegrityProtAlgorithm NIA0 = new RRC_IntegrityProtAlgorithm("nia0");
    public static final RRC_IntegrityProtAlgorithm NIA1 = new RRC_IntegrityProtAlgorithm("nia1");
    public static final RRC_IntegrityProtAlgorithm NIA2 = new RRC_IntegrityProtAlgorithm("nia2");
    public static final RRC_IntegrityProtAlgorithm NIA3 = new RRC_IntegrityProtAlgorithm("nia3");
    public static final RRC_IntegrityProtAlgorithm SPARE4 = new RRC_IntegrityProtAlgorithm("spare4");
    public static final RRC_IntegrityProtAlgorithm SPARE3 = new RRC_IntegrityProtAlgorithm("spare3");
    public static final RRC_IntegrityProtAlgorithm SPARE2 = new RRC_IntegrityProtAlgorithm("spare2");
    public static final RRC_IntegrityProtAlgorithm SPARE1 = new RRC_IntegrityProtAlgorithm("spare1");

    protected RRC_IntegrityProtAlgorithm(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "IntegrityProtAlgorithm";
    }

    @Override
    public String getXmlTagName() {
        return "IntegrityProtAlgorithm";
    }

}
