//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "mm.hpp"

#include <sstream>

#include <lib/nas/utils.hpp>
#include <ue/nas/sm/sm.hpp>
#include <ue/rrc/task.hpp>

#include <asn/rrc/ASN_RRC_EstablishmentCause.h>

namespace nr::ue
{

static std::string AccessIdentitiesToString(const std::bitset<16> &ais)
{
    bool first = true;
    std::stringstream ss;
    for (size_t i = 0; i < ais.size(); i++)
    {
        if (ais[i])
        {
            if (!first)
                ss << ", ";
            ss << i;
            first = false;
        }
    }
    return ss.str();
}

static std::string AccessCategoryToString(int n)
{
    if (n >= 32 && n <= 63)
        return "operator defined[" + std::to_string(n) + "]";

    switch (n)
    {
    case 0:
        return "MT_acc";
    case 1:
        return "delay tolerant";
    case 2:
        return "emergency";
    case 3:
        return "MO_sig";
    case 4:
        return "MO MMTel voice";
    case 5:
        return "MO MMTel video";
    case 6:
        return "MO SMS and SMSoIP";
    case 7:
        return "MO_data";
    default:
        return "?";
    }
}

bool NasMm::hasEmergency()
{
    // Indicates emergency services are required (even if registered for normal initial registration)
    // This happens if it 'has' or 'need' some emergency PDU Session, as well.
    if (m_rmState == ERmState::RM_REGISTERED && m_registeredForEmergency)
        return true;

    if (m_mmState == EMmState::MM_REGISTERED_INITIATED && m_lastRegistrationRequest &&
        m_lastRegistrationRequest->registrationType.registrationType == nas::ERegistrationType::EMERGENCY_REGISTRATION)
        return true;

    if (m_procCtl.initialRegistration && m_procCtl.initialRegistration == EInitialRegCause::EMERGENCY_SERVICES)
        return true;

    if (m_procCtl.mobilityRegistration && m_procCtl.mobilityRegistration == ERegUpdateCause::EMERGENCY_CASE)
        return true;

    if (m_procCtl.serviceRequest && m_procCtl.serviceRequest == EServiceReqCause::EMERGENCY_FALLBACK)
        return true;

    if (m_sm->anyEmergencySession())
        return true;

    if (m_usim->m_emgIndication)
        return true;

    return false;
}

bool NasMm::isHighPriority()
{
    auto &acc = m_base->config->uacAcc;
    return acc.cls11 || acc.cls12 || acc.cls13 || acc.cls14 || acc.cls15;
}

void NasMm::setN1Capability(bool enabled)
{
    // TODO
}

bool NasMm::isInNonAllowedArea()
{
    auto currentCell = m_base->shCtx.currentCell.get();
    if (!currentCell.hasValue())
        return false;

    auto plmn = currentCell.plmn;
    if (nas::utils::ServiceAreaListForbidsPlmn(m_storage->serviceAreaList->get(), nas::utils::PlmnFrom(plmn)))
        return true;

    int tac = currentCell.tac;
    if (nas::utils::ServiceAreaListForbidsTai(m_storage->serviceAreaList->get(),
                                              nas::VTrackingAreaIdentity{nas::utils::PlmnFrom(plmn), octet3{tac}}))
    {
        return true;
    }
    return false;
}

EUacResult NasMm::performUac()
{
    auto accessIdentities = [this]() {
        std::bitset<16> ais;

        auto currentPlmn = m_base->shCtx.getCurrentPlmn();

        if (m_base->config->uacAic.mps && m_rmState == ERmState::RM_REGISTERED && currentPlmn.hasValue())
        {
            if (currentPlmn == m_base->config->hplmn || m_storage->equivalentPlmnList->contains(currentPlmn) ||
                currentPlmn.mcc == m_base->config->hplmn.mcc)
                ais[1] = true;
        }

        if (m_base->config->uacAic.mcs && m_rmState == ERmState::RM_REGISTERED && currentPlmn.hasValue())
        {
            if (currentPlmn == m_base->config->hplmn || m_storage->equivalentPlmnList->contains(currentPlmn) ||
                currentPlmn.mcc == m_base->config->hplmn.mcc)
                ais[2] = true;
        }

        if (m_nwFeatureSupport)
        {
            if (m_nwFeatureSupport->mpsi == nas::EMpsIndicator::SUPPORTED)
                ais[1] = true;
            if (m_nwFeatureSupport->mcsi && m_nwFeatureSupport->mcsi == nas::EMcsIndicator::VALID)
                ais[2] = true;
        }

        if (currentPlmn.hasValue() &&
            (currentPlmn == m_base->config->hplmn || m_storage->equivalentPlmnList->contains(currentPlmn)))
        {
            if (m_base->config->uacAcc.cls11)
                ais[11] = true;
            if (m_base->config->uacAcc.cls15)
                ais[15] = true;
        }

        if (currentPlmn.hasValue() &&
            (currentPlmn == m_base->config->hplmn || currentPlmn.mcc == m_base->config->hplmn.mcc))
        {
            if (m_base->config->uacAcc.cls12)
                ais[12] = true;
            if (m_base->config->uacAcc.cls13)
                ais[13] = true;
            if (m_base->config->uacAcc.cls14)
                ais[14] = true;
        }

        if (ais.none())
            ais[0] = true;

        return ais;
    }();

    auto accessCategory = [this]() {
        /* Check for Rule #1 */
        if (m_procCtl.mobilityRegistration)
        {
            if (m_procCtl.mobilityRegistration == ERegUpdateCause::PAGING_OR_NOTIFICATION)
            {
                return 0;
            }
        }

        if (m_procCtl.serviceRequest)
        {
            if (m_procCtl.serviceRequest == EServiceReqCause::IDLE_PAGING ||
                m_procCtl.serviceRequest == EServiceReqCause::CONNECTED_3GPP_NOTIFICATION_N3GPP ||
                m_procCtl.serviceRequest == EServiceReqCause::IDLE_3GPP_NOTIFICATION_N3GPP)
            {
                return 0;
            }
        }

        /* Check for Rule #2 */
        if (hasEmergency())
            return 2;

        /* Check for Rule #3 */
        // TODO: Operator defined access category not supported

        /* Check for Rule #4 */
        // TODO: Access attempt for delay tolerant service

        /* Check for Rule #5 */
        // TODO: MO MMTel voice call

        /* Check for Rule #6 */
        // TODO: MO MMTel video call

        /* Check for Rule #7 */
        // TODO: MO SMS over NAS or MO SMSoIP

        /* Check for Rule #8 */
        if (hasPendingProcedure() && !m_sm->anyUplinkDataPending())
            return 3;

        /* Check for Rule #9 */
        if (hasPendingProcedure() && m_sm->anyUplinkDataPending())
            return 7;

        /* Check for Rule #10 */
        if (m_sm->anyUplinkDataPending())
            return 7;

        return 3;
    }();

    auto establishmentCause = [&accessIdentities, accessCategory]() {
        if (accessIdentities[1])
            return ASN_RRC_EstablishmentCause_mps_PriorityAccess;
        if (accessIdentities[2])
            return ASN_RRC_EstablishmentCause_mcs_PriorityAccess;
        if (accessIdentities[11] || accessIdentities[15])
            return ASN_RRC_EstablishmentCause_highPriorityAccess;
        if (accessIdentities[12] || accessIdentities[13] || accessIdentities[14])
            return ASN_RRC_EstablishmentCause_highPriorityAccess;

        ASN_RRC_EstablishmentCause categoryMapping[] = {
            ASN_RRC_EstablishmentCause_mt_Access,    ASN_RRC_EstablishmentCause_mo_Signalling,
            ASN_RRC_EstablishmentCause_emergency,    ASN_RRC_EstablishmentCause_mo_Signalling,
            ASN_RRC_EstablishmentCause_mo_VoiceCall, ASN_RRC_EstablishmentCause_mo_VideoCall,
            ASN_RRC_EstablishmentCause_mo_SMS,       ASN_RRC_EstablishmentCause_mo_Data};

        if (accessCategory == 1)
        {
            // TODO: RRC establishment cause not applicable
            return ASN_RRC_EstablishmentCause_mt_Access;
        }

        if (accessCategory >= 0 && accessCategory <= 7)
            return categoryMapping[accessCategory];

        return ASN_RRC_EstablishmentCause_mt_Access;
    }();

    auto uacInput = std::make_unique<UacInput>();
    uacInput->identities = accessIdentities;
    uacInput->category = accessCategory;
    uacInput->establishmentCause = static_cast<int>(establishmentCause);

    auto uacCtl = LightSync<UacInput, UacOutput>::MakeShared(100, 0, std::move(uacInput));

    auto w = std::make_unique<NmUeNasToRrc>(NmUeNasToRrc::PERFORM_UAC);
    w->uacCtl = uacCtl;
    m_base->rrcTask->push(std::move(w));

    auto uacOutput = uacCtl->waitForProcess();

    if (uacOutput == nullptr)
    {
        m_logger->err("No response from RRC from UAC checks, considering access attempt is barred");
        return EUacResult::BARRED;
    }

    auto res = uacOutput->res;

    switch (res)
    {
    case EUacResult::ALLOWED:
        m_logger->debug("UAC access attempt is allowed for identity[%s], category[%s]",
                        AccessIdentitiesToString(accessIdentities).c_str(),
                        AccessCategoryToString(accessCategory).c_str());
        return EUacResult::ALLOWED;
    case EUacResult::BARRED:
        m_logger->err("UAC access attempt is barred for identity[%s], category[%s]",
                      AccessIdentitiesToString(accessIdentities).c_str(),
                      AccessCategoryToString(accessCategory).c_str());
        return EUacResult::BARRED;
    case EUacResult::BARRING_APPLICABLE_EXCEPT_0_2:
        if (accessCategory != 0 && accessCategory != 2)
        {
            m_logger->err("UAC access barring is applicable except category [0] and [2]");
            return EUacResult::BARRING_APPLICABLE_EXCEPT_0_2;
        }
        return EUacResult::ALLOWED;
    default:
        // Should never reach here
        return EUacResult::BARRED;
    }
}

} // namespace nr::ue
