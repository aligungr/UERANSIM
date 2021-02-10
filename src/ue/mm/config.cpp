//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mm.hpp"

namespace nr::ue
{

void NasMm::receiveConfigurationUpdate(const nas::ConfigurationUpdateCommand &msg)
{
    m_timers->t3346.stop();

    if (msg.guti.has_value() && msg.guti->type == nas::EIdentityType::GUTI)
    {
        m_storedGuti = msg.guti.value();
        m_storedSuci = {};
        m_timers->t3519.stop();
    }

    if (msg.taiList.has_value())
        m_taiList = msg.taiList.value();

    if (msg.configurationUpdateIndication.has_value())
    {
        if (msg.configurationUpdateIndication.has_value())
        {
            if (msg.configurationUpdateIndication->red == nas::ERegistrationRequested::REQUESTED)
            {
                // TODO
            }
            if (msg.configurationUpdateIndication->ack == nas::EAcknowledgement::REQUESTED)
            {
                nas::ConfigurationUpdateComplete resp;
                sendNasMessage(resp);
            }
        }
    }
}

} // namespace nr::ue
