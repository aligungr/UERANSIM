/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_SRS_TPC_PDCCH_Config;

public class RRC_SRS_CarrierSwitching__srs_TPC_PDCCH_Group__typeA extends RRC_SequenceOf<RRC_SRS_TPC_PDCCH_Config> {

    @Override
    public Class<RRC_SRS_TPC_PDCCH_Config> getItemType() {
        return RRC_SRS_TPC_PDCCH_Config.class;
    }

}
