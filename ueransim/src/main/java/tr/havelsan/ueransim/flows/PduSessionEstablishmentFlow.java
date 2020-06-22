package tr.havelsan.ueransim.flows;

import tr.havelsan.ueransim.BaseFlow;
import tr.havelsan.ueransim.IncomingMessage;
import tr.havelsan.ueransim.OutgoingMessage;
import tr.havelsan.ueransim.apism.UePduSessionEstablishment;
import tr.havelsan.ueransim.configs.PduSessionEstablishmentConfig;
import tr.havelsan.ueransim.core.SimulationContext;

public class PduSessionEstablishmentFlow extends BaseFlow {

    private final PduSessionEstablishmentConfig config;

    public PduSessionEstablishmentFlow(SimulationContext simContext, PduSessionEstablishmentConfig config) {
        super(simContext);
        this.config = config;
    }

    @Override
    public State main(IncomingMessage message) {
        UePduSessionEstablishment.sendEstablishmentRequest(ctx, config);

        return this::waitPduSessionEstablishmentAccept;
    }

    private State waitPduSessionEstablishmentAccept(IncomingMessage message) {

        /* TODO
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
        */
        return flowComplete();
    }

    @Override
    public void onReceive(IncomingMessage incomingMessage) {

    }

    @Override
    public void onSent(OutgoingMessage outgoingMessage) {

    }
}
