//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "ue_nas_task.hpp"
#include "ue_nts.hpp"

static const int NTS_TIMER_ID_NAS_TIMER_CYCLE = 1;
static const int NTS_TIMER_INTERVAL_NAS_TIMER_CYCLE = 1000;

namespace nr::ue
{

NasTask::NasTask(TaskBase *base)
    : base{base}, timers{}, mmCtx{}, smCtx{}, currentNsCtx{}, nonCurrentNsCtx{},
      emulationMode(base->config->emulationMode)
{
    logger = base->logBase->makeUniqueLogger("nas");
}

void NasTask::onStart()
{
    logger->debug("NAS layer started");

    // TODO: initial status update

    setTimer(NTS_TIMER_ID_NAS_TIMER_CYCLE, NTS_TIMER_INTERVAL_NAS_TIMER_CYCLE);
    triggerMmCycle();
}

void NasTask::onQuit()
{
    // TODO
}

void NasTask::onLoop()
{
    NtsMessage *msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::UE_DOWNLINK_NAS_DELIVERY: {
        auto *w = dynamic_cast<NwDownlinkNasDelivery *>(msg);
        OctetString nasPdu = std::move(w->nasPdu);
        delete w;
        OctetBuffer buffer{nasPdu};
        std::unique_ptr<nas::NasMessage> nasMessage = nas::DecodeNasMessage(buffer);
        receiveNasMessage(*nasMessage);
        break;
    }
    case NtsMessageType::NAS_TIMER_EXPIRE: {
        auto *w = dynamic_cast<NwNasTimerExpire *>(msg);
        nas::NasTimer *timer = w->timer;
        delete w;
        onTimerExpire(*timer);
        break;
    }
    case NtsMessageType::UE_EXTERNAL_COMMAND: {
        // TODO
        delete msg;
        break;
    }
    case NtsMessageType::UE_MR_PLMN_SEARCH_RESPONSE: {
        auto *w = dynamic_cast<NwPlmnSearchResponse *>(msg);
        receivePlmnSearchResponse(*w);
        delete w;
        break;
    }
    case NtsMessageType::TIMER_EXPIRED: {
        auto *w = dynamic_cast<NwTimerExpired *>(msg);
        int timerId = w->timerId;
        delete w;
        if (timerId == NTS_TIMER_ID_NAS_TIMER_CYCLE)
        {
            setTimer(NTS_TIMER_ID_NAS_TIMER_CYCLE, NTS_TIMER_INTERVAL_NAS_TIMER_CYCLE);
            performTick();
        }
        break;
    }
    case NtsMessageType::NAS_PERFORM_MM_CYCLE: {
        delete msg;
        performMmCycle();
        break;
    }
    default:
        logger->err("Unhandled NTS message received with type %d", (int)msg->msgType);
        delete msg;
        break;
    }
}

void NasTask::receiveEapSuccessMessage(const eap::Eap &eap)
{
    // do nothing
}

void NasTask::receiveEapFailureMessage(const eap::Eap &eap)
{
    logger->err("EAP failure received. Deleting non-current NAS security context");
    nonCurrentNsCtx = {};
}

void NasTask::receiveEapResponseMessage(const eap::Eap &eap)
{
    if (eap.eapType == eap::EEapType::EAP_AKA_PRIME)
    {
        // TODO
    }
    else
    {
        logger->err("Unhandled EAP Response message type");
    }
}

void NasTask::receiveAuthenticationResponse(const nas::AuthenticationResponse &msg)
{
    if (msg.eapMessage.has_value())
    {
        if (msg.eapMessage->eap->code == eap::ECode::RESPONSE)
            receiveEapResponseMessage(*msg.eapMessage->eap);
        else
            logger->warn("Network sent EAP with an inconvenient type in Authentication Response, ignoring EAP IE.");
    }
}

void NasTask::receiveAuthenticationResult(const nas::AuthenticationResult &msg)
{
    if (msg.abba.has_value())
        nonCurrentNsCtx->keys.abba = msg.abba->rawData.copy();

    if (msg.eapMessage.eap->code == eap::ECode::SUCCESS)
        receiveEapSuccessMessage(*msg.eapMessage.eap);
    else if (msg.eapMessage.eap->code == eap::ECode::FAILURE)
        receiveEapFailureMessage(*msg.eapMessage.eap);
    else
        logger->warn("Network sent EAP with an inconvenient type in Authentication Result, ignoring EAP IE.");
}

void NasTask::receiveAuthenticationReject(const nas::AuthenticationReject &msg)
{
    logger->err("Authentication Reject received.");

    if (msg.eapMessage.has_value())
    {
        if (msg.eapMessage->eap->code == eap::ECode::FAILURE)
        {
            mmCtx.storedGuti = {};
            mmCtx.taiList = {};
            mmCtx.lastVisitedRegisteredTai = {};
            currentNsCtx = {};
            nonCurrentNsCtx = {};

            receiveEapFailureMessage(*msg.eapMessage->eap);
        }
        else
        {
            logger->warn("Network sent EAP with inconvenient type in AuthenticationReject, ignoring EAP IE.");
        }
    }
}

} // namespace nr::ue