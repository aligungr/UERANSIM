/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MAC_Parameters extends RRC_Sequence {

    public RRC_MAC_ParametersCommon mac_ParametersCommon;
    public RRC_MAC_ParametersXDD_Diff mac_ParametersXDD_Diff;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "mac-ParametersCommon","mac-ParametersXDD-Diff" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "mac_ParametersCommon","mac_ParametersXDD_Diff" };
    }

    @Override
    public String getAsnName() {
        return "MAC-Parameters";
    }

    @Override
    public String getXmlTagName() {
        return "MAC-Parameters";
    }

}
