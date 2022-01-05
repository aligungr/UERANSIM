//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "layer.hpp"

namespace nr::ue
{

NasLayer::NasLayer(UeTask *base) : m_ue{base}, m_timers{}
{
    m_logger = base->logBase->makeUniqueLogger(base->config->getLoggerPrefix() + "nas");

    m_mm = new NasMm(base, &m_timers);
    m_sm = new NasSm(base, &m_timers);
    m_usim = new Usim();
}

void NasLayer::onStart()
{
    m_usim->initialize(m_ue->config->supi.has_value());

    m_sm->onStart(m_mm);
    m_mm->onStart(m_sm, m_usim);
}

void NasLayer::onQuit()
{
    m_mm->onQuit();
    m_sm->onQuit();

    delete m_mm;
    delete m_sm;

    delete m_usim;
}

void NasLayer::performCycle()
{
    UeTimer *const arr[] = {
        &m_timers.t3346, &m_timers.t3396, &m_timers.t3444, &m_timers.t3445, &m_timers.t3502, &m_timers.t3510,
        &m_timers.t3511, &m_timers.t3512, &m_timers.t3516, &m_timers.t3517, &m_timers.t3519, &m_timers.t3520,
        &m_timers.t3521, &m_timers.t3525, &m_timers.t3540, &m_timers.t3584, &m_timers.t3585,
    };

    for (auto *timer : arr)
    {
        if (timer->performTick())
        {
            if (timer->isMmTimer())
                m_mm->onTimerExpire(*timer);
            else
                m_sm->onTimerExpire(*timer);
        }
    }

    m_sm->onTimerTick();

    m_mm->performMmCycle();
}

void NasLayer::handleRrcConnectionSetup()
{
    m_mm->handleRrcConnectionSetup();
}

void NasLayer::handleRrcConnectionRelease()
{
    m_mm->handleRrcConnectionRelease();
}

void NasLayer::handlePaging(const std::vector<GutiMobileIdentity> &tmsiIds)
{
    m_mm->handlePaging(tmsiIds);
}

void NasLayer::handleRrcFallbackIndication()
{
    m_mm->handleRrcFallbackIndication();
}

void NasLayer::handleRadioLinkFailure()
{
    m_mm->handleRadioLinkFailure();
}

void NasLayer::handleRrcEstablishmentFailure()
{
    m_mm->handleRrcEstablishmentFailure();
}

void NasLayer::handleActiveCellChange(const Tai &prevTai)
{
    m_mm->handleActiveCellChange(prevTai);
}

void NasLayer::handleNasDelivery(const OctetString &data)
{
    OctetView buffer{data};
    auto nasMessage = nas::DecodeNasMessage(buffer);
    if (nasMessage != nullptr)
        m_mm->receiveNasMessage(*nasMessage);
}

void NasLayer::handleUplinkDataRequest(int psi, OctetString &&data)
{
    m_sm->handleUplinkDataRequest(psi, std::move(data));
}

void NasLayer::handleDownlinkDataRequest(int psi, OctetString &&data)
{
    m_sm->handleDownlinkDataRequest(psi, std::move(data));
}

} // namespace nr::ue