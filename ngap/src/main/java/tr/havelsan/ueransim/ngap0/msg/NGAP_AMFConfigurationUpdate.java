package tr.havelsan.ueransim.ngap0.msg;

import tr.havelsan.ueransim.ngap0.pdu.*;
import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

public class NGAP_AMFConfigurationUpdate extends NGAP_BaseMessage {

    public NGAP_AMFConfigurationUpdate() {

    }

    @Override
    public NgapMessageType getMessageType() {
        return NgapMessageType.AMFConfigurationUpdate;
    }

    @Override
    public NgapProtocolIeType getProtocolIeType() {
        return NgapProtocolIeType.forMessage("AMFConfigurationUpdate");
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
        return new int[] { 1, 96, 86, 80, 6, 7, 8 };
    }

    @Override
    public int[] getIeCriticality() {
        return new int[] { 0, 0, 1, 0, 1, 1, 1 };
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[] { NGAP_AMFName.class, NGAP_ServedGUAMIList.class, NGAP_RelativeAMFCapacity.class, NGAP_PLMNSupportList.class, NGAP_AMF_TNLAssociationToAddList.class, NGAP_AMF_TNLAssociationToRemoveList.class, NGAP_AMF_TNLAssociationToUpdateList.class };
    }

    @Override
    public int[] getIePresence() {
        return new int[] { 0, 0, 0, 0, 0, 0, 0 };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[] { "protocolIEs" };
    }

    @Override
    public String[] getMemberNames() {
        return new String[] { "protocolIEs" };
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
