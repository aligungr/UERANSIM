//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mm.hpp"

#include <lib/nas/utils.hpp>
#include <ue/nas/sm/sm.hpp>

namespace nr::ue
{

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

std::bitset<16> NasMm::determineAccessIdentities()
{
    std::bitset<16> ais;

    auto currentPlmn = m_base->shCtx.getCurrentPlmn();

    if (m_base->config->uacAic.mps && m_rmState == ERmState::RM_REGISTERED && currentPlmn.hasValue())
    {
        if (currentPlmn == m_base->config->hplmn || m_storage->equivalentPlmnList->contains(currentPlmn) ||
            currentPlmn.mcc == m_base->config->hplmn.mcc)
        {
            ais[1] = true;
        }
    }

    if (m_base->config->uacAic.mcs && m_rmState == ERmState::RM_REGISTERED && currentPlmn.hasValue())
    {
        if (currentPlmn == m_base->config->hplmn || m_storage->equivalentPlmnList->contains(currentPlmn) ||
            currentPlmn.mcc == m_base->config->hplmn.mcc)
        {
            ais[2] = true;
        }
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
        ais[11] = true;
        ais[15] = true;
    }

    if (currentPlmn.hasValue() &&
        (currentPlmn == m_base->config->hplmn || currentPlmn.mcc == m_base->config->hplmn.mcc))
    {
        ais[12] = true;
        ais[13] = true;
        ais[14] = true;
    }

    if (ais.none())
        ais[0] = true;

    return ais;
}

int NasMm::determineAccessCategory()
{
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

    return 0;
}

} // namespace nr::ue
