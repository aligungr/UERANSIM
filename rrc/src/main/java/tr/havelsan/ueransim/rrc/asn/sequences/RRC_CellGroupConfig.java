/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CellGroupId;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CellGroupConfig__rlc_BearerToAddModList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CellGroupConfig__rlc_BearerToReleaseList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CellGroupConfig__sCellToAddModList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CellGroupConfig__sCellToReleaseList;

public class RRC_CellGroupConfig extends RRC_Sequence {

    public RRC_CellGroupId cellGroupId;
    public RRC_CellGroupConfig__rlc_BearerToAddModList rlc_BearerToAddModList;
    public RRC_CellGroupConfig__rlc_BearerToReleaseList rlc_BearerToReleaseList;
    public RRC_MAC_CellGroupConfig mac_CellGroupConfig;
    public RRC_PhysicalCellGroupConfig physicalCellGroupConfig;
    public RRC_SpCellConfig spCellConfig;
    public RRC_CellGroupConfig__sCellToAddModList sCellToAddModList;
    public RRC_CellGroupConfig__sCellToReleaseList sCellToReleaseList;
    public RRC_CellGroupConfig__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "cellGroupId","rlc-BearerToAddModList","rlc-BearerToReleaseList","mac-CellGroupConfig","physicalCellGroupConfig","spCellConfig","sCellToAddModList","sCellToReleaseList","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "cellGroupId","rlc_BearerToAddModList","rlc_BearerToReleaseList","mac_CellGroupConfig","physicalCellGroupConfig","spCellConfig","sCellToAddModList","sCellToReleaseList","ext1" };
    }

    @Override
    public String getAsnName() {
        return "CellGroupConfig";
    }

    @Override
    public String getXmlTagName() {
        return "CellGroupConfig";
    }

}
