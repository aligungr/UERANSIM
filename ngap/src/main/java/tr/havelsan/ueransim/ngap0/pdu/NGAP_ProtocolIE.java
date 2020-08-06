package tr.havelsan.ueransim.ngap0.pdu;

import tr.havelsan.ueransim.ngap0.NgapProtocolIeType;
import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

public class NGAP_ProtocolIE extends NGAP_Sequence {

    private final NgapProtocolIeType protocolIeType;

    public NGAP_ProtocolIE_ID id;
    public NGAP_Criticality criticality;
    public NGAP_IEChoice value;

    public NGAP_ProtocolIE(NgapProtocolIeType protocolIeType) {
        this.protocolIeType = protocolIeType;
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"id", "criticality", "value"};
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"id", "criticality", "value"};
    }

    @Override
    public String getAsnName() {
        return protocolIeType.getAsnName();
    }

    @Override
    public String getXmlTagName() {
        return protocolIeType.getAsnName();
    }

    public static NGAP_ProtocolIE newInstanceFromTag(String tag) {
        var type = NgapProtocolIeType.fromAsnName(tag);
        if (type == null) return null;
        return new NGAP_ProtocolIE(type);
    }
}
