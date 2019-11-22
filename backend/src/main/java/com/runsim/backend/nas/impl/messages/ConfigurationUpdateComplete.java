package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainMmMessage;
import com.runsim.backend.nas.impl.enums.EMessageType;

public class ConfigurationUpdateComplete extends PlainMmMessage {

    public ConfigurationUpdateComplete() {
        super(EMessageType.CONFIGURATION_UPDATE_COMPLETE);
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);
    }
}
