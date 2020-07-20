package tr.havelsan.ueransim.ngap0.msg;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.NgapMessageType;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

public class NGAP_Paging extends NGAP_BaseMessage {

    public NGAP_Paging() {

    }

    @Override
    public NgapMessageType getMessageType() {
        return NgapMessageType.Paging;
    }

    @Override
    public int getCriticality() {
        return 1;
    }

    @Override
    public int getProcedureCode() {
        return 24;
    }

    @Override
    public int getPduType() {
        return 0;
    }

    @Override
    public int[] getIeId() {
        return new int[]{115, 50, 103, 52, 118, 51, 11};
    }

    @Override
    public int[] getIeCriticality() {
        return new int[]{1, 1, 1, 1, 1, 1, 1};
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[]{NGAP_UEPagingIdentity.class, NGAP_PagingDRX.class, NGAP_TAIListForPaging.class, NGAP_PagingPriority.class, NGAP_UERadioCapabilityForPaging.class, NGAP_PagingOrigin.class, NGAP_AssistanceDataForPaging.class};
    }

    @Override
    public int[] getIePresence() {
        return new int[]{2, 0, 2, 0, 0, 0, 0};
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
        return "Paging";
    }

    @Override
    public String getXmlTagName() {
        return "Paging";
    }

}
