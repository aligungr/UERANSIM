/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_RLC_Parameters extends RRC_Sequence {

    public RRC_Integer am_WithShortSN;
    public RRC_Integer um_WithShortSN;
    public RRC_Integer um_WithLongSN;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "am-WithShortSN","um-WithShortSN","um-WithLongSN" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "am_WithShortSN","um_WithShortSN","um_WithLongSN" };
    }

    @Override
    public String getAsnName() {
        return "RLC-Parameters";
    }

    @Override
    public String getXmlTagName() {
        return "RLC-Parameters";
    }

}
