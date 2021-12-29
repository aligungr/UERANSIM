#include "layer.hpp"

namespace nr::ue
{

NasLayer::NasLayer(TaskBase *base) : m_base{base}, m_timers{}
{
    m_logger = base->logBase->makeUniqueLogger(base->config->getLoggerPrefix() + "nas");

    m_mm = new NasMm(base, &m_timers);
    m_sm = new NasSm(base, &m_timers);
    m_usim = new Usim();
}

void NasLayer::onStart()
{
    m_usim->initialize(m_base->config->supi.has_value());

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

void NasLayer::handleSapMessage(std::unique_ptr<NtsMessage> msg)
{
    switch (msg->msgType)
    {
    case NtsMessageType::UE_RRC_TO_NAS: {
        m_mm->handleRrcEvent(dynamic_cast<NmUeRrcToNas &>(*msg));
        break;
    }
    case NtsMessageType::UE_TUN_TO_APP: {
        auto &w = dynamic_cast<NmUeTunToApp &>(*msg);
        switch (w.present)
        {
        case NmUeTunToApp::DATA_PDU_DELIVERY: {
            m_sm->handleUplinkDataRequest(w.psi, std::move(w.data));
            break;
        }
        default:
            break;
        }
        break;
    }
    case NtsMessageType::UE_RLS_TO_NAS: {
        auto &w = dynamic_cast<NmUeRlsToNas &>(*msg);
        switch (w.present)
        {
        case NmUeRlsToNas::DATA_PDU_DELIVERY: {
            m_sm->handleDownlinkDataRequest(w.psi, std::move(w.pdu));
            break;
        }
        }

        break;
    }
    default:
        m_logger->unhandledNts(*msg);
        break;
    }
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

} // namespace nr::ue