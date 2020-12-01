/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_Q_QualMin;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_Q_RxLevMin;

public class RRC_SIB1__cellSelectionInfo extends RRC_Sequence {

    public RRC_Q_RxLevMin q_RxLevMin;
    public RRC_Integer q_RxLevMinOffset;
    public RRC_Q_RxLevMin q_RxLevMinSUL;
    public RRC_Q_QualMin q_QualMin;
    public RRC_Integer q_QualMinOffset;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "q-RxLevMin","q-RxLevMinOffset","q-RxLevMinSUL","q-QualMin","q-QualMinOffset" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "q_RxLevMin","q_RxLevMinOffset","q_RxLevMinSUL","q_QualMin","q_QualMinOffset" };
    }

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

}
