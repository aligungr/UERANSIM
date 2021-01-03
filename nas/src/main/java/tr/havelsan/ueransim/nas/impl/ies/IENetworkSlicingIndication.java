/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.core.ies.InformationElement1;
import tr.havelsan.ueransim.utils.bits.Bit4;

public class IENetworkSlicingIndication extends InformationElement1 {
    public ENetworkSlicingSubscriptionChangeIndication nssci;
    public EDefaultConfiguredNssaiIndication dcni;

    public IENetworkSlicingIndication() {
    }

    public IENetworkSlicingIndication(ENetworkSlicingSubscriptionChangeIndication nssci, EDefaultConfiguredNssaiIndication dcni) {
        this.nssci = nssci;
        this.dcni = dcni;
    }

    @Override
    public IENetworkSlicingIndication decodeIE1(Bit4 value) {
        var res = new IENetworkSlicingIndication();
        res.nssci = ENetworkSlicingSubscriptionChangeIndication.fromValue(value.getBitI(0));
        res.dcni = EDefaultConfiguredNssaiIndication.fromValue(value.getBitI(1));
        return res;
    }

    @Override
    public int encodeIE1() {
        return nssci.intValue() | (dcni.intValue() << 1);
    }

    public static class ENetworkSlicingSubscriptionChangeIndication extends ProtocolEnum {
        public static final ENetworkSlicingSubscriptionChangeIndication NOT_CHANGED
                = new ENetworkSlicingSubscriptionChangeIndication(0b0, "Network slicing subscription not changed");
        public static final ENetworkSlicingSubscriptionChangeIndication CHANGED
                = new ENetworkSlicingSubscriptionChangeIndication(0b1, "Network slicing subscription changed");

        private ENetworkSlicingSubscriptionChangeIndication(int value, String name) {
            super(value, name);
        }

        public static ENetworkSlicingSubscriptionChangeIndication fromValue(int value) {
            return fromValueGeneric(ENetworkSlicingSubscriptionChangeIndication.class, value, null);
        }
    }

    public static class EDefaultConfiguredNssaiIndication extends ProtocolEnum {
        public static final EDefaultConfiguredNssaiIndication NOT_CREATED_FROM_DEFAULT_CONFIGURED_NSSAI
                = new EDefaultConfiguredNssaiIndication(0b0, "Requested NSSAI not created from default configured NSSAI");
        public static final EDefaultConfiguredNssaiIndication CREATED_FROM_DEFAULT_CONFIGURED_NSSAI
                = new EDefaultConfiguredNssaiIndication(0b1, "Requested NSSAI created from default configured NSSAI");

        private EDefaultConfiguredNssaiIndication(int value, String name) {
            super(value, name);
        }

        public static EDefaultConfiguredNssaiIndication fromValue(int value) {
            return fromValueGeneric(EDefaultConfiguredNssaiIndication.class, value, null);
        }
    }
}
