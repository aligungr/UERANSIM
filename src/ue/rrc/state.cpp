//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "layer.hpp"

#include <lib/asn/utils.hpp>
#include <lib/rrc/encode.hpp>
#include <ue/task.hpp>

namespace nr::ue
{

void UeRrcLayer::triggerCycle()
{
    m_base->l23Task->push(std::make_unique<NmCycleRequired>());
}

void UeRrcLayer::performCycle()
{
    if (m_state == ERrcState::RRC_CONNECTED)
    {
    }
    else if (m_state == ERrcState::RRC_IDLE)
    {
        performCellSelection();
    }
    else if (m_state == ERrcState::RRC_INACTIVE)
    {
        performCellSelection();
    }
}

void UeRrcLayer::switchState(ERrcState state)
{
    ERrcState oldState = m_state;
    m_state = state;

    m_logger->info("UE switches to state [%s]", ToJson(state).str().c_str());

    if (m_base->nodeListener)
    {
        m_base->nodeListener->onSwitch(app::NodeType::UE, m_base->config->getNodeName(), app::StateType::RRC,
                                       ToJson(oldState).str(), ToJson(state).str());
    }

    onSwitchState(oldState, state);
}

void UeRrcLayer::onSwitchState(ERrcState oldState, ERrcState newState)
{
}

} // namespace nr::ue