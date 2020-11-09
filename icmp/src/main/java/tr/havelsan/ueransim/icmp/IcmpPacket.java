package tr.havelsan.ueransim.icmp;

import tr.havelsan.ueransim.utils.OctetOutputStream;

public abstract class IcmpPacket {

    abstract void encode(OctetOutputStream stream);
}
