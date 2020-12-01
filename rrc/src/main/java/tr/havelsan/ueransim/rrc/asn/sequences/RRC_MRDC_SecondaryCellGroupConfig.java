/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_MRDC_SecondaryCellGroupConfig__mrdc_SecondaryCellGroup;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MRDC_SecondaryCellGroupConfig extends RRC_Sequence {

    public RRC_Integer mrdc_ReleaseAndAdd;
    public RRC_MRDC_SecondaryCellGroupConfig__mrdc_SecondaryCellGroup mrdc_SecondaryCellGroup;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "mrdc-ReleaseAndAdd","mrdc-SecondaryCellGroup" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "mrdc_ReleaseAndAdd","mrdc_SecondaryCellGroup" };
    }

    @Override
    public String getAsnName() {
        return "MRDC-SecondaryCellGroupConfig";
    }

    @Override
    public String getXmlTagName() {
        return "MRDC-SecondaryCellGroupConfig";
    }

}
