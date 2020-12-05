package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CarrierInfoNR;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_RedirectedCarrierInfo_EUTRA;

public class RRC_RedirectedCarrierInfo extends AsnChoice {
    public RRC_CarrierInfoNR nr;
    public RRC_RedirectedCarrierInfo_EUTRA eutra;
}

