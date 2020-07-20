package tr.havelsan.ueransim.ngap0.msg;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.NgapMessageType;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

public class NGAP_AMFConfigurationUpdateAcknowledge extends NGAP_BaseMessage {

    public NGAP_AMFConfigurationUpdateAcknowledge() {

    }

    @Override
    public NgapMessageType getMessageType() {
        return NgapMessageType.AMFConfigurationUpdateAcknowledge;
    }

    @Override
    public int getCriticality() {
        return 0;
    }

    @Override
    public int getProcedureCode() {
        return 0;
    }

    @Override
    public int getPduType() {
        return 1;
    }

    @Override
    public int[] getIeId() {
        return new int[]{5, 4, 19};
    }

    @Override
    public int[] getIeCriticality() {
        return new int[]{1, 1, 1};
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[]{NGAP_AMF_TNLAssociationSetupList.class, NGAP_TNLAssociationList.class, NGAP_CriticalityDiagnostics.class};
    }

    @Override
    public int[] getIePresence() {
        return new int[]{0, 0, 0};
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
        return "AMFConfigurationUpdateAcknowledge";
    }

    @Override
    public String getXmlTagName() {
        return "AMFConfigurationUpdateAcknowledge";
    }

}
