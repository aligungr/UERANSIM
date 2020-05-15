package tr.havelsan.ueransim.contexts;

import tr.havelsan.ueransim.nas.NasSecurityContext;
import tr.havelsan.ueransim.sctp.ISCTPClient;

public class SimulationContext {
    public final ISCTPClient sctpClient;
    public final int streamNumber;

    public UeData ueData;
    public NasSecurityContext nasSecurityContext;

    public Long amfUeNgapId;

    public SimulationContext(ISCTPClient sctpClient, int streamNumber) {
        this.sctpClient = sctpClient;
        this.streamNumber = streamNumber;
    }
}
