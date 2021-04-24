//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mm.hpp"

#include <lib/nas/utils.hpp>

namespace nr::ue
{

void NasMm::receiveConfigurationUpdate(const nas::ConfigurationUpdateCommand &msg)
{
    // Indicates there exists at least one configuration to be updated
    bool hasNewConfig = false;

    // "Upon receiving the CONFIGURATION UPDATE COMMAND message, the UE shall stop timer T3346 if running"
    m_timers->t3346.stop();

    // "If the UE receives a new 5G-GUTI in the CONFIGURATION UPDATE COMMAND message, the UE shall consider the new
    //  5G-GUTI as valid, the old 5G-GUTI as invalid, stop timer T3519 if running, and delete any stored SUCI;"
    if (msg.guti.has_value() && msg.guti->type == nas::EIdentityType::GUTI)
    {
        hasNewConfig = true;
        m_usim->m_storedSuci = {};
        m_usim->m_storedGuti = *msg.guti;
        m_timers->t3519.stop();
    }

    // "If the UE receives a new TAI list in the CONFIGURATION UPDATE COMMAND message, the UE shall consider the new TAI
    //  list as valid and the old TAI list as invalid; otherwise, the UE shall consider the old TAI list as valid."
    if (msg.taiList.has_value())
    {
        hasNewConfig = true;
        m_usim->m_taiList = *msg.taiList;
    }

    // "If the UE receives a new service area list in the CONFIGURATION UPDATE COMMAND message, the UE shall consider
    // the
    //  new service area list as valid and the old service area list as invalid; otherwise, the UE shall consider the
    //  old service area list, if any, as valid."
    if (msg.serviceAreaList.has_value())
    {
        hasNewConfig = true;
        m_usim->m_serviceAreaList = *msg.serviceAreaList;
    }

    // "If the UE receives new NITZ information in the CONFIGURATION UPDATE COMMAND message, the UE considers the new
    //  NITZ information as valid and the old NITZ information as invalid; otherwise, the UE shall consider the old NITZ
    //  information as valid."
    if (msg.networkFullName.has_value())
    {
        hasNewConfig = true;
        m_usim->m_networkFullName = nas::utils::DeepCopyIe(*msg.networkFullName);
    }
    if (msg.networkShortName.has_value())
    {
        hasNewConfig = true;
        m_usim->m_networkShortName = nas::utils::DeepCopyIe(*msg.networkShortName);
    }
    if (msg.localTimeZone.has_value())
    {
        hasNewConfig = true;
        m_usim->m_localTimeZone = *msg.localTimeZone;
    }
    if (msg.universalTimeAndLocalTimeZone.has_value())
    {
        hasNewConfig = true;
        m_usim->m_universalTimeAndLocalTimeZone = *msg.universalTimeAndLocalTimeZone;
    }
    if (msg.networkDaylightSavingTime.has_value())
    {
        hasNewConfig = true;
        m_usim->m_networkDaylightSavingTime = *msg.networkDaylightSavingTime;
    }

    // "If the UE receives a new allowed NSSAI for the associated access type in the CONFIGURATION UPDATE COMMAND
    //  message, the UE shall consider the new allowed NSSAI as valid for the associated access type, store the allowed
    //  NSSAI for the associated access type as specified in subclause 4.6.2.2 and consider the old allowed NSSAI for
    //  the associated access type as invalid; otherwise, the UE shall consider the old Allowed NSSAI as valid for the
    //  associated access type."
    if (msg.allowedNssai.has_value())
    {
        hasNewConfig = true;
        m_usim->m_allowedNssai = nas::utils::NssaiTo(*msg.allowedNssai);
    }

    // "If the UE receives a new configured NSSAI in the CONFIGURATION UPDATE COMMAND message, the UE shall consider the
    //  new configured NSSAI for the registered PLMN as valid and the old configured NSSAI for the registered PLMN as
    //  invalid; otherwise, the UE shall consider the old configured NSSAI for the registered PLMN as valid The UE shall
    //  store the new configured NSSAI as specified in subclause 4.6.2.2."
    if (msg.configuredNssai.has_value())
    {
        hasNewConfig = true;
        m_usim->m_configuredNssai = nas::utils::NssaiTo(*msg.configuredNssai);
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
            SingleSlice slice{};
            slice.sst = rejectedSlice.sst;
            slice.sd = rejectedSlice.sd;

            auto &list = rejectedSlice.cause == nas::ERejectedSNssaiCause::NA_IN_PLMN ? m_usim->m_rejectedNssaiInPlmn
                                                                                      : m_usim->m_rejectedNssaiInTa;
            list.addIfNotExists(slice);
        }
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
            if (hasEmergency()) // "an emergency PDU session exists,"
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

    // "If acknowledgement requested is indicated in the Configuration update indication IE in the CONFIGURATION UPDATE
    //  COMMAND message, the UE shall send a CONFIGURATION UPDATE COMPLETE message."
    if (msg.configurationUpdateIndication.has_value() &&
        msg.configurationUpdateIndication->ack == nas::EAcknowledgement::REQUESTED)
    {
        sendNasMessage(nas::ConfigurationUpdateComplete{});
    }
}

} // namespace nr::ue
