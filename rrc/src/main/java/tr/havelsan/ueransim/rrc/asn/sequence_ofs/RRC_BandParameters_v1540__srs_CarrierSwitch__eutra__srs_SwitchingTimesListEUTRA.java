/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_SRS_SwitchingTimeEUTRA;

public class RRC_BandParameters_v1540__srs_CarrierSwitch__eutra__srs_SwitchingTimesListEUTRA extends RRC_SequenceOf<RRC_SRS_SwitchingTimeEUTRA> {

    @Override
    public Class<RRC_SRS_SwitchingTimeEUTRA> getItemType() {
        return RRC_SRS_SwitchingTimeEUTRA.class;
    }

}
