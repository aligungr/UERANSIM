/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_SRS_TPC_PDCCH_Config__srs_CC_SetIndexlist;

public class RRC_SRS_TPC_PDCCH_Config extends RRC_Sequence {

    public RRC_SRS_TPC_PDCCH_Config__srs_CC_SetIndexlist srs_CC_SetIndexlist;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "srs-CC-SetIndexlist" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "srs_CC_SetIndexlist" };
    }

    @Override
    public String getAsnName() {
        return "SRS-TPC-PDCCH-Config";
    }

    @Override
    public String getXmlTagName() {
        return "SRS-TPC-PDCCH-Config";
    }

}
