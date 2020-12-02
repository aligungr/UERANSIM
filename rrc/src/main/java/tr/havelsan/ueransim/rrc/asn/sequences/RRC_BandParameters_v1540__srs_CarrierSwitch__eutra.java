/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_BandParameters_v1540__srs_CarrierSwitch__eutra__srs_SwitchingTimesListEUTRA;

public class RRC_BandParameters_v1540__srs_CarrierSwitch__eutra extends RRC_Sequence {

    public RRC_BandParameters_v1540__srs_CarrierSwitch__eutra__srs_SwitchingTimesListEUTRA srs_SwitchingTimesListEUTRA;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "srs-SwitchingTimesListEUTRA" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "srs_SwitchingTimesListEUTRA" };
    }

}
