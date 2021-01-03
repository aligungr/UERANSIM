/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_InterfacesToTrace;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_TransportLayerAddress;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_TraceDepth;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_NGRANTraceID;

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
