/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PhysCellId;

public class RRC_MeasResultEUTRA extends AsnSequence {
    public RRC_PhysCellId eutra_PhysCellId; // mandatory
    public RRC_MeasQuantityResultsEUTRA measResult; // mandatory
    public RRC_CGI_InfoEUTRA cgi_Info; // optional
}

