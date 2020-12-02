/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_SRS_CarrierSwitching__srs_TPC_PDCCH_Group__typeA;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_SRS_TPC_PDCCH_Config;

public class RRC_SRS_CarrierSwitching__srs_TPC_PDCCH_Group extends RRC_Choice {

    public RRC_SRS_CarrierSwitching__srs_TPC_PDCCH_Group__typeA typeA;
    public RRC_SRS_TPC_PDCCH_Config typeB;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "typeA","typeB" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "typeA","typeB" };
    }

}
