package tr.havelsan.ueransim.ngap0.msg;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.NgapMessageType;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_DownlinkNASTransport extends NGAP_BaseMessage {

    public NGAP_DownlinkNASTransport() {

    }

    @Override
    public NgapMessageType getMessageType() {
        return NgapMessageType.DownlinkNASTransport;
    }

    @Override
    public int getCriticality() {
        return 1;
    }

    @Override
    public int getProcedureCode() {
        return 4;
    }

    @Override
    public int getPduType() {
        return 0;
    }

    @Override
    public int[] getIeId() {
        return new int[]{10, 85, 48, 83, 38, 36, 31, 110, 0};
    }

    @Override
    public int[] getIeCriticality() {
        return new int[]{0, 0, 0, 1, 0, 1, 1, 1, 0};
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[]{NGAP_AMF_UE_NGAP_ID.class, NGAP_RAN_UE_NGAP_ID.class, NGAP_AMFName.class, NGAP_RANPagingPriority.class, NGAP_NAS_PDU.class, NGAP_MobilityRestrictionList.class, NGAP_IndexToRFSP.class, NGAP_UEAggregateMaximumBitRate.class, NGAP_AllowedNSSAI.class};
    }

    @Override
    public int[] getIePresence() {
        return new int[]{2, 2, 0, 0, 2, 0, 0, 0, 0};
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
        return "DownlinkNASTransport";
    }

    @Override
    public String getXmlTagName() {
        return "DownlinkNASTransport";
    }

}
