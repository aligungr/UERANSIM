package tr.havelsan.ueransim.app.itms.wrappers;

import java.util.UUID;

public class ConnectionReleaseWrapper {
    public final UUID ue;

    public ConnectionReleaseWrapper(UUID ue) {
        this.ue = ue;
    }
}
