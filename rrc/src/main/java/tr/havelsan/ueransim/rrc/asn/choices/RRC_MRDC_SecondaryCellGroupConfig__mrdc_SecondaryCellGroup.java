/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;

public class RRC_MRDC_SecondaryCellGroupConfig__mrdc_SecondaryCellGroup extends RRC_Choice {

    public RRC_OctetString nr_SCG;
    public RRC_OctetString eutra_SCG;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "nr-SCG","eutra-SCG" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "nr_SCG","eutra_SCG" };
    }

}
