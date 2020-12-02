/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_NZP_CSI_RS_ResourceId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SSB_Index;

public class RRC_QCL_Info__referenceSignal extends RRC_Choice {

    public RRC_NZP_CSI_RS_ResourceId csi_rs;
    public RRC_SSB_Index ssb;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "csi-rs","ssb" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "csi_rs","ssb" };
    }

}
