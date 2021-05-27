//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mm.hpp"

#include <unordered_set>

#include <lib/nas/utils.hpp>
#include <ue/nas/sm/sm.hpp>

namespace nr::ue
{

void NasMm::receiveConfigurationUpdate(const nas::ConfigurationUpdateCommand &msg)
{
    m_logger->debug("Configuration Update Command received");

    // Abnormal case: 5.4.4.5, c) Generic UE configuration update and de-registration procedure collision
    if (m_mmState == EMmState::MM_DEREGISTERED_INITIATED)
    {
        // "If the UE receives CONFIGURATION UPDATE COMMAND message after sending a DEREGISTRATION REQUEST message and
        //  the access type included in the DEREGISTRATION REQUEST message is same as the access in which the
        //  CONFIGURATION UPDATE COMMAND message is received, then the UE shall ignore the CONFIGURATION UPDATE COMMAND
        //  message and proceed with the de-registration procedure. Otherwise, the UE shall proceed with both the
        //  procedures."
        m_logger->warn("Configuration Update Command ignored because of the De-registration procedure collusion");
        return;
    }

    // Indicates there exists at least one configuration to be updated
    bool hasNewConfig = false;

    // "Upon receiving the CONFIGURATION UPDATE COMMAND message, the UE shall stop timer T3346 if running"
    m_timers->t3346.stop();

    // "If the UE receives a new 5G-GUTI in the CONFIGURATION UPDATE COMMAND message, the UE shall consider the new
    //  5G-GUTI as valid, the old 5G-GUTI as invalid, stop timer T3519 if running, and delete any stored SUCI;"
    if (msg.guti.has_value() && msg.guti->type == nas::EIdentityType::GUTI)
    {
        hasNewConfig = true;
        m_storage->storedSuci->clear();
        m_storage->storedGuti->set(*msg.guti);
        m_timers->t3519.stop();
    }

    // "If the UE receives a new TAI list in the CONFIGURATION UPDATE COMMAND message, the UE shall consider the new TAI
    //  list as valid and the old TAI list as invalid; otherwise, the UE shall consider the old TAI list as valid."
    if (msg.taiList.has_value())
    {
        if (nas::utils::TaiListSize(*msg.taiList) == 0)
        {
            m_logger->err("Invalid TAI list size");
            sendMmStatus(nas::EMmCause::SEMANTICALLY_INCORRECT_MESSAGE);
        }
        else
        {
            hasNewConfig = true;

            m_storage->taiList->set(*msg.taiList);
            updateForbiddenTaiListsForAllowedIndications();

            Tai currentTai = m_base->shCtx.getCurrentTai();
            if (currentTai.hasValue() &&
                nas::utils::TaiListContains(*msg.taiList, nas::VTrackingAreaIdentity{currentTai}))
                m_storage->lastVisitedRegisteredTai->set(currentTai);
        }
    }

    // "If the UE receives a new service area list in the CONFIGURATION UPDATE COMMAND message, the UE shall consider
    //  the new service area list as valid and the old service area list as invalid; otherwise, the UE shall consider
    //  the old service area list, if any, as valid."
    if (msg.serviceAreaList.has_value())
    {
        hasNewConfig = true;
        m_storage->serviceAreaList->set(*msg.serviceAreaList);
        updateForbiddenTaiListsForAllowedIndications();
    }

    // "If the UE receives new NITZ information in the CONFIGURATION UPDATE COMMAND message, the UE considers the new
    //  NITZ information as valid and the old NITZ information as invalid; otherwise, the UE shall consider the old NITZ
    //  information as valid."
    if (msg.networkFullName.has_value())
    {
        hasNewConfig = true;
        m_storage->networkFullName->set(nas::utils::DeepCopyIe(*msg.networkFullName));
    }
    if (msg.networkShortName.has_value())
    {
        hasNewConfig = true;
        m_storage->networkShortName->set(nas::utils::DeepCopyIe(*msg.networkShortName));
    }
    if (msg.localTimeZone.has_value())
    {
        hasNewConfig = true;
        m_storage->localTimeZone->set(*msg.localTimeZone);
    }
    if (msg.universalTimeAndLocalTimeZone.has_value())
    {
        hasNewConfig = true;
        m_storage->universalTimeAndLocalTimeZone->set(*msg.universalTimeAndLocalTimeZone);
    }
    if (msg.networkDaylightSavingTime.has_value())
    {
        hasNewConfig = true;
        m_storage->networkDaylightSavingTime->set(*msg.networkDaylightSavingTime);
    }

    // "If the UE receives a new allowed NSSAI for the associated access type in the CONFIGURATION UPDATE COMMAND
    //  message, the UE shall consider the new allowed NSSAI as valid for the associated access type, store the allowed
    //  NSSAI for the associated access type as specified in subclause 4.6.2.2 and consider the old allowed NSSAI for
    //  the associated access type as invalid; otherwise, the UE shall consider the old Allowed NSSAI as valid for the
    //  associated access type."
    if (msg.allowedNssai.has_value())
    {
        hasNewConfig = true;
        m_storage->allowedNssai->set(nas::utils::NssaiTo(*msg.allowedNssai));
    }

    // "If the UE receives a new configured NSSAI in the CONFIGURATION UPDATE COMMAND message, the UE shall consider the
    //  new configured NSSAI for the registered PLMN as valid and the old configured NSSAI for the registered PLMN as
    //  invalid; otherwise, the UE shall consider the old configured NSSAI for the registered PLMN as valid The UE shall
    //  store the new configured NSSAI as specified in subclause 4.6.2.2."
    if (msg.configuredNssai.has_value())
    {
        hasNewConfig = true;
        m_storage->configuredNssai->set(nas::utils::NssaiTo(*msg.configuredNssai));
    }

    // "If the UE receives the Network slicing indication IE in the CONFIGURATION UPDATE COMMAND message with the
    //  Network slicing subscription change indication set to "Network slicing subscription changed", the UE shall
    //  delete the network slicing information for each and every PLMN except for the current PLMN as specified in
    //  subclause 4.6.2.2."
    if (msg.networkSlicingIndication.has_value() &&
        msg.networkSlicingIndication->nssci == nas::ENetworkSlicingSubscriptionChangeIndication::CHANGED)
    {
        hasNewConfig = true;
        handleNetworkSlicingSubscriptionChange();
    }

    // "The UE receiving the rejected NSSAI in the CONFIGURATION UPDATE COMMAND message takes the following actions
    //  based on the rejection cause in the rejected NSSAI: .."
    if (msg.rejectedNssai.has_value())
    {
        hasNewConfig = true;
        for (auto &rejectedSlice : msg.rejectedNssai->list)
        {
            SingleSlice slice;
            slice.sst = rejectedSlice.sst;
            slice.sd = rejectedSlice.sd;

            auto &nssai = rejectedSlice.cause == nas::ERejectedSNssaiCause::NA_IN_PLMN ? m_storage->rejectedNssaiInPlmn
                                                                                       : m_storage->rejectedNssaiInTa;
            nssai->mutate([slice](auto &value) { value.addIfNotExists(slice); });
        }
    }

    // "If acknowledgement requested is indicated in the Configuration update indication IE in the CONFIGURATION UPDATE
    //  COMMAND message, the UE shall send a CONFIGURATION UPDATE COMPLETE message."
    if (msg.configurationUpdateIndication.has_value() &&
        msg.configurationUpdateIndication->ack == nas::EAcknowledgement::REQUESTED)
    {
        sendNasMessage(nas::ConfigurationUpdateComplete{});
    }

    // "If the CONFIGURATION UPDATE COMMAND message indicates "registration requested" in the Configuration update
    //  indication IE and:"
    if (msg.configurationUpdateIndication.has_value() &&
        msg.configurationUpdateIndication->red == nas::ERegistrationRequested::REQUESTED)
    {
        // "contains no other parameters or contains at least one of the following parameters: a new allowed NSSAI,
        //  a new configured NSSAI or the Network slicing subscription change indication, and:"
        if (!hasNewConfig || (msg.allowedNssai.has_value() || msg.configuredNssai.has_value() ||
                              msg.networkSlicingIndication.has_value()))
        {
            if (m_sm->anyEmergencySession()) // "an emergency PDU session exists,"
            {
                // "the UE shall, after the completion of the generic UE configuration
                //  update procedure and after the emergency PDU session is released, release the existing N1 NAS
                //  signalling connection, and start a registration procedure for mobility and periodic registration
                //  update as specified in subclause 5.5.1.3;"
                // TODO
            }
            else // "no emergency PDU Session exists,"
            {
                // "the UE shall, after the completion of the generic UE configuration
                //  update procedure, and the release of the existing N1 NAS signalling connection, start a registration
                //  procedure for mobility and periodic registration update as specified in subclause 5.5.1.3;"
                // TODO
            }
        }

        if (msg.micoIndication.has_value() && !msg.allowedNssai.has_value() && !msg.configuredNssai.has_value())
        {
            // "an MICO indication is included without a new allowed NSSAI or a new configured NSSAI, the UE shall,
            //  after the completion of the generic UE configuration update procedure, start a registration procedure
            //  for mobility and registration update as specified in subclause 5.5.1.3 to re-negotiate MICO mode with
            //  the network."
            // TODO
        }
    }
}

void NasMm::updateForbiddenTaiListsForAllowedIndications()
{
    // "A tracking area shall be removed from the list of "5GS forbidden tracking areas for roaming", as well as the
    // list of "5GS forbidden tracking areas for regional provision of service", if the UE receives the tracking area in
    // the TAI list or the Service area list of "allowed tracking areas" in REGISTRATION ACCEPT message or a
    // CONFIGURATION UPDATE COMMAND message. The UE shall not remove the tracking area from "5GS forbidden tracking
    // areas for roaming" or "5GS forbidden tracking areas for regional provision of service" if the UE is registered
    // for emergency services"

    std::unordered_set<Tai> taiSet;

    m_storage->forbiddenTaiListRoaming->forEach([&taiSet, this](auto &value) {
        if (nas::utils::TaiListContains(m_storage->taiList->get(), nas::VTrackingAreaIdentity{value}))
            taiSet.insert(value);
        if (nas::utils::ServiceAreaListAllowsTai(m_storage->serviceAreaList->get(), nas::VTrackingAreaIdentity{value}))
            taiSet.insert(value);
    });

    m_storage->forbiddenTaiListRps->forEach([&taiSet, this](auto &value) {
        if (nas::utils::TaiListContains(m_storage->taiList->get(), nas::VTrackingAreaIdentity{value}))
            taiSet.insert(value);
        if (nas::utils::ServiceAreaListAllowsTai(m_storage->serviceAreaList->get(), nas::VTrackingAreaIdentity{value}))
            taiSet.insert(value);
    });

    for (auto &tai : taiSet)
    {
        m_storage->forbiddenTaiListRoaming->remove(tai);
        m_storage->forbiddenTaiListRps->remove(tai);
    }
}

} // namespace nr::ue
