package tr.havelsan.ueransim.flowtesting.inputs;

import tr.havelsan.ueransim.ngap2.SupportedTA;
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
