package tr.havelsan.ueransim.structs;

import tr.havelsan.ueransim.nas.impl.ies.IESNssai;
import tr.havelsan.ueransim.ngap2.UserLocationInformationNr;

public class UeConfig {
    public boolean smsOverNasSupported;
    public IESNssai[] requestedNssai;
    public UserLocationInformationNr userLocationInformationNr;
}
