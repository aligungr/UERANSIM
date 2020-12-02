/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_BandNR__ext1 extends RRC_Sequence {

    public RRC_Integer maxUplinkDutyCycle_PC2_FR1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "maxUplinkDutyCycle-PC2-FR1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "maxUplinkDutyCycle_PC2_FR1" };
    }

}
