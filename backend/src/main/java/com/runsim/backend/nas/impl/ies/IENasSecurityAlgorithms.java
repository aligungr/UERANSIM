package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ProtocolEnum;
import com.runsim.backend.nas.core.ies.InformationElement3;
import com.runsim.backend.nas.impl.enums.ETypeOfIntegrityProtectionAlgorithm;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IENasSecurityAlgorithms extends InformationElement3 {
    public ETypeOfIntegrityProtectionAlgorithm typeOfIntegrityProtectionAlgorithm;
    public ETypeOfCipheringAlgorithm typeOfCipheringAlgorithm;

    @Override
    protected IENasSecurityAlgorithms decodeIE3(OctetInputStream stream) {
        var res = new IENasSecurityAlgorithms();
        res.typeOfIntegrityProtectionAlgorithm = ETypeOfIntegrityProtectionAlgorithm.fromValue(stream.peekOctetI());
        res.typeOfCipheringAlgorithm = ETypeOfCipheringAlgorithm.fromValue(stream.readOctetI() >> 4);
        return res;
    }

    @Override
    public void encodeIE3(OctetOutputStream stream) {
        stream.writeOctet(typeOfCipheringAlgorithm.intValue(), typeOfIntegrityProtectionAlgorithm.intValue());
    }

    public static class ETypeOfCipheringAlgorithm extends ProtocolEnum {
        public static final ETypeOfCipheringAlgorithm EA0
                = new ETypeOfCipheringAlgorithm(0b0000, "5G encryption algorithm 5G-EA0 (null ciphering protection algorithm)");
        public static final ETypeOfCipheringAlgorithm EA1_128
                = new ETypeOfCipheringAlgorithm(0b0001, "5G encryption algorithm 128-5G-EA1");
        public static final ETypeOfCipheringAlgorithm EA2_128
                = new ETypeOfCipheringAlgorithm(0b0010, "5G encryption algorithm 128-5G-EA2");
        public static final ETypeOfCipheringAlgorithm EA3_128
                = new ETypeOfCipheringAlgorithm(0b0011, "5G encryption algorithm 128-5G-EA3");
        public static final ETypeOfCipheringAlgorithm EA4
                = new ETypeOfCipheringAlgorithm(0b0100, "5G encryption algorithm 5G-EA4");
        public static final ETypeOfCipheringAlgorithm EA5
                = new ETypeOfCipheringAlgorithm(0b0101, "5G encryption algorithm 5G-EA5");
        public static final ETypeOfCipheringAlgorithm EA6
                = new ETypeOfCipheringAlgorithm(0b0110, "5G encryption algorithm 5G-EA6");
        public static final ETypeOfCipheringAlgorithm EA7
                = new ETypeOfCipheringAlgorithm(0b0111, "5G encryption algorithm 5G-EA7");

        private ETypeOfCipheringAlgorithm(int value, String name) {
            super(value, name);
        }

        public static ETypeOfCipheringAlgorithm fromValue(int value) {
            return fromValueGeneric(ETypeOfCipheringAlgorithm.class, value);
        }
    }
}
