package tr.havelsan.ueransim.gtp.pdusup;

import org.apache.commons.net.ntp.TimeStamp;
import tr.havelsan.ueransim.utils.bits.Bit6;
import tr.havelsan.ueransim.utils.octets.Octet3;
import tr.havelsan.ueransim.utils.octets.Octet4;

// See 3GPP 38.415
public class UlPduSessionInformation extends PduSessionInformation {

    public Bit6 qfi;                        // (Mandatory) QOS Flow Identifier, See 5.5.3.3
    public TimeStamp dlSendingTsRepeated;   // (Optional) DL Sending Time Stamp Repeated, See 5.5.3.10
    public TimeStamp dlReceivedTs;          // (Optional) DL Received Time Stamp, See 5.5.3.11
    public TimeStamp ulSendingTs;           // (Optional) UL Sending Time Stamp, See 5.5.3.12
    public Octet4 dlDelayResult;            // (Optional) DL Delay Result, See 5.5.3.14
    public Octet4 ulDelayResult;            // (Optional) UL Delay Result, See 5.5.3.16
    public Octet3 ulQfiSeq;                 // (Optional) UL QFI Sequence Number, See 5.5.3.19

    public UlPduSessionInformation() {
        super(PDU_TYPE_UL);
    }
}
