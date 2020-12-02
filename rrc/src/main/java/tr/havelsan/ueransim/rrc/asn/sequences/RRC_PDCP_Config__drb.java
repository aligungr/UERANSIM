/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_PDCP_Config__drb__headerCompression;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_PDCP_Config__drb extends RRC_Sequence {

    public RRC_Integer discardTimer;
    public RRC_Integer pdcp_SN_SizeUL;
    public RRC_Integer pdcp_SN_SizeDL;
    public RRC_PDCP_Config__drb__headerCompression headerCompression;
    public RRC_Integer integrityProtection;
    public RRC_Integer statusReportRequired;
    public RRC_Integer outOfOrderDelivery;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "discardTimer","pdcp-SN-SizeUL","pdcp-SN-SizeDL","headerCompression","integrityProtection","statusReportRequired","outOfOrderDelivery" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "discardTimer","pdcp_SN_SizeUL","pdcp_SN_SizeDL","headerCompression","integrityProtection","statusReportRequired","outOfOrderDelivery" };
    }

}
