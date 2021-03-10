//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mm.hpp"
#include <nas/utils.hpp>
#include <ue/app/task.hpp>
#include <ue/rrc/task.hpp>
#include <ue/sm/sm.hpp>

namespace nr::ue
{

void NasMm::handlePlmnSearchResponse(const std::string &gnbName)
{
    if (m_base->nodeListener)
        m_base->nodeListener->onConnected(app::NodeType::UE, m_base->config->getNodeName(), app::NodeType::GNB,
                                          gnbName);

    m_logger->info("UE connected to gNB");

    if (m_mmSubState == EMmSubState::MM_REGISTERED_PLMN_SEARCH ||
        m_mmSubState == EMmSubState::MM_REGISTERED_NO_CELL_AVAILABLE)
        switchMmState(EMmState::MM_REGISTERED, EMmSubState::MM_REGISTERED_NORMAL_SERVICE);
    else if (m_mmSubState == EMmSubState::MM_DEREGISTERED_PLMN_SEARCH ||
             m_mmSubState == EMmSubState::MM_DEREGISTERED_NO_CELL_AVAILABLE)
        switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NORMAL_SERVICE);

    resetRegAttemptCounter();
}

void NasMm::handlePlmnSearchFailure()
{
    if (m_mmSubState == EMmSubState::MM_REGISTERED_PLMN_SEARCH)
        switchMmState(EMmState::MM_REGISTERED, EMmSubState::MM_REGISTERED_NO_CELL_AVAILABLE);
    else if (m_mmSubState == EMmSubState::MM_DEREGISTERED_PLMN_SEARCH)
        switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NO_CELL_AVAILABLE);
}

void NasMm::handleRrcConnectionSetup()
{
    switchCmState(ECmState::CM_CONNECTED);
}

void NasMm::handleRrcConnectionRelease()
{
    switchCmState(ECmState::CM_IDLE);
}

void NasMm::handleRadioLinkFailure()
{
    m_logger->debug("Radio link failure detected");
    handleRrcConnectionRelease();
}

void NasMm::localReleaseConnection()
{
    m_logger->info("Performing local release of NAS connection");

    m_base->rrcTask->push(new NwUeNasToRrc(NwUeNasToRrc::LOCAL_RELEASE_CONNECTION));
}

} // namespace nr::ue