/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_AS_Context__ext2 extends RRC_Sequence {

    public RRC_OctetString ueAssistanceInformation;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ueAssistanceInformation" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ueAssistanceInformation" };
    }

}
