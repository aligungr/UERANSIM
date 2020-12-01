/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_SI_RequestResources extends RRC_Sequence {

    public RRC_Integer ra_PreambleStartIndex;
    public RRC_Integer ra_AssociationPeriodIndex;
    public RRC_Integer ra_ssb_OccasionMaskIndex;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ra-PreambleStartIndex","ra-AssociationPeriodIndex","ra-ssb-OccasionMaskIndex" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ra_PreambleStartIndex","ra_AssociationPeriodIndex","ra_ssb_OccasionMaskIndex" };
    }

    @Override
    public String getAsnName() {
        return "SI-RequestResources";
    }

    @Override
    public String getXmlTagName() {
        return "SI-RequestResources";
    }

}
