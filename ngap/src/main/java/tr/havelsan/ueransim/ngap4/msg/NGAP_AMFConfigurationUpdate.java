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

public class NGAP_AMFConfigurationUpdate extends NGAP_BaseMessage {

    public NGAP_ProtocolIEContainer protocolIEs;

    public NGAP_AMFConfigurationUpdate() {

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
        return 0;
    }

    @Override
    public int[] getIeId() {
        return new int[]{1, 96, 86, 80, 6, 7, 8};
    }

    @Override
    public int[] getIeCriticality() {
        return new int[]{0, 0, 1, 0, 1, 1, 1};
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[]{NGAP_AMFName.class, NGAP_ServedGUAMIList.class, NGAP_RelativeAMFCapacity.class, NGAP_PLMNSupportList.class, NGAP_AMF_TNLAssociationToAddList.class, NGAP_AMF_TNLAssociationToRemoveList.class, NGAP_AMF_TNLAssociationToUpdateList.class};
    }

    @Override
    public int[] getIePresence() {
        return new int[]{0, 0, 0, 0, 0, 0, 0};
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
        return "AMFConfigurationUpdate";
    }

    @Override
    public String getXmlTagName() {
        return "AMFConfigurationUpdate";
    }

}
