/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Null;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_PDCP_Config__drb__headerCompression__rohc;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_PDCP_Config__drb__headerCompression__uplinkOnlyROHC;

public class RRC_PDCP_Config__drb__headerCompression extends RRC_Choice {

    public RRC_Null notUsed;
    public RRC_PDCP_Config__drb__headerCompression__rohc rohc;
    public RRC_PDCP_Config__drb__headerCompression__uplinkOnlyROHC uplinkOnlyROHC;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "notUsed","rohc","uplinkOnlyROHC" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "notUsed","rohc","uplinkOnlyROHC" };
    }

}
