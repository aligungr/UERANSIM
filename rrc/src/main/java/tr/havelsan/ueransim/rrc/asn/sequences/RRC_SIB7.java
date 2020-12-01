/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_SIB7 extends RRC_Sequence {

    public RRC_BitString messageIdentifier;
    public RRC_BitString serialNumber;
    public RRC_Integer warningMessageSegmentType;
    public RRC_Integer warningMessageSegmentNumber;
    public RRC_OctetString warningMessageSegment;
    public RRC_OctetString dataCodingScheme;
    public RRC_OctetString lateNonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "messageIdentifier","serialNumber","warningMessageSegmentType","warningMessageSegmentNumber","warningMessageSegment","dataCodingScheme","lateNonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "messageIdentifier","serialNumber","warningMessageSegmentType","warningMessageSegmentNumber","warningMessageSegment","dataCodingScheme","lateNonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "SIB7";
    }

    @Override
    public String getXmlTagName() {
        return "SIB7";
    }

}
