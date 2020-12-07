/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
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

