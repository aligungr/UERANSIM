/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_AffectedCarrierFreqCombEUTRA;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_AffectedCarrierFreqCombNR;

public class RRC_AffectedCarrierFreqCombInfoMRDC__affectedCarrierFreqCombMRDC extends RRC_Sequence {

    public RRC_AffectedCarrierFreqCombEUTRA affectedCarrierFreqCombEUTRA;
    public RRC_AffectedCarrierFreqCombNR affectedCarrierFreqCombNR;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "affectedCarrierFreqCombEUTRA","affectedCarrierFreqCombNR" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "affectedCarrierFreqCombEUTRA","affectedCarrierFreqCombNR" };
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
