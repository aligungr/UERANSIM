package tr.havelsan.ueransim.configs;

import tr.havelsan.ueransim.nas.impl.ies.IEDnn;
import tr.havelsan.ueransim.nas.impl.ies.IESNssai;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet4;

public class PduSessionEstablishmentConfig {
    public final Octet pduSessionId;
    public final Octet procedureTransactionId;
    public final IESNssai sNssai;
    public final IEDnn dnn;
    public final Octet4 transportLayerAddress;
    public final Octet4 gTpTeid;
    public final int qosFlowIdentifier;

    public PduSessionEstablishmentConfig(Octet pduSessionId, Octet procedureTransactionId, IESNssai sNssai, IEDnn dnn, Octet4 transportLayerAddress, Octet4 gTpTeid, int qosFlowIdentifier) {
        this.pduSessionId = pduSessionId;
        this.procedureTransactionId = procedureTransactionId;
        this.sNssai = sNssai;
        this.dnn = dnn;
        this.transportLayerAddress = transportLayerAddress;
        this.gTpTeid = gTpTeid;
        this.qosFlowIdentifier = qosFlowIdentifier;
    }
}
