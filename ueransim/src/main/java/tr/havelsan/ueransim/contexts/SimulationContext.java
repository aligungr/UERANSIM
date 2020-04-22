package tr.havelsan.ueransim.contexts;

import tr.havelsan.ueransim.nas.NasSecurityContext;
import tr.havelsan.ueransim.sctp.SCTPClient;

public class SimulationContext {
    private final SCTPClient sctpClient;
    private NasSecurityContext nasSecurityContext;

    public SimulationContext(SCTPClient sctpClient) {
        this.sctpClient = sctpClient;
        this.nasSecurityContext = null;
    }

    public SCTPClient getSctpClient() {
        return sctpClient;
    }

    public NasSecurityContext getNasSecurityContext() {
        return nasSecurityContext;
    }
}
