/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CrossCarrierSchedulingConfig__schedulingCellInfo__other;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CrossCarrierSchedulingConfig__schedulingCellInfo__own;

public class RRC_CrossCarrierSchedulingConfig__schedulingCellInfo extends RRC_Choice {

    public RRC_CrossCarrierSchedulingConfig__schedulingCellInfo__own own;
    public RRC_CrossCarrierSchedulingConfig__schedulingCellInfo__other other;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "own","other" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "own","other" };
    }

}
