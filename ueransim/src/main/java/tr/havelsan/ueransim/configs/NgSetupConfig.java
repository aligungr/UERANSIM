package tr.havelsan.ueransim.configs;

import tr.havelsan.ueransim.nas.impl.values.VPlmn;
import tr.havelsan.ueransim.ngap2.SupportedTA;

public class NgSetupConfig {
    public final int gnbId;
    public final VPlmn gnbPlmn;
    public final SupportedTA[] supportedTAs;

    public NgSetupConfig(int gnbId, VPlmn gnbPlmn, SupportedTA[] supportedTAs) {
        this.gnbId = gnbId;
        this.gnbPlmn = gnbPlmn;
        this.supportedTAs = supportedTAs;
    }
}
