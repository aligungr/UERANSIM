/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnInteger;

public class RRC_MeasTriggerQuantityOffset extends AsnChoice {
    public AsnInteger rsrp; // VALUE(-30..30)
    public AsnInteger rsrq; // VALUE(-30..30)
    public AsnInteger sinr; // VALUE(-30..30)
}

