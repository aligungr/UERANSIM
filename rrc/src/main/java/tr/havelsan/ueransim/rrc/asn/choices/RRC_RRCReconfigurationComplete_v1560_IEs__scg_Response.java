/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;

public class RRC_RRCReconfigurationComplete_v1560_IEs__scg_Response extends RRC_Choice {

    public RRC_OctetString nr_SCG_Response;
    public RRC_OctetString eutra_SCG_Response;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "nr-SCG-Response","eutra-SCG-Response" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "nr_SCG_Response","eutra_SCG_Response" };
    }

}
