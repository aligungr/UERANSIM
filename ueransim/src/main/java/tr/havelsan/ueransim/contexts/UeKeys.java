package tr.havelsan.ueransim.contexts;

import tr.havelsan.ueransim.utils.octets.OctetString;

public class UeKeys {
    public OctetString rand;
    public OctetString res;
    public OctetString resStar; // used in 5G-AKA

    public OctetString kAusf;
    public OctetString kSeaf;
    public OctetString kAmf;
    public OctetString kNasInt;
    public OctetString kNasEnc;
}
