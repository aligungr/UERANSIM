/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnNull;

public class RRC_SRS_PeriodicityAndOffset extends AsnChoice {
    public AsnNull sl1;
    public AsnInteger sl2; // VALUE(0..1)
    public AsnInteger sl4; // VALUE(0..3)
    public AsnInteger sl5; // VALUE(0..4)
    public AsnInteger sl8; // VALUE(0..7)
    public AsnInteger sl10; // VALUE(0..9)
    public AsnInteger sl16; // VALUE(0..15)
    public AsnInteger sl20; // VALUE(0..19)
    public AsnInteger sl32; // VALUE(0..31)
    public AsnInteger sl40; // VALUE(0..39)
    public AsnInteger sl64; // VALUE(0..63)
    public AsnInteger sl80; // VALUE(0..79)
    public AsnInteger sl160; // VALUE(0..159)
    public AsnInteger sl320; // VALUE(0..319)
    public AsnInteger sl640; // VALUE(0..639)
    public AsnInteger sl1280; // VALUE(0..1279)
    public AsnInteger sl2560; // VALUE(0..2559)
}

