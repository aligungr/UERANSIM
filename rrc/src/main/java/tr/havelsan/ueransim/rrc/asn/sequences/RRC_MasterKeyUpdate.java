/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;
import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_NextHopChainingCount;

public class RRC_MasterKeyUpdate extends RRC_Sequence {

    public RRC_Boolean keySetChangeIndicator;
    public RRC_NextHopChainingCount nextHopChainingCount;
    public RRC_OctetString nas_Container;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "keySetChangeIndicator","nextHopChainingCount","nas-Container" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "keySetChangeIndicator","nextHopChainingCount","nas_Container" };
    }

    @Override
    public String getAsnName() {
        return "MasterKeyUpdate";
    }

    @Override
    public String getXmlTagName() {
        return "MasterKeyUpdate";
    }

}
