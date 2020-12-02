/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_SRS_Resource__freqHopping extends RRC_Sequence {

    public RRC_Integer c_SRS;
    public RRC_Integer b_SRS;
    public RRC_Integer b_hop;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "c-SRS","b-SRS","b-hop" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "c_SRS","b_SRS","b_hop" };
    }

}
