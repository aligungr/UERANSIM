package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainMmMessage;
import com.runsim.backend.nas.impl.enums.EExtendedProtocolDiscriminator;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.enums.ESecurityHeaderType;

public class ConfigurationUpdateComplete extends PlainMmMessage {

    public ConfigurationUpdateComplete() {
        super.extendedProtocolDiscriminator = EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES;
        super.securityHeaderType = ESecurityHeaderType.NOT_PROTECTED;
        super.messageType = EMessageType.CONFIGURATION_UPDATE_COMPLETE;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);
    }
}
