package tr.havelsan.ueransim.ngap4.msg;

import tr.havelsan.ueransim.ngap4.core.NGAP_Message;
import tr.havelsan.ueransim.ngap2.NgapMessageType;

public class NGAP_UEContextReleaseCommand extends NGAP_Message {

    public NGAP_UEContextReleaseCommand() {
        super(NgapMessageType.UEContextReleaseCommand);
    }

}
