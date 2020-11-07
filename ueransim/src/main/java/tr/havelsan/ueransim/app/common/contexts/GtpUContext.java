package tr.havelsan.ueransim.app.common.contexts;

import tr.havelsan.ueransim.app.common.PduSessionResource;

import java.util.ArrayList;
import java.util.List;

public class GtpUContext {

    // TODO: Make O(1)
    public final List<PduSessionResource> pduSessions;

    public GtpUContext() {
        this.pduSessions = new ArrayList<>();
    }
}
