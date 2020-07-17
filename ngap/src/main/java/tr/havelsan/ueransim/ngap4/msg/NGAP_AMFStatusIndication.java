package tr.havelsan.ueransim.ngap4.msg;

import tr.havelsan.ueransim.ngap4.core.NGAP_Message;
import tr.havelsan.ueransim.ngap2.NgapMessageType;

public class NGAP_AMFStatusIndication extends NGAP_Message {

    public NGAP_AMFStatusIndication() {
        super(NgapMessageType.AMFStatusIndication);
    }

}
