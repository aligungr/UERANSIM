/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PLMN_RAN_AreaCell__ran_AreaCells;

public class RRC_PLMN_RAN_AreaCell extends RRC_Sequence {

    public RRC_PLMN_Identity plmn_Identity;
    public RRC_PLMN_RAN_AreaCell__ran_AreaCells ran_AreaCells;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "plmn-Identity","ran-AreaCells" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "plmn_Identity","ran_AreaCells" };
    }

    @Override
    public String getAsnName() {
        return "PLMN-RAN-AreaCell";
    }

    @Override
    public String getXmlTagName() {
        return "PLMN-RAN-AreaCell";
    }

}
