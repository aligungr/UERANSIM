package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnNull;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_EUTRA_RSTD_InfoList;

public class RRC_LocationMeasurementInfo extends AsnChoice {
    public RRC_EUTRA_RSTD_InfoList eutra_RSTD;
    public AsnNull eutra_FineTimingDetection;
}

