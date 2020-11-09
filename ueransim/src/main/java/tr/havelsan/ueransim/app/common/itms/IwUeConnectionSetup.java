package tr.havelsan.ueransim.app.common.itms;

import tr.havelsan.ueransim.app.common.PduSession;

public class IwUeConnectionSetup {
    public final PduSession pduSession;

    public IwUeConnectionSetup(PduSession pduSession) {
        this.pduSession = pduSession;
    }
}
