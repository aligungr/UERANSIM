/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_PDCP_Config__drb__headerCompression__uplinkOnlyROHC__profiles extends RRC_Sequence {

    public RRC_Boolean profile0x0006;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "profile0x0006" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "profile0x0006" };
    }

}
