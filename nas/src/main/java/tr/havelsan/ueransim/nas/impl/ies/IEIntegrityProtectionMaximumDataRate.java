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

public class IEIntegrityProtectionMaximumDataRate extends InformationElement3 {
    // couldn't have been a longer name I guess
    public EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink maxRateUplink;
    public EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink maxRateDownlink;

    public IEIntegrityProtectionMaximumDataRate() {
    }

    public IEIntegrityProtectionMaximumDataRate(EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink maxRateUplink, EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink maxRateDownlink) {
        this.maxRateUplink = maxRateUplink;
        this.maxRateDownlink = maxRateDownlink;
    }

    @Override
    protected IEIntegrityProtectionMaximumDataRate decodeIE3(OctetInputStream stream) {
        var res = new IEIntegrityProtectionMaximumDataRate();
        res.maxRateUplink = EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink.fromValue(stream.readOctetI());
        res.maxRateDownlink = EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink.fromValue(stream.readOctetI());
        return res;
    }

    @Override
    public void encodeIE3(OctetOutputStream stream) {
        stream.writeOctet(maxRateUplink.intValue());
        stream.writeOctet(maxRateDownlink.intValue());
    }

    public static class EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink extends ProtocolEnum {
        public static final EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink SIXTY_FOR_KBPS
                = new EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink(0b00000000, "64 kbps");
        public static final EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink FULL_DATA_RATE
                = new EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink(0b11111111, "Full data rate");

        private EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink(int value, String name) {
            super(value, name);
        }

        public static EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink fromValue(int value) {
            return fromValueGeneric(EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink.class, value, null);
        }
    }

    public static class EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink extends ProtocolEnum {
        public static final EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink SIXTY_FOR_KBPS
                = new EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink(0b00000000, "64 kbps");
        public static final EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink FULL_DATA_RATE
                = new EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink(0b11111111, "Full data rate");

        private EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink(int value, String name) {
            super(value, name);
        }

        public static EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink fromValue(int value) {
            return fromValueGeneric(EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink.class, value, null);
        }
    }
}
