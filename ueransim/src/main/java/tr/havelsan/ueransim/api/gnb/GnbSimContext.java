package tr.havelsan.ueransim.api.gnb;

import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.sctp.ISCTPClient;

public class GnbSimContext {
    public final SimulationContext simCtx;

    public ISCTPClient sctpClient;
    public int streamNumber;
    public String amfHost;
    public int amfPort;

    public Long amfUeNgapId;
    public long ranUeNgapId;

    public GnbSimContext(SimulationContext simCtx) {
        this.simCtx = simCtx;
    }
}
