/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SRS_CarrierSwitching__srs_TPC_PDCCH_Group;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_SRS_CarrierSwitching__monitoringCells;

public class RRC_SRS_CarrierSwitching extends RRC_Sequence {

    public RRC_Integer srs_SwitchFromServCellIndex;
    public RRC_Integer srs_SwitchFromCarrier;
    public RRC_SRS_CarrierSwitching__srs_TPC_PDCCH_Group srs_TPC_PDCCH_Group;
    public RRC_SRS_CarrierSwitching__monitoringCells monitoringCells;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "srs-SwitchFromServCellIndex","srs-SwitchFromCarrier","srs-TPC-PDCCH-Group","monitoringCells" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "srs_SwitchFromServCellIndex","srs_SwitchFromCarrier","srs_TPC_PDCCH_Group","monitoringCells" };
    }

    @Override
    public String getAsnName() {
        return "SRS-CarrierSwitching";
    }

    @Override
    public String getXmlTagName() {
        return "SRS-CarrierSwitching";
    }

}
