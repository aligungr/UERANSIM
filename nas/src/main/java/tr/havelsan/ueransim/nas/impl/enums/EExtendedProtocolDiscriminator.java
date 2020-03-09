package tr.havelsan.ueransim.nas.impl.enums;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;

public class EExtendedProtocolDiscriminator extends ProtocolEnum {
    public static final EExtendedProtocolDiscriminator MOBILITY_MANAGEMENT_MESSAGES
            = new EExtendedProtocolDiscriminator(0b01111110, "5GS mobility management messages");
    public static final EExtendedProtocolDiscriminator SESSION_MANAGEMENT_MESSAGES
            = new EExtendedProtocolDiscriminator(0b00101110, "5GS session management messages");

    private EExtendedProtocolDiscriminator(int value, String name) {
        super(value, name);
    }

    public static EExtendedProtocolDiscriminator fromValue(int value) {
        return fromValueGeneric(EExtendedProtocolDiscriminator.class, value, null);
    }
}
