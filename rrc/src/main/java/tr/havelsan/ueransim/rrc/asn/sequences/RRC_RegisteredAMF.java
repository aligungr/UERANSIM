/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_AMF_Identifier;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_RegisteredAMF extends RRC_Sequence {

    public RRC_PLMN_Identity plmn_Identity;
    public RRC_AMF_Identifier amf_Identifier;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "plmn-Identity","amf-Identifier" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "plmn_Identity","amf_Identifier" };
    }

    @Override
    public String getAsnName() {
        return "RegisteredAMF";
    }

    @Override
    public String getXmlTagName() {
        return "RegisteredAMF";
    }

}
