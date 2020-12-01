/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_ControlResourceSet__cce_REG_MappingType__interleaved extends RRC_Sequence {

    public RRC_Integer reg_BundleSize;
    public RRC_Integer interleaverSize;
    public RRC_Integer shiftIndex;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "reg-BundleSize","interleaverSize","shiftIndex" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "reg_BundleSize","interleaverSize","shiftIndex" };
    }

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

}
