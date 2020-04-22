package tr.havelsan.ueransim.flowinputs;

import tr.havelsan.ueransim.nas.impl.ies.IE5gTmsiMobileIdentity;
import tr.havelsan.ueransim.ngap2.UserLocationInformationNr;
import tr.havelsan.ueransim.utils.bits.Bit3;

public class ServiceRequestFlowInput {
    public final long ranUeNgapId;
    public final UserLocationInformationNr userLocationInformationNr;
    public final IE5gTmsiMobileIdentity tmsi;
    public final Bit3 ngKSI;

    public ServiceRequestFlowInput(long ranUeNgapId, UserLocationInformationNr userLocationInformationNr,
                                   IE5gTmsiMobileIdentity tmsi, Bit3 ngKSI) {
        this.ranUeNgapId = ranUeNgapId;
        this.userLocationInformationNr = userLocationInformationNr;
        this.tmsi = tmsi;
        this.ngKSI = ngKSI;
    }
}
