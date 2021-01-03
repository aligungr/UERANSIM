/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.core.ies.InformationElement3;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;

public class IEEpsNasSecurityAlgorithms extends InformationElement3 {
    public EEpsTypeOfIntegrityProtectionAlgorithm typeOfIntegrityProtectionAlgorithm;
    public EEpsTypeOfCipheringAlgorithm typeOfCipheringAlgorithm;

    public IEEpsNasSecurityAlgorithms() {
    }

    public IEEpsNasSecurityAlgorithms(EEpsTypeOfIntegrityProtectionAlgorithm typeOfIntegrityProtectionAlgorithm, EEpsTypeOfCipheringAlgorithm typeOfCipheringAlgorithm) {
        this.typeOfIntegrityProtectionAlgorithm = typeOfIntegrityProtectionAlgorithm;
        this.typeOfCipheringAlgorithm = typeOfCipheringAlgorithm;
    }

    @Override
    protected IEEpsNasSecurityAlgorithms decodeIE3(OctetInputStream stream) {
        var res = new IEEpsNasSecurityAlgorithms();
        res.typeOfIntegrityProtectionAlgorithm = EEpsTypeOfIntegrityProtectionAlgorithm.fromValue(stream.peekOctetI() & 0b111);
        res.typeOfCipheringAlgorithm = EEpsTypeOfCipheringAlgorithm.fromValue(stream.readOctetI() >> 4 & 0b111);
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
            return fromValueGeneric(EEpsTypeOfCipheringAlgorithm.class, value, null);
        }
    }

    public static class EEpsTypeOfIntegrityProtectionAlgorithm extends ProtocolEnum {
        public static final EEpsTypeOfIntegrityProtectionAlgorithm EIA0
                = new EEpsTypeOfIntegrityProtectionAlgorithm(0b000, "EPS integrity algorithm EIA0 (null integrity protection algorithm)");
        public static final EEpsTypeOfIntegrityProtectionAlgorithm EIA1_128
                = new EEpsTypeOfIntegrityProtectionAlgorithm(0b001, "EPS integrity algorithm 128-EIA1");
        public static final EEpsTypeOfIntegrityProtectionAlgorithm EIA2_128
                = new EEpsTypeOfIntegrityProtectionAlgorithm(0b010, "EPS integrity algorithm 128-EIA2");
        public static final EEpsTypeOfIntegrityProtectionAlgorithm EIA3_128
                = new EEpsTypeOfIntegrityProtectionAlgorithm(0b011, "EPS integrity algorithm 128-EIA3");
        public static final EEpsTypeOfIntegrityProtectionAlgorithm EIA4
                = new EEpsTypeOfIntegrityProtectionAlgorithm(0b100, "EPS integrity algorithm EIA4");
        public static final EEpsTypeOfIntegrityProtectionAlgorithm EIA5
                = new EEpsTypeOfIntegrityProtectionAlgorithm(0b101, "EPS integrity algorithm EIA5");
        public static final EEpsTypeOfIntegrityProtectionAlgorithm EIA6
                = new EEpsTypeOfIntegrityProtectionAlgorithm(0b110, "EPS integrity algorithm EIA6");
        public static final EEpsTypeOfIntegrityProtectionAlgorithm EIA7
                = new EEpsTypeOfIntegrityProtectionAlgorithm(0b111, "EPS integrity algorithm EIA7");

        private EEpsTypeOfIntegrityProtectionAlgorithm(int value, String name) {
            super(value, name);
        }

        public static EEpsTypeOfIntegrityProtectionAlgorithm fromValue(int value) {
            return fromValueGeneric(EEpsTypeOfIntegrityProtectionAlgorithm.class, value, null);
        }
    }
}
