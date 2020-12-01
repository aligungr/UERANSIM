/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_Phy_ParametersFRX_Diff__ext2 extends RRC_Sequence {

    public RRC_Phy_ParametersFRX_Diff__ext2__pdcch_BlindDetectionNRDC pdcch_BlindDetectionNRDC;
    public RRC_Integer mux_HARQ_ACK_PUSCH_DiffSymbol;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "pdcch-BlindDetectionNRDC","mux-HARQ-ACK-PUSCH-DiffSymbol" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "pdcch_BlindDetectionNRDC","mux_HARQ_ACK_PUSCH_DiffSymbol" };
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
