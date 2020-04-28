package tr.havelsan.ueransim.flowinputs;

import tr.havelsan.ueransim.nas.impl.ies.IEDnn;
import tr.havelsan.ueransim.nas.impl.ies.IESNssai;
import tr.havelsan.ueransim.ngap2.UserLocationInformationNr;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet4;

public class PduSessionEstablishmentInput {
    public final long ranUeNgapId;
    public final Octet pduSessionId;
    public final Octet procedureTransactionId;
    public final IESNssai sNssai;
    public final IEDnn dnn;
    public final UserLocationInformationNr userLocationInformationNr;
    public final Octet4 transportLayerAddress;
    public final Octet4 gTpTeid;
    public final int qosFlowIdentifier;

    public PduSessionEstablishmentInput(long ranUeNgapId, Octet pduSessionId, Octet procedureTransactionId, IESNssai sNssai, IEDnn dnn, UserLocationInformationNr userLocationInformationNr, Octet4 transportLayerAddress, Octet4 gTpTeid, int qosFlowIdentifier) {
        this.ranUeNgapId = ranUeNgapId;
        this.pduSessionId = pduSessionId;
        this.procedureTransactionId = procedureTransactionId;
        this.sNssai = sNssai;
        this.dnn = dnn;
        this.userLocationInformationNr = userLocationInformationNr;
        this.transportLayerAddress = transportLayerAddress;
        this.gTpTeid = gTpTeid;
        this.qosFlowIdentifier = qosFlowIdentifier;
    }
}
