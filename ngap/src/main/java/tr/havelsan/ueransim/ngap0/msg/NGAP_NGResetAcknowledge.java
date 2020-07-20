package tr.havelsan.ueransim.ngap0.msg;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.NgapMessageType;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

public class NGAP_NGResetAcknowledge extends NGAP_BaseMessage {

    public NGAP_NGResetAcknowledge() {

    }

    @Override
    public NgapMessageType getMessageType() {
        return NgapMessageType.NGResetAcknowledge;
    }

    @Override
    public int getCriticality() {
        return 0;
    }

    @Override
    public int getProcedureCode() {
        return 20;
    }

    @Override
    public int getPduType() {
        return 1;
    }

    @Override
    public int[] getIeId() {
        return new int[]{111, 19};
    }

    @Override
    public int[] getIeCriticality() {
        return new int[]{1, 1};
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[]{NGAP_UE_associatedLogicalNG_connectionList.class, NGAP_CriticalityDiagnostics.class};
    }

    @Override
    public int[] getIePresence() {
        return new int[]{0, 0};
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
        return "NGResetAcknowledge";
    }

    @Override
    public String getXmlTagName() {
        return "NGResetAcknowledge";
    }

}
