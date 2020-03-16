package tr.havelsan.ueransim.flowtesting.inputs;

import tr.havelsan.ueransim.nas.impl.ies.IEDnn;
import tr.havelsan.ueransim.nas.impl.ies.IESNssai;
import tr.havelsan.ueransim.ngap2.UserLocationInformationNr;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class PduSessionReleaseInput {
    public final long ranUeNgapId;
    public final long amfUeNgapId;
    public final UserLocationInformationNr userLocationInformationNr;
    public final Octet pduSessionId;
    public final Octet procedureTransactionId;
    public final IESNssai sNssai;
    public final IEDnn dnn;
    public final OctetString payloadContainer;

    public PduSessionReleaseInput(long ranUeNgapId, long amfUeNgapId, UserLocationInformationNr userLocationInformationNr, Octet pduSessionId, Octet procedureTransactionId, IESNssai sNssai, IEDnn dnn, OctetString payloadContainer) {
        this.ranUeNgapId = ranUeNgapId;
        this.amfUeNgapId = amfUeNgapId;
        this.userLocationInformationNr = userLocationInformationNr;
        this.pduSessionId = pduSessionId;
        this.procedureTransactionId = procedureTransactionId;
        this.sNssai = sNssai;
        this.dnn = dnn;
        this.payloadContainer = payloadContainer;
    }
}