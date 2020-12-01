/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_PUCCH_PathlossReferenceRS__referenceSignal;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PUCCH_PathlossReferenceRS_Id;

public class RRC_PUCCH_PathlossReferenceRS extends RRC_Sequence {

    public RRC_PUCCH_PathlossReferenceRS_Id pucch_PathlossReferenceRS_Id;
    public RRC_PUCCH_PathlossReferenceRS__referenceSignal referenceSignal;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "pucch-PathlossReferenceRS-Id","referenceSignal" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "pucch_PathlossReferenceRS_Id","referenceSignal" };
    }

    @Override
    public String getAsnName() {
        return "PUCCH-PathlossReferenceRS";
    }

    @Override
    public String getXmlTagName() {
        return "PUCCH-PathlossReferenceRS";
    }

}
