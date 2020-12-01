/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_PDSCH_CodeBlockGroupTransmission;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_PDSCH_ServingCellConfig extends RRC_Sequence {

    public RRC_SetupRelease_PDSCH_CodeBlockGroupTransmission codeBlockGroupTransmission;
    public RRC_Integer xOverhead;
    public RRC_Integer nrofHARQ_ProcessesForPDSCH;
    public RRC_ServCellIndex pucch_Cell;
    public RRC_PDSCH_ServingCellConfig__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "codeBlockGroupTransmission","xOverhead","nrofHARQ-ProcessesForPDSCH","pucch-Cell","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "codeBlockGroupTransmission","xOverhead","nrofHARQ_ProcessesForPDSCH","pucch_Cell","ext1" };
    }

    @Override
    public String getAsnName() {
        return "PDSCH-ServingCellConfig";
    }

    @Override
    public String getXmlTagName() {
        return "PDSCH-ServingCellConfig";
    }

}
