package tr.havelsan.ueransim.ngap0.msg;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.NgapMessageType;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

public class NGAP_RANConfigurationUpdateFailure extends NGAP_BaseMessage {

    public NGAP_RANConfigurationUpdateFailure() {

    }

    @Override
    public NgapMessageType getMessageType() {
        return NgapMessageType.RANConfigurationUpdateFailure;
    }

    @Override
    public int getCriticality() {
        return 0;
    }

    @Override
    public int getProcedureCode() {
        return 35;
    }

    @Override
    public int getPduType() {
        return 2;
    }

    @Override
    public int[] getIeId() {
        return new int[]{15, 107, 19};
    }

    @Override
    public int[] getIeCriticality() {
        return new int[]{1, 1, 1};
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[]{NGAP_Cause.class, NGAP_TimeToWait.class, NGAP_CriticalityDiagnostics.class};
    }

    @Override
    public int[] getIePresence() {
        return new int[]{2, 0, 0};
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
        return "RANConfigurationUpdateFailure";
    }

    @Override
    public String getXmlTagName() {
        return "RANConfigurationUpdateFailure";
    }

}
