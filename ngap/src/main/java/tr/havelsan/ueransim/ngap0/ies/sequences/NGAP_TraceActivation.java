package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

public class NGAP_TraceActivation extends NGAP_Sequence {

    public NGAP_NGRANTraceID nGRANTraceID;
    public NGAP_InterfacesToTrace interfacesToTrace;
    public NGAP_TraceDepth traceDepth;
    public NGAP_TransportLayerAddress traceCollectionEntityIPAddress;

    @Override
    public String getAsnName() {
        return "TraceActivation";
    }

    @Override
    public String getXmlTagName() {
        return "TraceActivation";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"nGRANTraceID", "interfacesToTrace", "traceDepth", "traceCollectionEntityIPAddress"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"nGRANTraceID", "interfacesToTrace", "traceDepth", "traceCollectionEntityIPAddress"};
    }
}
