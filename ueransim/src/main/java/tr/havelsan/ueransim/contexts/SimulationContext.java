package tr.havelsan.ueransim.contexts;

import tr.havelsan.ueransim.nas.NasSecurityContext;
import tr.havelsan.ueransim.ngap2.UserLocationInformationNr;
import tr.havelsan.ueransim.sctp.ISCTPClient;

public class SimulationContext {
    public ISCTPClient sctpClient;
    public int streamNumber;

    public UeData ueData;
    public NasSecurityContext nasSecurityContext;

    public Long amfUeNgapId;
    public long ranUeNgapId;
    public UserLocationInformationNr userLocationInformationNr;
}
