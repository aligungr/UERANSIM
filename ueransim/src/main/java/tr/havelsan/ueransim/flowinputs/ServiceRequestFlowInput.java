package tr.havelsan.ueransim.flowinputs;

import tr.havelsan.ueransim.nas.impl.ies.IE5gTmsiMobileIdentity;
import tr.havelsan.ueransim.utils.bits.Bit3;

public class ServiceRequestFlowInput {
    public final IE5gTmsiMobileIdentity tmsi;
    public final Bit3 ngKSI;

    public ServiceRequestFlowInput(IE5gTmsiMobileIdentity tmsi, Bit3 ngKSI) {
        this.tmsi = tmsi;
        this.ngKSI = ngKSI;
    }
}
