package tr.havelsan.ueransim.flowtesting.inputs;

import tr.havelsan.ueransim.sim.ue.UserLocationInformationNr;
import tr.havelsan.ueransim.nas.impl.ies.IEDnn;
import tr.havelsan.ueransim.nas.impl.ies.IESNssai;
import tr.havelsan.ueransim.utils.octets.Octet;

public class PduSessionReleaseInput {
    public  final UserLocationInformationNr userLocationInformationNr;
    public final Octet pduSessionId;
    public final Octet procedureTransactionId;
    public final IESNssai sNssai;
    public final IEDnn dnn;

    public PduSessionReleaseInput(
            UserLocationInformationNr userLocationInformationNr,
            Octet pduSessionId, Octet procedureTransactionId,
            IESNssai sNssai, IEDnn dnn) {
        this.userLocationInformationNr = userLocationInformationNr;
        this.pduSessionId = pduSessionId;
        this.procedureTransactionId = procedureTransactionId;
        this.sNssai = sNssai;
        this.dnn = dnn;
    }
}