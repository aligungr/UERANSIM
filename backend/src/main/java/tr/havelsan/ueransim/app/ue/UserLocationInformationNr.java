package tr.havelsan.ueransim.app.ue;

import tr.havelsan.ueransim.nas.impl.values.VPlmn;
import tr.havelsan.ueransim.nas.impl.values.VTrackingAreaIdentity;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class UserLocationInformationNr {
    public final NrCgi nrCgi;
    public final VTrackingAreaIdentity tai;
    public final OctetString timeStamp;

    public UserLocationInformationNr(NrCgi nrCgi, VTrackingAreaIdentity tai, OctetString timeStamp) {
        this.nrCgi = nrCgi;
        this.tai = tai;
        this.timeStamp = timeStamp;
    }

    public static class NrCgi {
        public final VPlmn plmn;
        public final OctetString nrCellIdentity;

        public NrCgi(VPlmn plmn, OctetString nrCellIdentity) {
            this.plmn = plmn;
            this.nrCellIdentity = nrCellIdentity;
        }
    }
}
