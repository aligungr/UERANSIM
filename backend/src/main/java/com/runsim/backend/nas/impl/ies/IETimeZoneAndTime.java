package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement3;
import com.runsim.backend.nas.impl.values.VTimeZone;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet;

// TODO: Bunu sezgiselleştir.  https://wiki.nicksoft.info/lcb:programming:siemens-mc35:tp-scts
public class IETimeZoneAndTime extends InformationElement3 {
    public Octet year;
    public Octet month;
    public Octet day;
    public Octet hour;
    public Octet minute;
    public Octet second;
    public VTimeZone timeZone;

    @Override
    protected IETimeZoneAndTime decodeIE3(OctetInputStream stream) {
        var res = new IETimeZoneAndTime();
        res.year = stream.readOctet();
        res.month = stream.readOctet();
        res.day = stream.readOctet();
        res.hour = stream.readOctet();
        res.minute = stream.readOctet();
        res.second = stream.readOctet();
        res.timeZone = VTimeZone.decode(stream);
        return res;
    }

    @Override
    public void encodeIE3(OctetOutputStream stream) {
        stream.writeOctet(year);
        stream.writeOctet(month);
        stream.writeOctet(day);
        stream.writeOctet(hour);
        stream.writeOctet(minute);
        stream.writeOctet(second);
        timeZone.encode(stream);
    }
}
