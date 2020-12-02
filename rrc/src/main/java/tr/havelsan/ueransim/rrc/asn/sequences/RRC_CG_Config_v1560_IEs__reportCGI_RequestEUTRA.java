/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CG_Config_v1560_IEs__reportCGI_RequestEUTRA extends RRC_Sequence {

    public RRC_CG_Config_v1560_IEs__reportCGI_RequestEUTRA__requestedCellInfoEUTRA requestedCellInfoEUTRA;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "requestedCellInfoEUTRA" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "requestedCellInfoEUTRA" };
    }

}
