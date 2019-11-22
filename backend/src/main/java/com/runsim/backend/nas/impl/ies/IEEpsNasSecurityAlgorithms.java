package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ProtocolEnum;
import com.runsim.backend.nas.core.ies.InformationElement3;
import com.runsim.backend.nas.impl.enums.EEpsTypeOfIntegrityProtectionAlgorithm;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IEEpsNasSecurityAlgorithms extends InformationElement3 {
    public EEpsTypeOfIntegrityProtectionAlgorithm typeOfIntegrityProtectionAlgorithm;
    public EEpsTypeOfCipheringAlgorithm typeOfCipheringAlgorithm;

    @Override
    protected IEEpsNasSecurityAlgorithms decodeIE3(OctetInputStream stream) {
        var res = new IEEpsNasSecurityAlgorithms();
        res.typeOfIntegrityProtectionAlgorithm = EEpsTypeOfIntegrityProtectionAlgorithm.fromValue(stream.peekOctetI());
        res.typeOfCipheringAlgorithm = EEpsTypeOfCipheringAlgorithm.fromValue(stream.readOctetI() >> 4);
        return res;
    }

    @Override
    public void encodeIE3(OctetOutputStream stream) {
        stream.writeOctet(typeOfCipheringAlgorithm.intValue(), typeOfIntegrityProtectionAlgorithm.intValue());
    }

    public static class EEpsTypeOfCipheringAlgorithm extends ProtocolEnum {
        public static final EEpsTypeOfCipheringAlgorithm EEA0
                = new EEpsTypeOfCipheringAlgorithm(0b000, "EPS encryption algorithm EEA0 (null ciphering protection algorithm)");
        public static final EEpsTypeOfCipheringAlgorithm EEA1_128
                = new EEpsTypeOfCipheringAlgorithm(0b001, "EPS encryption algorithm 128-EEA1");
        public static final EEpsTypeOfCipheringAlgorithm EEA2_128
                = new EEpsTypeOfCipheringAlgorithm(0b010, "EPS encryption algorithm 128-EEA2");
        public static final EEpsTypeOfCipheringAlgorithm EEA3_128
                = new EEpsTypeOfCipheringAlgorithm(0b011, "EPS encryption algorithm 128-EEA3");
        public static final EEpsTypeOfCipheringAlgorithm EEA4
                = new EEpsTypeOfCipheringAlgorithm(0b100, "EPS encryption algorithm EEA4");
        public static final EEpsTypeOfCipheringAlgorithm EEA5
                = new EEpsTypeOfCipheringAlgorithm(0b101, "EPS encryption algorithm EEA5");
        public static final EEpsTypeOfCipheringAlgorithm EEA6
                = new EEpsTypeOfCipheringAlgorithm(0b110, "EPS encryption algorithm EEA6");
        public static final EEpsTypeOfCipheringAlgorithm EEA7
                = new EEpsTypeOfCipheringAlgorithm(0b111, "EPS encryption algorithm EEA7");

        private EEpsTypeOfCipheringAlgorithm(int value, String name) {
            super(value, name);
        }

        public static EEpsTypeOfCipheringAlgorithm fromValue(int value) {
            return fromValueGeneric(EEpsTypeOfCipheringAlgorithm.class, value);
        }
    }
}
