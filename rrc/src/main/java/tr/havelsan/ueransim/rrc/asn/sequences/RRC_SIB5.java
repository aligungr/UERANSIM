/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_T_Reselection;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CarrierFreqListEUTRA;

public class RRC_SIB5 extends RRC_Sequence {

    public RRC_CarrierFreqListEUTRA carrierFreqListEUTRA;
    public RRC_T_Reselection t_ReselectionEUTRA;
    public RRC_SpeedStateScaleFactors t_ReselectionEUTRA_SF;
    public RRC_OctetString lateNonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "carrierFreqListEUTRA","t-ReselectionEUTRA","t-ReselectionEUTRA-SF","lateNonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "carrierFreqListEUTRA","t_ReselectionEUTRA","t_ReselectionEUTRA_SF","lateNonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "SIB5";
    }

    @Override
    public String getXmlTagName() {
        return "SIB5";
    }

}
