/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_CSI_RS_ProcFrameworkForSRS extends AsnSequence {
    public AsnInteger maxNumberPeriodicSRS_AssocCSI_RS_PerBWP; // mandatory, VALUE(1..4)
    public AsnInteger maxNumberAperiodicSRS_AssocCSI_RS_PerBWP; // mandatory, VALUE(1..4)
    public AsnInteger maxNumberSP_SRS_AssocCSI_RS_PerBWP; // mandatory, VALUE(0..4)
    public AsnInteger simultaneousSRS_AssocCSI_RS_PerCC; // mandatory, VALUE(1..8)
}

