#include "layer.hpp"

namespace nr::ue
{

NasLayer::NasLayer(TaskBase *base) : base{base}, timers{}
{
    logger = base->logBase->makeUniqueLogger(base->config->getLoggerPrefix() + "nas");

    mm = new NasMm(base, &timers);
    sm = new NasSm(base, &timers);
    usim = new Usim();
}

void NasLayer::onStart()
{
    usim->initialize(base->config->supi.has_value());

    sm->onStart(mm);
    mm->onStart(sm, usim);
}

void NasLayer::onQuit()
{
    mm->onQuit();
    sm->onQuit();

    delete mm;
    delete sm;

    delete usim;
}

void NasLayer::handleSapMessage(std::unique_ptr<NtsMessage> msg)
{
    switch (msg->msgType)
    {
    case NtsMessageType::UE_RRC_TO_NAS: {
        mm->handleRrcEvent(dynamic_cast<NmUeRrcToNas &>(*msg));
        break;
    }
    case NtsMessageType::UE_TUN_TO_APP: {
        auto &w = dynamic_cast<NmUeTunToApp &>(*msg);
        switch (w.present)
        {
        case NmUeTunToApp::DATA_PDU_DELIVERY: {
            sm->handleUplinkDataRequest(w.psi, std::move(w.data));
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
            sm->handleDownlinkDataRequest(w.psi, std::move(w.pdu));
            break;
        }
        }

        break;
    }
    default:
        logger->unhandledNts(*msg);
        break;
    }
}

void NasLayer::performTick()
{
    std::array<UeTimer *, 17> arr = {
        &timers.t3346, &timers.t3396, &timers.t3444, &timers.t3445, &timers.t3502, &timers.t3510,
        &timers.t3511, &timers.t3512, &timers.t3516, &timers.t3517, &timers.t3519, &timers.t3520,
        &timers.t3521, &timers.t3525, &timers.t3540, &timers.t3584, &timers.t3585,
    };

    for (auto* timer : arr){
        if (timer->performTick())
        {
            if (timer->isMmTimer())
                mm->onTimerExpire(*timer);
            else
                sm->onTimerExpire(*timer);
        }
    }

    sm->onTimerTick();
}

void NasLayer::performCycle()
{
    mm->performMmCycle();
}

} // namespace nr::ue