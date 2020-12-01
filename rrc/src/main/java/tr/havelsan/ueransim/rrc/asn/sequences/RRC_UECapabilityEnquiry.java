/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_UECapabilityEnquiry__criticalExtensions;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RRC_TransactionIdentifier;

public class RRC_UECapabilityEnquiry extends RRC_Sequence {

    public RRC_RRC_TransactionIdentifier rrc_TransactionIdentifier;
    public RRC_UECapabilityEnquiry__criticalExtensions criticalExtensions;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "rrc-TransactionIdentifier","criticalExtensions" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "rrc_TransactionIdentifier","criticalExtensions" };
    }

    @Override
    public String getAsnName() {
        return "UECapabilityEnquiry";
    }

    @Override
    public String getXmlTagName() {
        return "UECapabilityEnquiry";
    }

}
