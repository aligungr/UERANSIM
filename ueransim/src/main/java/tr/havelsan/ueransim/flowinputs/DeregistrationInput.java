package tr.havelsan.ueransim.flowinputs;

import tr.havelsan.ueransim.nas.impl.ies.IE5gGutiMobileIdentity;
import tr.havelsan.ueransim.nas.impl.ies.IEDeRegistrationType;
import tr.havelsan.ueransim.ngap2.UserLocationInformationNr;
import tr.havelsan.ueransim.utils.bits.Bit3;

public class DeregistrationInput {
    public final long ranUeNgapId;
    public final IEDeRegistrationType deregistrationType;
    public final Bit3 ngKSI;
    public final IE5gGutiMobileIdentity guti;
    public final UserLocationInformationNr userLocationInformationNr;

    public DeregistrationInput(long ranUeNgapId, IEDeRegistrationType deregistrationType, Bit3 ngKSI, IE5gGutiMobileIdentity guti, UserLocationInformationNr userLocationInformationNr) {
        this.ranUeNgapId = ranUeNgapId;
        this.deregistrationType = deregistrationType;
        this.ngKSI = ngKSI;
        this.guti = guti;
        this.userLocationInformationNr = userLocationInformationNr;
    }
}
