package tr.havelsan.ueransim.flows;

import fr.marben.asnsdk.japi.InvalidStructureException;
import fr.marben.asnsdk.japi.spe.ContainingOctetStringValue;
import tr.havelsan.ueransim.*;
import tr.havelsan.ueransim.apism.UePduSession;
import tr.havelsan.ueransim.configs.PduSessionEstablishmentConfig;
import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.ngap.ngap_ies.*;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.PDUSessionResourceSetupRequest;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapProcedure;

import java.util.Collections;

import static tr.havelsan.ueransim.ngap.Values.NGAP_Constants__id_PDUSessionResourceSetupListSURes;

public class PduSessionEstablishmentFlow extends BaseFlow {

    private final PduSessionEstablishmentConfig config;

    public PduSessionEstablishmentFlow(SimulationContext simContext, PduSessionEstablishmentConfig config) {
        super(simContext);
        this.config = config;
    }

    @Override
    public State main(IncomingMessage message) {
        UePduSession.sendEstablishmentRequest(ctx, config);

        return this::waitPduSessionEstablishmentAccept;
    }

    private State waitPduSessionEstablishmentAccept(IncomingMessage message) {
        var pduSessionResourceSetupRequest = message.getNgapMessage(PDUSessionResourceSetupRequest.class);
        if (pduSessionResourceSetupRequest == null) {
            FlowLogging.logUnhandledMessage(message, PDUSessionResourceSetupRequest.class);
            return this::waitPduSessionEstablishmentAccept;
        }

        var gtpTunnel = new GTPTunnel();
        gtpTunnel.transportLayerAddress = new TransportLayerAddress(config.transportLayerAddress.toByteArray(), 32);
        gtpTunnel.gTP_TEID = new GTP_TEID(config.gTpTeid.toByteArray());

        var transfer = new PDUSessionResourceSetupResponseTransfer();
        transfer.qosFlowPerTNLInformation = new QosFlowPerTNLInformation();
        try {
            transfer.qosFlowPerTNLInformation.uPTransportLayerInformation = new UPTransportLayerInformation(UPTransportLayerInformation.ASN_gTPTunnel, gtpTunnel);
        } catch (InvalidStructureException e) {
            throw new RuntimeException(e);
        }
        transfer.qosFlowPerTNLInformation.associatedQosFlowList = new AssociatedQosFlowList();

        var associatedFlowItem = new AssociatedQosFlowItem();
        associatedFlowItem.qosFlowIdentifier = new QosFlowIdentifier(config.qosFlowIdentifier);
        transfer.qosFlowPerTNLInformation.associatedQosFlowList.valueList = Collections.singletonList(associatedFlowItem);

        var list = new PDUSessionResourceSetupListSURes();
        var item = new PDUSessionResourceSetupItemSURes();

        item.pDUSessionID = new PDUSessionID(config.pduSessionId.intValue());
        item.pDUSessionResourceSetupResponseTransfer = new ContainingOctetStringValue(transfer);
        list.valueList = Collections.singletonList(item);

        send(new SendingMessage(new NgapBuilder(NgapProcedure.PDUSessionResourceSetupResponse, NgapCriticality.REJECT)
                .addProtocolIE(list, NgapCriticality.IGNORE, NGAP_Constants__id_PDUSessionResourceSetupListSURes), null));

        return flowComplete();
    }

    @Override
    public void onReceive(IncomingMessage incomingMessage) {

    }

    @Override
    public void onSent(OutgoingMessage outgoingMessage) {

    }
}
