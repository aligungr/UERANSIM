package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_DelayBudgetReport;

public class RRC_UEAssistanceInformation_IEs extends AsnSequence {
    public RRC_DelayBudgetReport delayBudgetReport; // optional
    public AsnOctetString lateNonCriticalExtension; // optional
    public RRC_UEAssistanceInformation_v1540_IEs nonCriticalExtension; // optional
}

