/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_T_Reselection;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CarrierFreqListEUTRA;

public class RRC_SIB5 extends AsnSequence {
    public RRC_CarrierFreqListEUTRA carrierFreqListEUTRA; // optional
    public RRC_T_Reselection t_ReselectionEUTRA; // mandatory
    public RRC_SpeedStateScaleFactors t_ReselectionEUTRA_SF; // optional
    public AsnOctetString lateNonCriticalExtension; // optional
}

