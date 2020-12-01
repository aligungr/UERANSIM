/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PhysCellId;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CSI_RS_CellMobility__csi_rs_ResourceList_Mobility;

public class RRC_CSI_RS_CellMobility extends RRC_Sequence {

    public RRC_PhysCellId cellId;
    public RRC_CSI_RS_CellMobility__csi_rs_MeasurementBW csi_rs_MeasurementBW;
    public RRC_Integer density;
    public RRC_CSI_RS_CellMobility__csi_rs_ResourceList_Mobility csi_rs_ResourceList_Mobility;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "cellId","csi-rs-MeasurementBW","density","csi-rs-ResourceList-Mobility" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "cellId","csi_rs_MeasurementBW","density","csi_rs_ResourceList_Mobility" };
    }

    @Override
    public String getAsnName() {
        return "CSI-RS-CellMobility";
    }

    @Override
    public String getXmlTagName() {
        return "CSI-RS-CellMobility";
    }

}
