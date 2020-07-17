package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap4.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap4.ies.sequences.*;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap4.ies.choices.*;
import tr.havelsan.ueransim.ngap4.ies.integers.*;
import tr.havelsan.ueransim.ngap4.ies.enumerations.*;

import java.util.List;

public class NGAP_TraceActivation extends NgapSequence {

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
