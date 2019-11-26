package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ProtocolEnum;
import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IEUesUsageSetting extends InformationElement4 {
    public EUesUsageSetting uesUsageSetting;

    @Override
    protected IEUesUsageSetting decodeIE4(OctetInputStream stream, int length) {
        var res = new IEUesUsageSetting();
        res.uesUsageSetting = EUesUsageSetting.fromValue(stream.readOctetI() & 0b1);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctet(uesUsageSetting.intValue());
    }

    public static class EUesUsageSetting extends ProtocolEnum {
        public static final EUesUsageSetting VOICE_CENTRIC
                = new EUesUsageSetting(0b0, "voice centric");
        public static final EUesUsageSetting DATA_CENTRIC
                = new EUesUsageSetting(0b1, "data centric");

        private EUesUsageSetting(int value, String name) {
            super(value, name);
        }

        public static EUesUsageSetting fromValue(int value) {
            return fromValueGeneric(EUesUsageSetting.class, value, null);
        }
    }
}
