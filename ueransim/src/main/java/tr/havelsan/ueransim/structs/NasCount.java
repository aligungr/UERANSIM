package tr.havelsan.ueransim.structs;

import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet2;
import tr.havelsan.ueransim.utils.octets.Octet4;

public class NasCount {
    public Octet2 overflow;
    public Octet sqn;

    public NasCount() {
        this.overflow = new Octet2();
        this.sqn = new Octet();
    }

    public NasCount deepCopy() {
        var res = new NasCount();
        res.overflow = this.overflow;
        res.sqn = this.sqn;
        return res;
    }

    public Octet4 toOctet4() {
        long value = 0;
        value |= this.overflow.longValue();
        value <<= 8;
        value |= this.sqn.longValue();
        return new Octet4(value);
    }
}