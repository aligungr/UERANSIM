package tr.havelsan.ueransim.gtp.pdusup;

import org.apache.commons.net.ntp.TimeStamp;
import tr.havelsan.ueransim.utils.octets.Octet3;

public class DlPduSessionInformation extends PduSessionInformation {

    public int qfi;             // QOS Flow Identifier
    public boolean rqi;         // Reflective QOS Indicator, See 5.5.3.4
    public Integer ppi;         // Paging Policy Indicator, See 5.5.3.7
    public TimeStamp sendingTs; // DL Sending Time Stamp, See 5.5.3.9
    public Octet3 qfiSeq;       // DL QFI Sequence Number, See 5.5.3.18

    public DlPduSessionInformation() {
        super(PDU_TYPE_DL);
    }
}
