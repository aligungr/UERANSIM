package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.NGAP_InterfacesToTrace;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.NGAP_TransportLayerAddress;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_TraceDepth;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_NGRANTraceID;

public class NGAP_TraceActivation extends NgapSequence {

    public NGAP_NGRANTraceID nGRANTraceID;
    public NGAP_InterfacesToTrace interfacesToTrace;
    public NGAP_TraceDepth traceDepth;
    public NGAP_TransportLayerAddress traceCollectionEntityIPAddress;

    @Override
    protected String getAsnName() {
        return "TraceActivation";
    }

    @Override
    protected String getXmlTagName() {
        return "TraceActivation";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"nGRANTraceID", "interfacesToTrace", "traceDepth", "traceCollectionEntityIPAddress"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"nGRANTraceID", "interfacesToTrace", "traceDepth", "traceCollectionEntityIPAddress"};
    }
}
