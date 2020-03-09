package tr.havelsan.ueransim.inputs;

import tr.havelsan.ueransim.app.ue.UserLocationInformationNr;
import tr.havelsan.ueransim.nas.impl.ies.IE5gGutiMobileIdentity;
import tr.havelsan.ueransim.nas.impl.ies.IEDeRegistrationType;
import tr.havelsan.ueransim.utils.bits.Bit3;

public class DeregistrationInput {
    public final long ranUeNgapId;
    public final long amfUeNgapId;
    public final IEDeRegistrationType deregistrationType;
    public final Bit3 ngKSI;
    public final IE5gGutiMobileIdentity guti;
    public final UserLocationInformationNr userLocationInformationNr;

    public DeregistrationInput(long ranUeNgapId, long amfUeNgapId, IEDeRegistrationType deregistrationType, Bit3 ngKSI, IE5gGutiMobileIdentity guti, UserLocationInformationNr userLocationInformationNr) {
        this.ranUeNgapId = ranUeNgapId;
        this.amfUeNgapId = amfUeNgapId;
        this.deregistrationType = deregistrationType;
        this.ngKSI = ngKSI;
        this.guti = guti;
        this.userLocationInformationNr = userLocationInformationNr;
    }
}
