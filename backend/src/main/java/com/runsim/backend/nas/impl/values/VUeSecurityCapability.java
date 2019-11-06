package com.runsim.backend.nas.impl.values;

import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.bits.Bit;

public class VUeSecurityCapability extends NasValue {

    // 3GPP 24.501 f20:
    // "For the UE all bits in octets 7 to 10 are spare and shall be ignored, if the respective octet is received with the information element."
    // Hence, octet7-octet10 are ignored because it is meaningless for UE (but meaningful for AMF)

    public static final String NAME_5G_EA0 = "5G-EA0";
    public static final String NAME_128_5G_EA1 = "128-5G-EA1";
    public static final String NAME_128_5G_EA2 = "128-5G-EA2";
    public static final String NAME_128_5G_EA3 = "128-5G-EA3";
    public static final String NAME_5G_EA4 = "5G-EA4";
    public static final String NAME_5G_EA5 = "5G-EA5";
    public static final String NAME_5G_EA6 = "5G-EA6";
    public static final String NAME_5G_EA7 = "5G-EA7";
    public static final String NAME_5G_IA0 = "5G-IA0";
    public static final String NAME_128_5G_IA1 = "128-5G-IA1";
    public static final String NAME_128_5G_IA2 = "128-5G-IA2";
    public static final String NAME_128_5G_IA3 = "128-5G-IA3";
    public static final String NAME_5G_IA4 = "5G-IA4";
    public static final String NAME_5G_IA5 = "5G-IA5";
    public static final String NAME_5G_IA6 = "5G-IA6";
    public static final String NAME_5G_IA7 = "5G-IA7";
    public static final String NAME_EEA0 = "EEA0";
    public static final String NAME_128_EEA1 = "128-EEA1";
    public static final String NAME_128_EEA2 = "128-EEA2";
    public static final String NAME_128_EEA3 = "128-EEA3";
    public static final String NAME_EEA4 = "EEA4";
    public static final String NAME_EEA5 = "EEA5";
    public static final String NAME_EEA6 = "EEA6";
    public static final String NAME_EEA7 = "EEA7";
    public static final String NAME_EIA0 = "EIA0";
    public static final String NAME_128_EIA1 = "128-EIA1";
    public static final String NAME_128_EIA2 = "128-EIA2";
    public static final String NAME_128_EIA3 = "128-EIA3";
    public static final String NAME_EIA4 = "EIA4";
    public static final String NAME_EIA5 = "EIA5";
    public static final String NAME_EIA6 = "EIA6";
    public static final String NAME_EIA7 = "EIA7";

    public Bit SUPPORTED_5G_EA0;
    public Bit SUPPORTED_128_5G_EA1;
    public Bit SUPPORTED_128_5G_EA2;
    public Bit SUPPORTED_128_5G_EA3;
    public Bit SUPPORTED_5G_EA4;
    public Bit SUPPORTED_5G_EA5;
    public Bit SUPPORTED_5G_EA6;
    public Bit SUPPORTED_5G_EA7;
    public Bit SUPPORTED_5G_IA0;
    public Bit SUPPORTED_128_5G_IA1;
    public Bit SUPPORTED_128_5G_IA2;
    public Bit SUPPORTED_128_5G_IA3;
    public Bit SUPPORTED_5G_IA4;
    public Bit SUPPORTED_5G_IA5;
    public Bit SUPPORTED_5G_IA6;
    public Bit SUPPORTED_5G_IA7;
    public Bit SUPPORTED_EEA0;
    public Bit SUPPORTED_128_EEA1;
    public Bit SUPPORTED_128_EEA2;
    public Bit SUPPORTED_128_EEA3;
    public Bit SUPPORTED_EEA4;
    public Bit SUPPORTED_EEA5;
    public Bit SUPPORTED_EEA6;
    public Bit SUPPORTED_EEA7;
    public Bit SUPPORTED_EIA0;
    public Bit SUPPORTED_128_EIA1;
    public Bit SUPPORTED_128_EIA2;
    public Bit SUPPORTED_128_EIA3;
    public Bit SUPPORTED_EIA4;
    public Bit SUPPORTED_EIA5;
    public Bit SUPPORTED_EIA6;
    public Bit SUPPORTED_EIA7;

    @Override
    public NasValue decode(OctetInputStream stream) {
        int length = stream.readOctetI();

        var cap = new VUeSecurityCapability();

        if (length >= 1) {
            var bits = stream.readOctet();
            cap.SUPPORTED_5G_EA0 = bits.getBit(7);
            cap.SUPPORTED_128_5G_EA1 = bits.getBit(6);
            cap.SUPPORTED_128_5G_EA2 = bits.getBit(5);
            cap.SUPPORTED_128_5G_EA3 = bits.getBit(4);
            cap.SUPPORTED_5G_EA4 = bits.getBit(3);
            cap.SUPPORTED_5G_EA5 = bits.getBit(2);
            cap.SUPPORTED_5G_EA6 = bits.getBit(1);
            cap.SUPPORTED_5G_EA7 = bits.getBit(0);
        }

        if (length >= 2) {
            var bits = stream.readOctet();
            cap.SUPPORTED_5G_IA0 = bits.getBit(7);
            cap.SUPPORTED_128_5G_IA1 = bits.getBit(6);
            cap.SUPPORTED_128_5G_IA2 = bits.getBit(5);
            cap.SUPPORTED_128_5G_IA3 = bits.getBit(4);
            cap.SUPPORTED_5G_IA4 = bits.getBit(3);
            cap.SUPPORTED_5G_IA5 = bits.getBit(2);
            cap.SUPPORTED_5G_IA6 = bits.getBit(1);
            cap.SUPPORTED_5G_IA7 = bits.getBit(0);
        }

        if (length >= 3) {
            var bits = stream.readOctet();
            cap.SUPPORTED_EEA0 = bits.getBit(7);
            cap.SUPPORTED_128_EEA1 = bits.getBit(6);
            cap.SUPPORTED_128_EEA2 = bits.getBit(5);
            cap.SUPPORTED_128_EEA3 = bits.getBit(4);
            cap.SUPPORTED_EEA4 = bits.getBit(3);
            cap.SUPPORTED_EEA5 = bits.getBit(2);
            cap.SUPPORTED_EEA6 = bits.getBit(1);
            cap.SUPPORTED_EEA7 = bits.getBit(0);
        }

        if (length >= 4) {
            var bits = stream.readOctet();
            cap.SUPPORTED_EIA0 = bits.getBit(7);
            cap.SUPPORTED_128_EIA1 = bits.getBit(6);
            cap.SUPPORTED_128_EIA2 = bits.getBit(5);
            cap.SUPPORTED_128_EIA3 = bits.getBit(4);
            cap.SUPPORTED_EIA4 = bits.getBit(3);
            cap.SUPPORTED_EIA5 = bits.getBit(2);
            cap.SUPPORTED_EIA6 = bits.getBit(1);
            cap.SUPPORTED_EIA7 = bits.getBit(0);
        }

        return cap;
    }
}
