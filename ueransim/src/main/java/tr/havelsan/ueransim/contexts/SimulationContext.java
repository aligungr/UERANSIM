package tr.havelsan.ueransim.contexts;

import tr.havelsan.ueransim.nas.NasSecurityContext;
import tr.havelsan.ueransim.sctp.SCTPClient;

public class SimulationContext {
    private final SCTPClient sctpClient;
    private NasSecurityContext nasSecurityContext;
    private long amfUeNgapId;

    public SimulationContext(SCTPClient sctpClient) {
        this.sctpClient = sctpClient;
        this.nasSecurityContext = null;
        this.amfUeNgapId = 0;
    }

    public SCTPClient getSctpClient() {
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
}
