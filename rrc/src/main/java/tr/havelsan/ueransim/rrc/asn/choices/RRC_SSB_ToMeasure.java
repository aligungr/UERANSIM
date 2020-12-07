/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnBitString;
import tr.havelsan.ueransim.asn.core.AsnChoice;

public class RRC_SSB_ToMeasure extends AsnChoice {
    public AsnBitString shortBitmap; // SIZE(4)
    public AsnBitString mediumBitmap; // SIZE(8)
    public AsnBitString longBitmap; // SIZE(64)
}

