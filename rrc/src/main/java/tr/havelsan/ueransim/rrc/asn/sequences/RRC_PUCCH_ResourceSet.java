/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PUCCH_ResourceSetId;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PUCCH_ResourceSet__resourceList;

public class RRC_PUCCH_ResourceSet extends RRC_Sequence {

    public RRC_PUCCH_ResourceSetId pucch_ResourceSetId;
    public RRC_PUCCH_ResourceSet__resourceList resourceList;
    public RRC_Integer maxPayloadMinus1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "pucch-ResourceSetId","resourceList","maxPayloadMinus1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "pucch_ResourceSetId","resourceList","maxPayloadMinus1" };
    }

    @Override
    public String getAsnName() {
        return "PUCCH-ResourceSet";
    }

    @Override
    public String getXmlTagName() {
        return "PUCCH-ResourceSet";
    }

}
