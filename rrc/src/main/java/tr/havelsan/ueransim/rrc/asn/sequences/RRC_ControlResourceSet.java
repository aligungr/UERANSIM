/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_ControlResourceSet__cce_REG_MappingType;
import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ControlResourceSetId;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_ControlResourceSet__tci_StatesPDCCH_ToAddList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_ControlResourceSet__tci_StatesPDCCH_ToReleaseList;

public class RRC_ControlResourceSet extends RRC_Sequence {

    public RRC_ControlResourceSetId controlResourceSetId;
    public RRC_BitString frequencyDomainResources;
    public RRC_Integer duration;
    public RRC_ControlResourceSet__cce_REG_MappingType cce_REG_MappingType;
    public RRC_Integer precoderGranularity;
    public RRC_ControlResourceSet__tci_StatesPDCCH_ToAddList tci_StatesPDCCH_ToAddList;
    public RRC_ControlResourceSet__tci_StatesPDCCH_ToReleaseList tci_StatesPDCCH_ToReleaseList;
    public RRC_Integer tci_PresentInDCI;
    public RRC_Integer pdcch_DMRS_ScramblingID;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "controlResourceSetId","frequencyDomainResources","duration","cce-REG-MappingType","precoderGranularity","tci-StatesPDCCH-ToAddList","tci-StatesPDCCH-ToReleaseList","tci-PresentInDCI","pdcch-DMRS-ScramblingID" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "controlResourceSetId","frequencyDomainResources","duration","cce_REG_MappingType","precoderGranularity","tci_StatesPDCCH_ToAddList","tci_StatesPDCCH_ToReleaseList","tci_PresentInDCI","pdcch_DMRS_ScramblingID" };
    }

    @Override
    public String getAsnName() {
        return "ControlResourceSet";
    }

    @Override
    public String getXmlTagName() {
        return "ControlResourceSet";
    }

}
