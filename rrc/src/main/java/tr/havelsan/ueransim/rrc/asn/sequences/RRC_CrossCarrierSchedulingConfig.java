/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_CrossCarrierSchedulingConfig__schedulingCellInfo;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CrossCarrierSchedulingConfig extends RRC_Sequence {

    public RRC_CrossCarrierSchedulingConfig__schedulingCellInfo schedulingCellInfo;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "schedulingCellInfo" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "schedulingCellInfo" };
    }

    @Override
    public String getAsnName() {
        return "CrossCarrierSchedulingConfig";
    }

    @Override
    public String getXmlTagName() {
        return "CrossCarrierSchedulingConfig";
    }

}
