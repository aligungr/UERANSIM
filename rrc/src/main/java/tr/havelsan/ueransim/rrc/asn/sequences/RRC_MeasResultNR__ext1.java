/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MeasResultNR__ext1 extends RRC_Sequence {

    public RRC_CGI_InfoNR cgi_Info;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "cgi-Info" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "cgi_Info" };
    }

}
