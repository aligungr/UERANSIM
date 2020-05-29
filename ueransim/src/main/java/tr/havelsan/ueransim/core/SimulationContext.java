package tr.havelsan.ueransim.core;

import tr.havelsan.ueransim.IncomingMessage;
import tr.havelsan.ueransim.OutgoingMessage;
import tr.havelsan.ueransim.nas.impl.ies.IE5gGutiMobileIdentity;
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
    public IE5gGutiMobileIdentity guti;
    public NasSecurityContext nasSecurityContext;

    // NGAP IE related
    public Long amfUeNgapId;
    public long ranUeNgapId;
    public UserLocationInformationNr userLocationInformationNr;

    // Message callback
    private IMessageListener messageListener;

    public SimulationContext() {
        this.messageListener = null;
    }

    // todo: use read/write lock instead of synchronized

    public synchronized void registerListener(IMessageListener listener) {
        messageListener = listener;
    }

    public synchronized void unregisterListener() {
        messageListener = null;
    }

    public synchronized void dispatchMessageReceive(IncomingMessage incomingMessage) {
        var listener = messageListener;
        if (listener != null) listener.onReceive(incomingMessage);
    }

    public synchronized void dispatchMessageSent(OutgoingMessage outgoingMessage) {
        var listener = messageListener;
        if (listener != null) listener.onSent(outgoingMessage);
    }
}
