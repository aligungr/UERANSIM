/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.enums;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;

public class EKeyAmfChangeFlag extends ProtocolEnum {
    public static final EKeyAmfChangeFlag CALCULATED = new EKeyAmfChangeFlag(0b0, "a new K_AMF has not been calculated by the network");
    public static final EKeyAmfChangeFlag NOT_CALCULATED = new EKeyAmfChangeFlag(0b1, "a new K_AMF has been calculated by the network");

    private EKeyAmfChangeFlag(int value, String name) {
        super(value, name);
    }

    public static EKeyAmfChangeFlag fromValue(int value) {
        return fromValueGeneric(EKeyAmfChangeFlag.class, value, null);
    }
}
