/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_PLMN_Identity;

public class RRC_PLMN_Identity_EUTRA_5GC extends AsnChoice {
    public RRC_PLMN_Identity plmn_Identity_EUTRA_5GC;
    public AsnInteger plmn_index; // VALUE(1..12)
}

