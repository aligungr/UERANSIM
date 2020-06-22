package tr.havelsan.ueransim.core;

import tr.havelsan.ueransim.IncomingMessage;
import tr.havelsan.ueransim.OutgoingMessage;
import tr.havelsan.ueransim.nas.impl.ies.IE5gsTrackingAreaIdentity;
import tr.havelsan.ueransim.nas.impl.ies.IE5gsTrackingAreaIdentityList;
import tr.havelsan.ueransim.nas.impl.messages.RegistrationRequest;
import tr.havelsan.ueransim.sctp.ISCTPClient;
import tr.havelsan.ueransim.structs.UeConfig;
import tr.havelsan.ueransim.structs.UeData;
import tr.havelsan.ueransim.structs.UeTimers;

public class SimulationContext {
    // Connection related
    public ISCTPClient sctpClient;
    public int streamNumber;
    public String amfHost;
    public int amfPort;

    // UE related
    public UeData ueData;
    public UeConfig ueConfig;
    public NasSecurityContext currentNsc;
    public NasSecurityContext nonCurrentNsc;
    public UeTimers ueTimers;
    public IE5gsTrackingAreaIdentity lastVisitedRegisteredTai;
    public IE5gsTrackingAreaIdentityList taiList;
    public RegistrationRequest registrationRequest;

    // NGAP IE related
    public Long amfUeNgapId;
    public long ranUeNgapId;

    // Message callback
    private IMessageListener messageListener;

    public SimulationContext() {
        this.messageListener = null;
        this.ueTimers = new UeTimers();
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
