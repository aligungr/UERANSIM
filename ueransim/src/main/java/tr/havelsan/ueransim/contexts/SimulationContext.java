package tr.havelsan.ueransim.contexts;

import tr.havelsan.ueransim.nas.NasSecurityContext;
import tr.havelsan.ueransim.sctp.ISCTPClient;

public class SimulationContext {
    private final ISCTPClient sctpClient;
    private final int streamNumber;
    private NasSecurityContext nasSecurityContext;
    private long amfUeNgapId;

    public SimulationContext(ISCTPClient sctpClient, int streamNumber) {
        this.sctpClient = sctpClient;
        this.nasSecurityContext = null;
        this.amfUeNgapId = 0;
        this.streamNumber = streamNumber;
    }

    public ISCTPClient getSctpClient() {
        return sctpClient;
    }

    public NasSecurityContext getNasSecurityContext() {
        return nasSecurityContext;
    }

    public long getAmfUeNgapId() {
        return amfUeNgapId;
    }

    public void setAmfUeNgapId(long amfUeNgapId) {
        this.amfUeNgapId = amfUeNgapId;
    }

    public int getStreamNumber() {
        return streamNumber;
    }
}
