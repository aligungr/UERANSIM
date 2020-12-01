/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CGI_InfoEUTRA__cgi_info_EPC__cgi_info_EPC_list;

public class RRC_CGI_InfoEUTRA__cgi_info_EPC extends RRC_Sequence {

    public RRC_CellAccessRelatedInfo_EUTRA_EPC cgi_info_EPC_legacy;
    public RRC_CGI_InfoEUTRA__cgi_info_EPC__cgi_info_EPC_list cgi_info_EPC_list;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "cgi-info-EPC-legacy","cgi-info-EPC-list" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "cgi_info_EPC_legacy","cgi_info_EPC_list" };
    }

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

}
