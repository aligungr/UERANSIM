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
        return "EUTRA-PresenceAntennaPort1";
    }

    @Override
    public String getXmlTagName() {
        return "EUTRA-PresenceAntennaPort1";
    }

}
