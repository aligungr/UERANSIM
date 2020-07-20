package tr.havelsan.ueransim.ngap0.msg;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.NgapMessageType;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;

public class NGAP_PWSCancelResponse extends NGAP_BaseMessage {

    public NGAP_PWSCancelResponse() {

    }

    @Override
    public NgapMessageType getMessageType() {
        return NgapMessageType.PWSCancelResponse;
    }

    @Override
    public int getCriticality() {
        return 0;
    }

    @Override
    public int getProcedureCode() {
        return 32;
    }

    @Override
    public int getPduType() {
        return 1;
    }

    @Override
    public int[] getIeId() {
        return new int[]{35, 95, 12, 19};
    }

    @Override
    public int[] getIeCriticality() {
        return new int[]{0, 0, 1, 1};
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[]{NGAP_MessageIdentifier.class, NGAP_SerialNumber.class, NGAP_BroadcastCancelledAreaList.class, NGAP_CriticalityDiagnostics.class};
    }

    @Override
    public int[] getIePresence() {
        return new int[]{2, 2, 0, 0};
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
        return "PWSCancelResponse";
    }

    @Override
    public String getXmlTagName() {
        return "PWSCancelResponse";
    }

}
