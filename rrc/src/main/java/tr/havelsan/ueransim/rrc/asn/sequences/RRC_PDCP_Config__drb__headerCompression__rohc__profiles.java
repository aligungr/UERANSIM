/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_PDCP_Config__drb__headerCompression__rohc__profiles extends RRC_Sequence {

    public RRC_Boolean profile0x0001;
    public RRC_Boolean profile0x0002;
    public RRC_Boolean profile0x0003;
    public RRC_Boolean profile0x0004;
    public RRC_Boolean profile0x0006;
    public RRC_Boolean profile0x0101;
    public RRC_Boolean profile0x0102;
    public RRC_Boolean profile0x0103;
    public RRC_Boolean profile0x0104;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "profile0x0001","profile0x0002","profile0x0003","profile0x0004","profile0x0006","profile0x0101","profile0x0102","profile0x0103","profile0x0104" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "profile0x0001","profile0x0002","profile0x0003","profile0x0004","profile0x0006","profile0x0101","profile0x0102","profile0x0103","profile0x0104" };
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
