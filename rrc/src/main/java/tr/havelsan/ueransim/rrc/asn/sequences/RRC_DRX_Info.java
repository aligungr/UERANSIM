/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_DRX_Info__drx_LongCycleStartOffset;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_DRX_Info extends RRC_Sequence {

    public RRC_DRX_Info__drx_LongCycleStartOffset drx_LongCycleStartOffset;
    public RRC_DRX_Info__shortDRX shortDRX;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "drx-LongCycleStartOffset","shortDRX" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "drx_LongCycleStartOffset","shortDRX" };
    }

    @Override
    public String getAsnName() {
        return "DRX-Info";
    }

    @Override
    public String getXmlTagName() {
        return "DRX-Info";
    }

}
