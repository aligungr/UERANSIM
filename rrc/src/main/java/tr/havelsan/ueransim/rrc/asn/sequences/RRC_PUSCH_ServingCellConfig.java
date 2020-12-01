/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_PUSCH_CodeBlockGroupTransmission;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_PUSCH_ServingCellConfig extends RRC_Sequence {

    public RRC_SetupRelease_PUSCH_CodeBlockGroupTransmission codeBlockGroupTransmission;
    public RRC_Integer rateMatching;
    public RRC_Integer xOverhead;
    public RRC_PUSCH_ServingCellConfig__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "codeBlockGroupTransmission","rateMatching","xOverhead","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "codeBlockGroupTransmission","rateMatching","xOverhead","ext1" };
    }

    @Override
    public String getAsnName() {
        return "PUSCH-ServingCellConfig";
    }

    @Override
    public String getXmlTagName() {
        return "PUSCH-ServingCellConfig";
    }

}
