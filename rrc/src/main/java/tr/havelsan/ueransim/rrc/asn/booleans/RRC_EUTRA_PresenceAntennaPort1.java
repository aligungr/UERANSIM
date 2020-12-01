/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.booleans;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;

public class RRC_EUTRA_PresenceAntennaPort1 extends RRC_Boolean {

    public RRC_EUTRA_PresenceAntennaPort1(boolean value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

}
