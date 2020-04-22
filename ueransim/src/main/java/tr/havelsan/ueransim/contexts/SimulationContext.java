package tr.havelsan.ueransim.contexts;

import tr.havelsan.ueransim.sctp.SCTPClient;

public class SimulationContext {
    private final SCTPClient sctpClient;

    public SimulationContext(SCTPClient sctpClient) {
        this.sctpClient = sctpClient;
    }

    public SCTPClient getSctpClient() {
        return sctpClient;
    }
}
