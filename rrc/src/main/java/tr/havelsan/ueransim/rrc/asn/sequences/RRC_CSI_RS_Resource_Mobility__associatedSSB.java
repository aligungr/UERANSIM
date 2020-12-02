/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SSB_Index;

public class RRC_CSI_RS_Resource_Mobility__associatedSSB extends RRC_Sequence {

    public RRC_SSB_Index ssb_Index;
    public RRC_Boolean isQuasiColocated;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ssb-Index","isQuasiColocated" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ssb_Index","isQuasiColocated" };
    }

}
