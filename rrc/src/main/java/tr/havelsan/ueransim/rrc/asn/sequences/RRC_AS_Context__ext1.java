/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_RAN_NotificationAreaInfo;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_AS_Context__ext1 extends RRC_Sequence {

    public RRC_RAN_NotificationAreaInfo ran_NotificationAreaInfo;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ran-NotificationAreaInfo" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ran_NotificationAreaInfo" };
    }

}
