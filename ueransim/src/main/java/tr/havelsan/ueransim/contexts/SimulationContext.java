package tr.havelsan.ueransim.contexts;

import tr.havelsan.ueransim.nas.NasSecurityContext;
import tr.havelsan.ueransim.ngap2.UserLocationInformationNr;
import tr.havelsan.ueransim.sctp.ISCTPClient;

public class SimulationContext {
    // Connection related
    public ISCTPClient sctpClient;
    public int streamNumber;
    public String amfHost;
    public int amfPort;

    // UE related
    public UeData ueData;
    public NasSecurityContext nasSecurityContext;

    // NGAP IE related
    public Long amfUeNgapId;
    public long ranUeNgapId;
    public UserLocationInformationNr userLocationInformationNr;
}
