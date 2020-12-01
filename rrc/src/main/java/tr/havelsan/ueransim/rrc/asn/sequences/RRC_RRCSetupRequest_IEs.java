/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_InitialUE_Identity;
import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_EstablishmentCause;

public class RRC_RRCSetupRequest_IEs extends RRC_Sequence {

    public RRC_InitialUE_Identity ue_Identity;
    public RRC_EstablishmentCause establishmentCause;
    public RRC_BitString spare;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ue-Identity","establishmentCause","spare" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ue_Identity","establishmentCause","spare" };
    }

    @Override
    public String getAsnName() {
        return "RRCSetupRequest-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "RRCSetupRequest-IEs";
    }

}
