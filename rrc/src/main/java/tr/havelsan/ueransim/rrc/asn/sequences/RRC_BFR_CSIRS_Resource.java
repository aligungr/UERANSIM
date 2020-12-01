/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_NZP_CSI_RS_ResourceId;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_BFR_CSIRS_Resource__ra_OccasionList;

public class RRC_BFR_CSIRS_Resource extends RRC_Sequence {

    public RRC_NZP_CSI_RS_ResourceId csi_RS;
    public RRC_BFR_CSIRS_Resource__ra_OccasionList ra_OccasionList;
    public RRC_Integer ra_PreambleIndex;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "csi-RS","ra-OccasionList","ra-PreambleIndex" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "csi_RS","ra_OccasionList","ra_PreambleIndex" };
    }

    @Override
    public String getAsnName() {
        return "BFR-CSIRS-Resource";
    }

    @Override
    public String getXmlTagName() {
        return "BFR-CSIRS-Resource";
    }

}
