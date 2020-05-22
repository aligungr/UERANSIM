package tr.havelsan.ueransim.core;

import tr.havelsan.ueransim.ngap2.UserLocationInformationNr;
import tr.havelsan.ueransim.sctp.ISCTPClient;

import java.util.HashSet;
import java.util.Set;

public class SimulationContext {
    // Message callback
    private final Set<IMessageListener> messageListeners;

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

    public SimulationContext() {
        this.messageListeners = new HashSet<>();
    }

    public synchronized void registerListener(IMessageListener listener) {
        messageListeners.add(listener);
    }

    public synchronized void unregisterListener(IMessageListener listener) {
        messageListeners.remove(listener);
    }
}
