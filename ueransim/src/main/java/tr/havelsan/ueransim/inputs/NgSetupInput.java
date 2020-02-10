package tr.havelsan.ueransim.inputs;

import tr.havelsan.ueransim.app.ue.SupportedTA;
import tr.havelsan.ueransim.nas.impl.values.VPlmn;

public class NgSetupInput {
    public final int gnbId;
    public final VPlmn gnbPlmn;
    public final SupportedTA[] supportedTAs;

    public NgSetupInput(int gnbId, VPlmn gnbPlmn, SupportedTA[] supportedTAs) {
        this.gnbId = gnbId;
        this.gnbPlmn = gnbPlmn;
        this.supportedTAs = supportedTAs;
    }
}
