package tr.havelsan.ueransim.ngap0.msg;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.NgapMessageType;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

public class NGAP_DownlinkRANConfigurationTransfer extends NGAP_BaseMessage {

    public NGAP_DownlinkRANConfigurationTransfer() {

    }

    @Override
    public NgapMessageType getMessageType() {
        return NgapMessageType.DownlinkRANConfigurationTransfer;
    }

    @Override
    public int getCriticality() {
        return 1;
    }

    @Override
    public int getProcedureCode() {
        return 6;
    }

    @Override
    public int getPduType() {
        return 0;
    }

    @Override
    public int[] getIeId() {
        return new int[]{98};
    }

    @Override
    public int[] getIeCriticality() {
        return new int[]{1};
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[]{NGAP_SONConfigurationTransfer.class};
    }

    @Override
    public int[] getIePresence() {
        return new int[]{0};
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
        return "DownlinkRANConfigurationTransfer";
    }

    @Override
    public String getXmlTagName() {
        return "DownlinkRANConfigurationTransfer";
    }

}
