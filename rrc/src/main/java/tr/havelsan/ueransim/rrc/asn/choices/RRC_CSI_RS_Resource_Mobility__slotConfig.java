/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;

public class RRC_CSI_RS_Resource_Mobility__slotConfig extends RRC_Choice {

    public RRC_Integer ms4;
    public RRC_Integer ms5;
    public RRC_Integer ms10;
    public RRC_Integer ms20;
    public RRC_Integer ms40;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ms4","ms5","ms10","ms20","ms40" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ms4","ms5","ms10","ms20","ms40" };
    }

}
