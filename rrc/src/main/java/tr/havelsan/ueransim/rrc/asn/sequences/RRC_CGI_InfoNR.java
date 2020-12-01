/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MultiFrequencyBandListNR;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PLMN_IdentityInfoList;

public class RRC_CGI_InfoNR extends RRC_Sequence {

    public RRC_PLMN_IdentityInfoList plmn_IdentityInfoList;
    public RRC_MultiFrequencyBandListNR frequencyBandList;
    public RRC_CGI_InfoNR__noSIB1 noSIB1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "plmn-IdentityInfoList","frequencyBandList","noSIB1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "plmn_IdentityInfoList","frequencyBandList","noSIB1" };
    }

    @Override
    public String getAsnName() {
        return "CGI-InfoNR";
    }

    @Override
    public String getXmlTagName() {
        return "CGI-InfoNR";
    }

}
