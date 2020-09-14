package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.pdu.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

import java.util.List;

public class NGAP_QoSFlowsUsageReport_Item extends NGAP_Sequence {

    public NGAP_QosFlowIdentifier qosFlowIdentifier;
    public NGAP_Integer rATType;
    public NGAP_VolumeTimedReportList qoSFlowsTimedReportList;

    @Override
    public String getAsnName() {
        return "QoSFlowsUsageReport-Item";
    }

    @Override
    public String getXmlTagName() {
        return "QoSFlowsUsageReport-Item";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"qosFlowIdentifier", "rATType", "qoSFlowsTimedReportList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"qosFlowIdentifier", "rATType", "qoSFlowsTimedReportList"};
    }
}
