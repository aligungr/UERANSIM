/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_SIB6 extends RRC_Sequence {

    public RRC_BitString messageIdentifier;
    public RRC_BitString serialNumber;
    public RRC_OctetString warningType;
    public RRC_OctetString lateNonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "messageIdentifier","serialNumber","warningType","lateNonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "messageIdentifier","serialNumber","warningType","lateNonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "SIB6";
    }

    @Override
    public String getXmlTagName() {
        return "SIB6";
    }

}
