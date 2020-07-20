package tr.havelsan.ueransim.ngap4.msg;

import tr.havelsan.ueransim.ngap4.pdu.*;
import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.ngap2.NgapMessageType;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap4.ies.sequences.*;
import tr.havelsan.ueransim.ngap4.ies.choices.*;
import tr.havelsan.ueransim.ngap4.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap4.ies.integers.*;
import tr.havelsan.ueransim.ngap4.ies.enumerations.*;

public class NGAP_UERadioCapabilityCheckResponse extends NGAP_BaseMessage {

    public NGAP_ProtocolIEContainer protocolIEs;

    public NGAP_UERadioCapabilityCheckResponse() {

    }

    @Override
    public int getCriticality() {
        return 0;
    }

    @Override
    public int getProcedureCode() {
        return 43;
    }

    @Override
    public int getPduType() {
        return 1;
    }

    @Override
    public int[] getIeId() {
        return new int[]{10, 85, 30, 19};
    }

    @Override
    public int[] getIeCriticality() {
        return new int[]{1, 1, 0, 1};
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[]{NGAP_AMF_UE_NGAP_ID.class, NGAP_RAN_UE_NGAP_ID.class, NGAP_IMSVoiceSupportIndicator.class, NGAP_CriticalityDiagnostics.class};
    }

    @Override
    public int[] getIePresence() {
        return new int[]{2, 2, 2, 0};
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
        return "UERadioCapabilityCheckResponse";
    }

    @Override
    public String getXmlTagName() {
        return "UERadioCapabilityCheckResponse";
    }

}
