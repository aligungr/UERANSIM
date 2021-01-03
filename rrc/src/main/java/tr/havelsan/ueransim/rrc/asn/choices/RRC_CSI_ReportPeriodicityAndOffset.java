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

public class RRC_CSI_ReportPeriodicityAndOffset extends AsnChoice {
    public AsnInteger slots4; // VALUE(0..3)
    public AsnInteger slots5; // VALUE(0..4)
    public AsnInteger slots8; // VALUE(0..7)
    public AsnInteger slots10; // VALUE(0..9)
    public AsnInteger slots16; // VALUE(0..15)
    public AsnInteger slots20; // VALUE(0..19)
    public AsnInteger slots40; // VALUE(0..39)
    public AsnInteger slots80; // VALUE(0..79)
    public AsnInteger slots160; // VALUE(0..159)
    public AsnInteger slots320; // VALUE(0..319)
}

