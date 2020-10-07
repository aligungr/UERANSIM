package tr.havelsan.ueransim.gtp.pdusup;

import org.apache.commons.net.ntp.TimeStamp;
import tr.havelsan.ueransim.utils.bits.Bit6;
import tr.havelsan.ueransim.utils.octets.Octet3;

// See 3GPP 38.415
public class DlPduSessionInformation extends PduSessionInformation {

    public Bit6 qfi;              // (Mandatory) QOS Flow Identifier
    public boolean rqi;           // (Mandatory) Reflective QOS Indicator, See 5.5.3.4
    public Integer ppi;           // (Optional) Paging Policy Indicator, See 5.5.3.7
    public TimeStamp dlSendingTs; // (Optional) DL Sending Time Stamp, See 5.5.3.9
    public Octet3 dlQfiSeq;       // (Optional) DL QFI Sequence Number, See 5.5.3.18

    public DlPduSessionInformation() {
        super(PDU_TYPE_DL);
    }
}
