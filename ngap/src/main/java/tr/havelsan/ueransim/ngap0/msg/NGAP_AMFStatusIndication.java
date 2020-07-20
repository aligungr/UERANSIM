package tr.havelsan.ueransim.ngap0.msg;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.NgapMessageType;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;

public class NGAP_AMFStatusIndication extends NGAP_BaseMessage {

    public NGAP_AMFStatusIndication() {

    }

    @Override
    public NgapMessageType getMessageType() {
        return NgapMessageType.AMFStatusIndication;
    }

    @Override
    public int getCriticality() {
        return 1;
    }

    @Override
    public int getProcedureCode() {
        return 1;
    }

    @Override
    public int getPduType() {
        return 0;
    }

    @Override
    public int[] getIeId() {
        return new int[]{120};
    }

    @Override
    public int[] getIeCriticality() {
        return new int[]{0};
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[]{NGAP_UnavailableGUAMIList.class};
    }

    @Override
    public int[] getIePresence() {
        return new int[]{2};
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
        return "AMFStatusIndication";
    }

    @Override
    public String getXmlTagName() {
        return "AMFStatusIndication";
    }

}
