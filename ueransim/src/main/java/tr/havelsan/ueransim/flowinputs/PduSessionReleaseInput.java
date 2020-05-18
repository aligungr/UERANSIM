package tr.havelsan.ueransim.flowinputs;

import tr.havelsan.ueransim.nas.impl.ies.IEDnn;
import tr.havelsan.ueransim.nas.impl.ies.IESNssai;
import tr.havelsan.ueransim.utils.octets.Octet;

public class PduSessionReleaseInput {
    public final long ranUeNgapId;
    public final Octet pduSessionId;
    public final Octet procedureTransactionId;
    public final IESNssai sNssai;
    public final IEDnn dnn;

    public PduSessionReleaseInput(long ranUeNgapId, Octet pduSessionId, Octet procedureTransactionId, IESNssai sNssai, IEDnn dnn) {
        this.ranUeNgapId = ranUeNgapId;
        this.pduSessionId = pduSessionId;
        this.procedureTransactionId = procedureTransactionId;
        this.sNssai = sNssai;
        this.dnn = dnn;
    }
}