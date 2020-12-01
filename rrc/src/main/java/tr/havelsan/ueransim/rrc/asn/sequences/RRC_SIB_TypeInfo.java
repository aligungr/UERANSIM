/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_SIB_TypeInfo extends RRC_Sequence {

    public RRC_Integer type;
    public RRC_Integer valueTag;
    public RRC_Integer areaScope;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "type","valueTag","areaScope" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "type","valueTag","areaScope" };
    }

    @Override
    public String getAsnName() {
        return "SIB-TypeInfo";
    }

    @Override
    public String getXmlTagName() {
        return "SIB-TypeInfo";
    }

}
