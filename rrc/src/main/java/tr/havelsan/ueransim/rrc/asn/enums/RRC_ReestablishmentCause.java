/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_ReestablishmentCause extends RRC_Enumerated {

    public static final RRC_ReestablishmentCause RECONFIGURATIONFAILURE = new RRC_ReestablishmentCause("reconfigurationFailure");
    public static final RRC_ReestablishmentCause HANDOVERFAILURE = new RRC_ReestablishmentCause("handoverFailure");
    public static final RRC_ReestablishmentCause OTHERFAILURE = new RRC_ReestablishmentCause("otherFailure");
    public static final RRC_ReestablishmentCause SPARE1 = new RRC_ReestablishmentCause("spare1");

    protected RRC_ReestablishmentCause(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "ReestablishmentCause";
    }

    @Override
    public String getXmlTagName() {
        return "ReestablishmentCause";
    }

}
