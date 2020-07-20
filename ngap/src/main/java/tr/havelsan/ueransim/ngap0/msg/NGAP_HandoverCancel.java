package tr.havelsan.ueransim.ngap0.msg;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.NgapMessageType;
import tr.havelsan.ueransim.ngap0.ies.choices.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_HandoverCancel extends NGAP_BaseMessage {

    public NGAP_HandoverCancel() {

    }

    @Override
    public NgapMessageType getMessageType() {
        return NgapMessageType.HandoverCancel;
    }

    @Override
    public int getCriticality() {
        return 0;
    }

    @Override
    public int getProcedureCode() {
        return 10;
    }

    @Override
    public int getPduType() {
        return 0;
    }

    @Override
    public int[] getIeId() {
        return new int[]{10, 85, 15};
    }

    @Override
    public int[] getIeCriticality() {
        return new int[]{0, 0, 1};
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[]{NGAP_AMF_UE_NGAP_ID.class, NGAP_RAN_UE_NGAP_ID.class, NGAP_Cause.class};
    }

    @Override
    public int[] getIePresence() {
        return new int[]{2, 2, 2};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"protocolIEs"};
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"protocolIEs"};
    }

    @Override
    public String getAsnName() {
        return "HandoverCancel";
    }

    @Override
    public String getXmlTagName() {
        return "HandoverCancel";
    }

}
