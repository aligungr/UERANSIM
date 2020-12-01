/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_BWP_Id;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PUCCH_ResourceId;

public class RRC_PUCCH_CSI_Resource extends RRC_Sequence {

    public RRC_BWP_Id uplinkBandwidthPartId;
    public RRC_PUCCH_ResourceId pucch_Resource;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "uplinkBandwidthPartId","pucch-Resource" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "uplinkBandwidthPartId","pucch_Resource" };
    }

    @Override
    public String getAsnName() {
        return "PUCCH-CSI-Resource";
    }

    @Override
    public String getXmlTagName() {
        return "PUCCH-CSI-Resource";
    }

}
