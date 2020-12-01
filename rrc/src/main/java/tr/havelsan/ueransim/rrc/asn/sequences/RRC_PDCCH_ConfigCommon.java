/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ControlResourceSetZero;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SearchSpaceId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SearchSpaceZero;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PDCCH_ConfigCommon__commonSearchSpaceList;

public class RRC_PDCCH_ConfigCommon extends RRC_Sequence {

    public RRC_ControlResourceSetZero controlResourceSetZero;
    public RRC_ControlResourceSet commonControlResourceSet;
    public RRC_SearchSpaceZero searchSpaceZero;
    public RRC_PDCCH_ConfigCommon__commonSearchSpaceList commonSearchSpaceList;
    public RRC_SearchSpaceId searchSpaceSIB1;
    public RRC_SearchSpaceId searchSpaceOtherSystemInformation;
    public RRC_SearchSpaceId pagingSearchSpace;
    public RRC_SearchSpaceId ra_SearchSpace;
    public RRC_PDCCH_ConfigCommon__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "controlResourceSetZero","commonControlResourceSet","searchSpaceZero","commonSearchSpaceList","searchSpaceSIB1","searchSpaceOtherSystemInformation","pagingSearchSpace","ra-SearchSpace","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "controlResourceSetZero","commonControlResourceSet","searchSpaceZero","commonSearchSpaceList","searchSpaceSIB1","searchSpaceOtherSystemInformation","pagingSearchSpace","ra_SearchSpace","ext1" };
    }

    @Override
    public String getAsnName() {
        return "PDCCH-ConfigCommon";
    }

    @Override
    public String getXmlTagName() {
        return "PDCCH-ConfigCommon";
    }

}
