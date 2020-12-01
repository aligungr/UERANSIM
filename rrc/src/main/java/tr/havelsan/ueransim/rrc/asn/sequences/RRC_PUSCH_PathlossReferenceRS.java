/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_PUSCH_PathlossReferenceRS__referenceSignal;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PUSCH_PathlossReferenceRS_Id;

public class RRC_PUSCH_PathlossReferenceRS extends RRC_Sequence {

    public RRC_PUSCH_PathlossReferenceRS_Id pusch_PathlossReferenceRS_Id;
    public RRC_PUSCH_PathlossReferenceRS__referenceSignal referenceSignal;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "pusch-PathlossReferenceRS-Id","referenceSignal" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "pusch_PathlossReferenceRS_Id","referenceSignal" };
    }

    @Override
    public String getAsnName() {
        return "PUSCH-PathlossReferenceRS";
    }

    @Override
    public String getXmlTagName() {
        return "PUSCH-PathlossReferenceRS";
    }

}
