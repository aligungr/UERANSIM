/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_SIB1;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_SystemInformation;

public class RRC_BCCH_DL_SCH_MessageType__c1 extends RRC_Choice {

    public RRC_SystemInformation systemInformation;
    public RRC_SIB1 systemInformationBlockType1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "systemInformation","systemInformationBlockType1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "systemInformation","systemInformationBlockType1" };
    }

}
