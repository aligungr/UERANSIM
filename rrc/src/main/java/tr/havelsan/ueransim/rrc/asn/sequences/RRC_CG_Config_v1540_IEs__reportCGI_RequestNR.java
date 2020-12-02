/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CG_Config_v1540_IEs__reportCGI_RequestNR extends RRC_Sequence {

    public RRC_CG_Config_v1540_IEs__reportCGI_RequestNR__requestedCellInfo requestedCellInfo;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "requestedCellInfo" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "requestedCellInfo" };
    }

}
