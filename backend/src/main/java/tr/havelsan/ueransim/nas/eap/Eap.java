package tr.havelsan.ueransim.nas.eap;

import tr.havelsan.ueransim.nas.core.ProtocolValue;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet2;

public abstract class Eap extends ProtocolValue {
    public EEapCode code;
    public Octet id;
    public Octet2 length;
    public EEapType EAPType;
}
