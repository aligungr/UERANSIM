package tr.havelsan.ueransim.contexts;

import tr.havelsan.ueransim.nas.impl.ies.IEImeiMobileIdentity;
import tr.havelsan.ueransim.nas.impl.ies.IEImsiMobileIdentity;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class UeData {
    public String ssn;
    public OctetString key;
    public OctetString op;
    public OctetString sqn;
    public OctetString amf;

    public IEImeiMobileIdentity imei;
    public IEImsiMobileIdentity imsi;
}
