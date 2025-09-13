#include "state_learner.hpp"

#include <lib/nas/utils.hpp>
#include <ue/nas/task.hpp>
#include <ue/rrc/task.hpp>
#include <pthread.h>
#include <netinet/in.h>
#include <unistd.h>
#include <string>
#include <iostream>
#include <unordered_map>
#include <ue/nas/mm/dereg.cpp>
#include <lib/nas/encode.hpp>
#include <chrono>
#include <sys/un.h>
#include <fstream>

namespace nr::ue
{

UeStateLearner *state_learner;

int FLAG_SECMOD = 0;
bool FLAG_REPLAY = 0;
bool SMC_SENT = 0;
int port;

void UeStateLearner::startThread() 
{
    // set a seed for mutation
    srand(time(NULL));

    connfd = -1;
    pthread_t thread;
    pthread_create(&thread, NULL, start_socket, NULL);
    pthread_detach(thread);
}

void UeStateLearner::execute_command(std::string msg) 
{
    size_t index = msg.find("_");
    std::string msg_str = msg.substr(0, index);
    std::string sub_str = msg.substr(index + 1);

    if (msgMap.count(msg_str) == 0) 
    {
        notify_response("Unknown message name");
        return;
    }

    // set security_mode and replay flag
    FLAG_SECMOD = 0;
    FLAG_REPLAY = 0;

    NasMm* mm = m_base->nasTask->mm;
    MsgType msgType = msgMap[msg_str];

    if (!enableFuzzing) 
    {
        if (shtMap.count(sub_str) != 0) 
        {
            ShtType shtType = shtMap[sub_str];

            // set security
            switch (shtType)
            {
            case ShtType::nosec:
                FLAG_SECMOD = 1;
                break;
            case ShtType::intonly:
                FLAG_SECMOD = 2;
                break;
            case ShtType::_protected:
                FLAG_SECMOD = 3;
                break;
            case ShtType::replay:
                FLAG_REPLAY = 1;
                break;
            
            default:
                FLAG_SECMOD = 0;
                FLAG_REPLAY = 0;
                break;
            }
        }

        // send message
        switch (msgType)
        {
        case MsgType::registrationRequestIMSI:
            std::cout << "sending registrationRequestIMSI" << std::endl;
            // should get this message when UE starts
            // assume initial registration
            if (m_base->nasTask->mm->m_mmSubState != EMmSubState::MM_REGISTERED_NORMAL_SERVICE)
                mm->sendNasMessage(registrationRequestIMSI);
            else
                notify_response("null_action");
            break;
        case MsgType::registrationRequestGUTI: {
            std::cout << "sending registrationRequestGUTI" << std::endl;
            if (mm->m_storage->storedGuti->get().type == nas::EIdentityType::NO_IDENTITY)
            {
                std::cout << "No GUTI found!" << std::endl;
                nas::IE5gsMobileIdentity tempGUTI;
                tempGUTI.type = nas::EIdentityType::GUTI;
                tempGUTI.gutiOrTmsi.plmn = mm->m_base->config->hplmn;
                tempGUTI.gutiOrTmsi.amfRegionId = octet(0);
                tempGUTI.gutiOrTmsi.amfSetId = 0;
                tempGUTI.gutiOrTmsi.amfPointer = 0;
                tempGUTI.gutiOrTmsi.tmsi = octet4(0);

                registrationRequestGUTI.mobileIdentity = tempGUTI;
                if (mm->m_storage->lastVisitedRegisteredTai->get().hasValue())
                    registrationRequestGUTI.lastVisitedRegisteredTai = nas::IE5gsTrackingAreaIdentity{mm->m_storage->lastVisitedRegisteredTai->get()};
                // Assign ngKSI
                if (mm->m_usim->m_currentNsCtx)
                {
                    registrationRequestGUTI.nasKeySetIdentifier.tsc = mm->m_usim->m_currentNsCtx->tsc;
                    registrationRequestGUTI.nasKeySetIdentifier.ksi = mm->m_usim->m_currentNsCtx->ngKsi;
                }
                mm->sendNasMessage(registrationRequestGUTI);
            }
            else
            {
                std::cout << "GUTI found!" << std::endl;
                mm->updateProvidedGuti();
                registrationRequestGUTI.mobileIdentity = mm->getOrGeneratePreferredId();
                if (mm->m_storage->lastVisitedRegisteredTai->get().hasValue())
                    registrationRequestGUTI.lastVisitedRegisteredTai = nas::IE5gsTrackingAreaIdentity{mm->m_storage->lastVisitedRegisteredTai->get()};
                // Assign ngKSI
                if (mm->m_usim->m_currentNsCtx)
                {
                    registrationRequestGUTI.nasKeySetIdentifier.tsc = mm->m_usim->m_currentNsCtx->tsc;
                    registrationRequestGUTI.nasKeySetIdentifier.ksi = mm->m_usim->m_currentNsCtx->ngKsi;
                }
                // Add NSSAI
                registrationRequestGUTI.requestedNSSAI = nas::utils::NssaiFrom(mm->m_storage->configuredNssai->get());
                mm->sendNasMessage(registrationRequestGUTI);
            }       
            break;}
        case MsgType::registrationComplete:
            if (!has_sec_ctx())
            {
                notify_response("null_action");
            }
            else
            {
                std::cout << "sending registrationComplete" << std::endl;
                mm->sendNasMessage(nas::RegistrationComplete{});
            }
            break;
        case MsgType::deregistrationRequest:
            std::cout << "sending deregistrationRequest" << std::endl;
            if (!storedMsgCount[(int)MsgType::deregistrationRequest])
            {
                deregistrationRequest.deRegistrationType = MakeDeRegistrationType(EDeregCause::NORMAL);
                deregistrationRequest.ngKSI.ksi = nas::IENasKeySetIdentifier::NOT_AVAILABLE_OR_RESERVED;
                deregistrationRequest.ngKSI.tsc = nas::ETypeOfSecurityContext::NATIVE_SECURITY_CONTEXT;
                deregistrationRequest.mobileIdentity = m_base->nasTask->mm->getOrGeneratePreferredId();
                storedMsgCount[(int)MsgType::deregistrationRequest]++;
            }
            mm->sendNasMessage(deregistrationRequest);
            break;
        case MsgType::serviceRequest:
            std::cout << "sending serviceRequest" << std::endl;
            if (!storedMsgCount[(int)MsgType::serviceRequest])
            {
                serviceRequest.serviceType.serviceType = nas::EServiceType::DATA;
                if (!mm->m_usim->isValid())
                {
                    serviceRequest.ngKSI.tsc = mm->m_usim->m_currentNsCtx->tsc;
                    serviceRequest.ngKSI.ksi = mm->m_usim->m_currentNsCtx->ngKsi;
                }
                // Assign TMSI (TMSI is a part of GUTI)
                serviceRequest.tmsi.type = nas::EIdentityType::TMSI;
                serviceRequest.tmsi.gutiOrTmsi.plmn = {};
                serviceRequest.tmsi.gutiOrTmsi.amfRegionId = {};
                storedMsgCount[(int)MsgType::serviceRequest]++;
            }
            // trigger new serviceRequest
            mm->sendServiceRequest(EServiceReqCause::IDLE_UPLINK_SIGNAL_PENDING);
            mm->sendNasMessage(serviceRequest);
            break;
        case MsgType::securityModeReject: {
            std::cout << "sending securityModeReject" << std::endl;
            securityModeReject.mmCause.value = nas::EMmCause::SEC_MODE_REJECTED_UNSPECIFIED;
            mm->sendNasMessage(securityModeReject);
            break;}
        case MsgType::authenticationResponse:
            std::cout << "sending authenticationResponse" << std::endl;
            if (!storedMsgCount[(int)MsgType::authenticationResponse])
            {
                authenticationResponse.authenticationResponseParameter = nas::IEAuthenticationResponseParameter{};
                authenticationResponse.authenticationResponseParameter->rawData = mm->m_usim->m_resStar.copy();
                storedMsgCount[(int)MsgType::authenticationResponse]++;
            }
            mm->sendNasMessage(authenticationResponse);
            break;
        case MsgType::authenticationResponseEmpty:{
            nas::AuthenticationResponse resp;
            resp.authenticationResponseParameter = nas::IEAuthenticationResponseParameter{};
            std::cout << "sending authenticationResponseEmpty" << std::endl;
            mm->sendNasMessage(resp);
            break;}
        case MsgType::authenticationFailure:{
            nas::AuthenticationFailure resp;
            resp.mmCause.value = nas::EMmCause::UNSPECIFIED_PROTOCOL_ERROR;
            std::cout << "sending authenticationFailure" << std::endl;
            mm->sendNasMessage(resp);
            break;}
        case MsgType::deregistrationAccept:
            std::cout << "sending deregistrationAccept" << std::endl;
            mm->sendNasMessage(nas::DeRegistrationAcceptUeTerminated{});
            break;
        case MsgType::securityModeComplete:{
            if (FLAG_REPLAY == 1)
            {
                if (SMC_SENT == 1)
                {
                    std::cout << "sending securityModeComplete_replay" << std::endl;
                    mm->sendNasMessage(securityModeComplete_replay);
                }
                else 
                {
                    notify_response("null_action");
                }
                break;
            }
            if (!has_sec_ctx())
            {
                notify_response("null_action");
            }
            else
            {
                SMC_SENT = 1;
                // store msg for replay
                auto copy = nas::utils::DeepCopyMsg(securityModeComplete);
                securityModeComplete_replay = std::move((nas::SecurityModeComplete&) *copy);
                std::cout << "sending securityModeComplete" << std::endl;
                // don't need to set any fields
                mm->sendNasMessage(securityModeComplete);
            }
            break;}
        case MsgType::identityResponse:
            std::cout << "sending identityResponse" << std::endl;
            if (!storedMsgCount[(int)MsgType::identityResponse])
            {
                identityResponse.mobileIdentity = mm->getOrGenerateSuci();
                storedMsgCount[(int)MsgType::identityResponse]++;
            }
            // handle incorrect transition in open5gs
            if (m_base->nasTask->mm->m_mmSubState != EMmSubState::MM_REGISTERED_NORMAL_SERVICE)
                mm->sendNasMessage(identityResponse);
            else
                notify_response("null_action");
            break;
        case MsgType::configurationUpdateComplete:
            if (!has_sec_ctx())
            {
                notify_response("null_action");
            }
            else
            {
                std::cout << "sending configurationUpdateComplete" << std::endl;
                mm->sendNasMessage(nas::ConfigurationUpdateComplete{});
            }
            break;

        case MsgType::ulNasTransport:
            if (!has_sec_ctx())
            {
                notify_response("null_action");
            }
            else
            {
                std::cout << "sending ulNasTransport" << std::endl;
                mm->sendNasMessage(nas::UlNasTransport{});
            }
            break;
        case MsgType::gmmStatus: {
            std::cout << "sending gmmStatus" << std::endl;
            gmmStatus.mmCause.value = nas::EMmCause::UNSPECIFIED_PROTOCOL_ERROR;
            mm->sendNasMessage(gmmStatus);
            break;}

        // smMessage
        case MsgType::PDUSessionEstablishmentRequest: {
            std::cout << "sending PDUSessionEstablishmentRequest" << std::endl;
            SessionConfig config = m_base->config->defaultSessions[0];
            m_base->nasTask->sm->sendEstablishmentRequest(config);
            mm->sendNasMessage(pdusessionEstablishmentRequest);
            break;}
        // check later
        case MsgType::PDUSessionAuthenticationComplete: {
            std::cout << "sending PDUSessionAuthenticationComplete" << std::endl;
            mm->sendNasMessage(pdusessionAuthenticationComplete);
            break;}
        case MsgType::PDUSessionModificationRequest: {
            std::cout << "sending PDUSessionModificationRequest" << std::endl;
            auto basemsg = nas::PduSessionModificationRequest{};
            basemsg.pti = m_base->nasTask->sm->allocateProcedureTransactionId();
            basemsg.requestedQosRules = nas::IEQoSRules{};
            basemsg.requestedQosRules->data = OctetString::FromHex("010006300041"); 
            basemsg.requestedQosFlowDescriptions = nas::IEQoSFlowDescriptions{};
            std::vector<std::unique_ptr<nas::VQoSFlowParameter>> parameterList;
            parameterList.push_back(std::make_unique<nas::VQoSFlowParameter>(1, OctetString::FromHex("0A"))); // 5QI 10
            basemsg.requestedQosFlowDescriptions->list.push_back(nas::VQoSFlowDescription{1, nas::EQoSOperationCode::CREATE_NEW, 1, true, std::move(parameterList)});
            basemsg.pduSessionId = 1;
            m_base->nasTask->sm->sendSmMessage(1, (nas::SmMessage &) basemsg);
            mm->sendNasMessage(pdusessionModificationRequest);
            break;}
        case MsgType::PDUSessionModificationComplete: {
            std::cout << "sending PDUSessionModificationComplete" << std::endl;
            auto basemsg = nas::PduSessionModificationComplete{};
            m_base->nasTask->sm->sendSmMessage(1, (nas::SmMessage &) basemsg);
            mm->sendNasMessage(pdusessionModificationComplete);
            break;}
        case MsgType::PDUSessionModificationCommandReject: {
            std::cout << "sending PDUSessionModificationCommandReject" << std::endl;
            auto basemsg = nas::PduSessionModificationCommandReject{};
            basemsg.pduSessionId = 1;
            basemsg.pti = m_base->nasTask->sm->allocateProcedureTransactionId();
            basemsg.smCause = nas::IE5gSmCause{nas::ESmCause::PROTOCOL_ERROR_UNSPECIFIED};
            m_base->nasTask->sm->sendSmMessage(1, (nas::SmMessage &) basemsg);
            mm->sendNasMessage(pdusessionModificationCommandReject);
            break;}
        case MsgType::PDUSessionReleaseRequest: {
            std::cout << "sending PDUSessionReleaseRequest" << std::endl;
            m_base->nasTask->sm->sendReleaseRequest(1);
            mm->sendNasMessage(pdusessionReleaseRequest);
            break;}
        case MsgType::PDUSessionReleaseComplete: {
            std::cout << "sending PDUSessionReleaseComplete" << std::endl;
            auto basemsg = nas::PduSessionReleaseComplete{};
            m_base->nasTask->sm->sendSmMessage(1, (nas::SmMessage &) basemsg);
            mm->sendNasMessage(pdusessionReleaseComplete);
            break;}
        case MsgType::gsmStatus: {
            std::cout << "sending gsmStatus" << std::endl;
            m_base->nasTask->sm->sendSmCause(nas::ESmCause::PROTOCOL_ERROR_UNSPECIFIED, 
            m_base->nasTask->sm->allocateProcedureTransactionId(), 1);
            mm->sendNasMessage(gsmStatus);
            break;}

        // fuzzing
        case MsgType::enableFuzzing:
            std::cout << "enable fuzzing" << std::endl;
            notify_response("Start fuzzing");
            enableFuzzing = true;
            break;
        // send the a message from db to the Core
        case MsgType::testMessage:{
            testMessage = true;
            notify_response("OK");
            std::cout << "testMessage" << std::endl;
            // get size of binary message
            size_t size = 1000;
            // get string size from message
            if (index != std::string::npos)
                size = std::stoi(sub_str);
            // get message
            char* buffer = (char*)calloc(size+1, sizeof(char));
            int valread = read(connfd, buffer, size+1); // read (block)
            if (connfd < 0) 
            {
                perror("No connection to CoreFuzzer\n");
                exit(1);
            }
            std::string msgIn(buffer, valread);
            printf("Read %d bytes from CoreFuzzer\n", valread);
            printf("Message: %s\n", msgIn.c_str());
            std::cout << std::endl; // flush
            size_t c1 = msgIn.find_first_of(":");
            size_t c2 = msgIn.find_last_of(":");
            // set pdu
            OctetString pdu = OctetString::FromHex(msgIn.substr(0, c1));
            // set SECMOD
            FLAG_SECMOD = std::stoi(msgIn.substr(c1+1, c2-c1-1));
            // set sht
            control_sht = std::stoi(msgIn.substr(c2+1));
            // send the message directly to avoid decode error
            mm->sendNasMessageFromPDU(std::move(pdu));

            testMessage = false;
            break;}

        default:
            std::cout << "Unknown message name" << std::endl;
            notify_response("Unknown message name");
            break;
        }
    }
    else
    {
        // naive AFL fuzzing
        // custom mutator fuzzing
        // send message to corefuzzer
        OctetString stream;
        // prepare response message
        response_t resp;
        response = &resp;
        switch (msgType)
        {
        case MsgType::enableFuzzing:
            std::cout << "enable fuzzing" << std::endl;
            notify_response("Start fuzzing");
            enableFuzzing = true;
            break;
        case MsgType::registrationRequestIMSI:
            std::cout << "sending registrationRequestIMSI to fuzzer" << std::endl;
            // should get this message when UE starts
            // assume initial registration
            nas::EncodeNasMessage((nas::PlainMmMessage &)registrationRequestIMSI, stream);
            response->new_msg = stream.toHexString();
            send_response_message(response);
            break;
        case MsgType::registrationComplete:{
            std::cout << "sending registrationComplete to fuzzer" << std::endl;
            auto basemsg = nas::RegistrationComplete{};
            nas::EncodeNasMessage((nas::PlainMmMessage &) basemsg, stream);
            response->new_msg = stream.toHexString();
            send_response_message(response);
            break;}
        case MsgType::deregistrationRequest:
            std::cout << "sending deregistrationRequest to fuzzer" << std::endl;
            if (!storedMsgCount[(int)MsgType::deregistrationRequest])
            {
                deregistrationRequest.deRegistrationType = MakeDeRegistrationType(EDeregCause::NORMAL);
                deregistrationRequest.ngKSI.ksi = nas::IENasKeySetIdentifier::NOT_AVAILABLE_OR_RESERVED;
                deregistrationRequest.ngKSI.tsc = nas::ETypeOfSecurityContext::NATIVE_SECURITY_CONTEXT;
                deregistrationRequest.mobileIdentity = m_base->nasTask->mm->getOrGeneratePreferredId();
                storedMsgCount[(int)MsgType::deregistrationRequest]++;
            }
            nas::EncodeNasMessage((nas::PlainMmMessage &) deregistrationRequest, stream);
            response->new_msg = stream.toHexString();
            send_response_message(response);
            break;
        case MsgType::serviceRequest:
            std::cout << "sending serviceRequest to fuzzer" << std::endl;
            if (!storedMsgCount[(int)MsgType::serviceRequest])
            {
                serviceRequest.serviceType.serviceType = nas::EServiceType::DATA;
                if (!mm->m_usim->isValid())
                {
                    serviceRequest.ngKSI.tsc = mm->m_usim->m_currentNsCtx->tsc;
                    serviceRequest.ngKSI.ksi = mm->m_usim->m_currentNsCtx->ngKsi;
                }
                // Assign TMSI (TMSI is a part of GUTI)
                serviceRequest.tmsi.type = nas::EIdentityType::TMSI;
                serviceRequest.tmsi.gutiOrTmsi.plmn = {};
                serviceRequest.tmsi.gutiOrTmsi.amfRegionId = {};
                storedMsgCount[(int)MsgType::serviceRequest]++;
            }
            // trigger new serviceRequest
            mm->sendServiceRequest(EServiceReqCause::IDLE_UPLINK_SIGNAL_PENDING);
            nas::EncodeNasMessage((nas::PlainMmMessage &) serviceRequest, stream);
            response->new_msg = stream.toHexString();
            send_response_message(response);
            break;
        case MsgType::securityModeReject: {
            std::cout << "sending securityModeReject to fuzzer" << std::endl;
            securityModeReject.mmCause.value = nas::EMmCause::SEC_MODE_REJECTED_UNSPECIFIED;
            nas::EncodeNasMessage((nas::PlainMmMessage &) securityModeReject, stream);
            response->new_msg = stream.toHexString();
            send_response_message(response);
            break;}
        case MsgType::authenticationResponse:
            std::cout << "sending authenticationResponse to fuzzer" << std::endl;
            if (!storedMsgCount[(int)MsgType::authenticationResponse])
            {
                authenticationResponse.authenticationResponseParameter = nas::IEAuthenticationResponseParameter{};
                authenticationResponse.authenticationResponseParameter->rawData = mm->m_usim->m_resStar.copy();
                storedMsgCount[(int)MsgType::authenticationResponse]++;
            }
            nas::EncodeNasMessage((nas::PlainMmMessage &) authenticationResponse, stream);
            response->new_msg = stream.toHexString();
            send_response_message(response);
            break;
        case MsgType::authenticationResponseEmpty:{
            nas::AuthenticationResponse resp;
            resp.authenticationResponseParameter = nas::IEAuthenticationResponseParameter{};
            std::cout << "sending authenticationResponseEmpty to fuzzer" << std::endl;
            nas::EncodeNasMessage((nas::PlainMmMessage &) resp, stream);
            response->new_msg = stream.toHexString();
            send_response_message(response);
            break;}
        case MsgType::authenticationFailure:{
            nas::AuthenticationFailure resp;
            resp.mmCause.value = nas::EMmCause::UNSPECIFIED_PROTOCOL_ERROR;
            std::cout << "sending authenticationFailure to fuzzer" << std::endl;
            nas::EncodeNasMessage((nas::PlainMmMessage &) resp, stream);
            response->new_msg = stream.toHexString();
            send_response_message(response);
            break;}
        case MsgType::deregistrationAccept:{
            std::cout << "sending deregistrationAccept to fuzzer" << std::endl;
            auto basemsg = nas::DeRegistrationAcceptUeTerminated{};
            nas::EncodeNasMessage((nas::PlainMmMessage &) basemsg, stream);
            response->new_msg = stream.toHexString();
            send_response_message(response);
            break;}
        case MsgType::securityModeComplete:
            // store msg for replay
            std::cout << "sending securityModeComplete to fuzzer" << std::endl;
            nas::EncodeNasMessage((nas::PlainMmMessage &) securityModeComplete, stream);
            response->new_msg = stream.toHexString();
            send_response_message(response);
            break;
        case MsgType::identityResponse:
            std::cout << "sending identityResponse to fuzzer" << std::endl;
            if (!storedMsgCount[(int)MsgType::identityResponse])
            {
                identityResponse.mobileIdentity = mm->getOrGenerateSuci();
                storedMsgCount[(int)MsgType::identityResponse]++;
            }
            nas::EncodeNasMessage((nas::PlainMmMessage &) identityResponse, stream);
            response->new_msg = stream.toHexString();
            send_response_message(response);
            break;
        case MsgType::configurationUpdateComplete:{
            std::cout << "sending configurationUpdateComplete to fuzzer" << std::endl;
            auto basemsg = nas::ConfigurationUpdateComplete{};
            nas::EncodeNasMessage((nas::PlainMmMessage &) basemsg, stream);
            response->new_msg = stream.toHexString();
            send_response_message(response);
            break;}
        case MsgType::gmmStatus: {
            std::cout << "sending gmmStatus to fuzzer" << std::endl;
            auto basemsg = nas::FiveGMmStatus{};
            basemsg.mmCause.value = nas::EMmCause::UNSPECIFIED_PROTOCOL_ERROR;
            nas::EncodeNasMessage((nas::PlainMmMessage &) basemsg, stream);
            response->new_msg = stream.toHexString();
            send_response_message(response);
            break;}
        case MsgType::ulNasTransport: {
            std::cout << "sending ulNasTransport to fuzzer" << std::endl;
            auto basemsg = nas::UlNasTransport{};
            nas::EncodeNasMessage((nas::PlainMmMessage &) basemsg, stream);
            response->new_msg = stream.toHexString();
            send_response_message(response);
            break;}
        // smMessage
        case MsgType::PDUSessionEstablishmentRequest: {
            std::cout << "sending PDUSessionEstablishmentRequest to fuzzer" << std::endl;
            SessionConfig config = m_base->config->defaultSessions[0];
            m_base->nasTask->sm->sendEstablishmentRequest(config);
            nas::EncodeNasMessage((nas::PlainMmMessage &) pdusessionEstablishmentRequest, stream);
            response->new_msg = stream.toHexString();
            send_response_message(response);
            break;}
        case MsgType::PDUSessionAuthenticationComplete: {
            std::cout << "sending PDUSessionAuthenticationComplete to fuzzer" << std::endl;
            nas::EncodeNasMessage((nas::PlainMmMessage &) pdusessionAuthenticationComplete, stream);
            response->new_msg = stream.toHexString();
            send_response_message(response);
            break;}
        case MsgType::PDUSessionModificationRequest: {
            std::cout << "sending PDUSessionModificationRequest to fuzzer" << std::endl;
            auto basemsg = nas::PduSessionModificationRequest{};
            m_base->nasTask->sm->sendSmMessage(1, (nas::SmMessage &) basemsg);
            nas::EncodeNasMessage((nas::PlainMmMessage &) pdusessionModificationRequest, stream);
            response->new_msg = stream.toHexString();
            send_response_message(response);
            break;}
        case MsgType::PDUSessionModificationComplete: {
            std::cout << "sending PDUSessionModificationComplete to fuzzer" << std::endl;
            auto basemsg = nas::PduSessionModificationComplete{};
            m_base->nasTask->sm->sendSmMessage(1, (nas::SmMessage &) basemsg);
            nas::EncodeNasMessage((nas::PlainMmMessage &) pdusessionModificationComplete, stream);
            response->new_msg = stream.toHexString();
            send_response_message(response);
            break;}
        case MsgType::PDUSessionModificationCommandReject: {
            std::cout << "sending PDUSessionModificationCommandReject to fuzzer" << std::endl;
            auto basemsg = nas::PduSessionModificationCommandReject{};
            basemsg.pduSessionId = 1;
            basemsg.pti = m_base->nasTask->sm->allocateProcedureTransactionId();
            basemsg.smCause = nas::IE5gSmCause{nas::ESmCause::PROTOCOL_ERROR_UNSPECIFIED};
            m_base->nasTask->sm->sendSmMessage(1, (nas::SmMessage &) basemsg);
            nas::EncodeNasMessage((nas::PlainMmMessage &) pdusessionModificationCommandReject, stream);
            response->new_msg = stream.toHexString();
            send_response_message(response);
            break;}
        case MsgType::PDUSessionReleaseRequest: {
            std::cout << "sending PDUSessionReleaseRequest to fuzzer" << std::endl;
            m_base->nasTask->sm->sendReleaseRequest(1);
            nas::EncodeNasMessage((nas::PlainMmMessage &) pdusessionReleaseRequest, stream);
            response->new_msg = stream.toHexString();
            send_response_message(response);
            break;}
        case MsgType::PDUSessionReleaseComplete: {
            std::cout << "sending PDUSessionReleaseComplete to fuzzer" << std::endl;
            auto basemsg = nas::PduSessionReleaseComplete{};
            m_base->nasTask->sm->sendSmMessage(1, (nas::SmMessage &) basemsg);
            nas::EncodeNasMessage((nas::PlainMmMessage &) pdusessionReleaseComplete, stream);
            response->new_msg = stream.toHexString();
            send_response_message(response);
            break;}
        case MsgType::gsmStatus: {
            std::cout << "sending gsmStatus to fuzzer" << std::endl;
            m_base->nasTask->sm->sendSmCause(nas::ESmCause::PROTOCOL_ERROR_UNSPECIFIED, 
                m_base->nasTask->sm->allocateProcedureTransactionId(), 1);
            nas::EncodeNasMessage((nas::PlainMmMessage &) gsmStatus, stream);
            response->new_msg = stream.toHexString();
            send_response_message(response);
            break;}
        case MsgType::incomingMessage:{
            notify_response("OK");
            std::cout << "incomingMessage" << std::endl;
            // get size of binary message
            size_t size = 1000;
            // get string size from message
            if (index != std::string::npos)
                size = std::stoi(sub_str);
            // get binary message
            OctetString pdu = recv_incoming_message(size);

            if (nas::generate_bit(1))
            {
                // send binary message to corefuzzer
                dec_before_mut = true;
                auto msg = nas::DecodeNasMessage(OctetView{pdu});
                dec_before_mut = false;
                MutateNasMessage(*msg.get());
                std::cout << "mutate complete" << std::endl;
                // change secmod
                response->secmod = nas::mutate_secmod();
                mm->sendNasMessage((nas::PlainMmMessage &) *msg);
                FLAG_SECMOD = 1; // for encode plaintext message
                nas::EncodeNasMessage((nas::PlainMmMessage &) *msg, stream);
                response->new_msg = stream.toHexString();
                std::cout << "Structural-level mutation" << std::endl;
            }
            else
            {
                // change secmod
                nas::mutate_pdu(pdu);
                response->new_msg = pdu.toHexString();
                response->secmod = nas::mutate_secmod();
                mm->sendNasMessageFromPDU(std::move(pdu));
                response->byte_mut = true;
                std::cout << "Byte-level mutation" << std::endl;
            }

            // wait for response (max 0.5s)
            for (int i = 0; i < 10; i++)
            {
                if (response->ret_msg != "" && response->ret_type != "")
                    break;
                std::this_thread::sleep_for(std::chrono::milliseconds(50));
            }

            send_response_message(response);
            break;}
        // send the a message from db to the Core
        case MsgType::testMessage:{
            enableFuzzing = false;
            testMessage = true;
            notify_response("OK");
            std::cout << "testMessage" << std::endl;
            // get size of binary message
            size_t size = 1000;
            // get string size from message
            if (index != std::string::npos)
                size = std::stoi(sub_str);
            // get message
            char* buffer = (char*)calloc(size+1, sizeof(char));
            int valread = read(connfd, buffer, size+1); // read (block)
            if (connfd < 0) 
            {
                perror("No connection to CoreFuzzer\n");
                exit(1);
            }
            std::string msgIn(buffer, valread);
            printf("Read %d bytes from CoreFuzzer\n", valread);
            printf("Message: %s\n", msgIn.c_str());

            size_t c1 = msgIn.find_first_of(":");
            size_t c2 = msgIn.find_last_of(":");
            // set pdu
            OctetString pdu = OctetString::FromHex(msgIn.substr(0, c1));
            // set SECMOD
            FLAG_SECMOD = std::stoi(msgIn.substr(c1+1, c2-c1-1));
            // set sht
            control_sht = std::stoi(msgIn.substr(c2+1));

            mm->sendNasMessageFromPDU(std::move(pdu));

            testMessage = false;
            enableFuzzing = true;
            break;}
        // send the message as-is to the Core
        case MsgType::rawMessage:{
            enableFuzzing = false;
            notify_response("OK");
            std::cout << "rawMessage" << std::endl;
            // get size of binary message
            size_t size = 1000;
            // get string size from message
            if (index != std::string::npos)
                size = std::stoi(sub_str);
            // get binary message
            OctetString pdu = recv_incoming_message(size);

            auto m = std::make_unique<NmUeNasToRrc>(NmUeNasToRrc::UPLINK_NAS_DELIVERY);
            m->pduId = 0;
            m->nasPdu = std::move(pdu);
            m_base->rrcTask->push(std::move(m));

            enableFuzzing = true;
            break;}
            
        default:
            std::cout << "Unknown fuzzing message name" << std::endl;
            notify_response("Unknown fuzzing message name");
            break;
        }
        response = nullptr;
    }
}

void UeStateLearner::notify_response(std::string msg) 
{
    std::cout << msg << std::endl;
    msg.append("\n");
    if (connfd < 0) 
    {
        printf("No connection to statelearner\n");
        return;
    }
    
    if ((send(connfd, msg.c_str(), msg.length(), 0)) < 0) 
    {
        perror("Error in Send to Statelearner\n");
        assert(0);
        return;
    }
}

void UeStateLearner::send_response_message(response_t* response) 
{
    std::string resp_str = response->ToJson();
    std::cout << "send to fuzzer: " << response->new_msg << std::endl;
    notify_response(resp_str);
}

OctetString UeStateLearner::recv_incoming_message(size_t size) 
{
    // make sure the buffer is big enough 
    char* buffer = (char*)calloc(size+1, sizeof(char));
    int valread = read(connfd, buffer, size+1); // read (block)
    if (connfd < 0) 
    {
        perror("No connection to CoreFuzzer\n");
        exit(1);
    }
    std::string msg(buffer, valread);
    printf("Read %d bytes from CoreFuzzer\n", valread);
    printf("Message: %s\n", msg.c_str());
    OctetString base = OctetString::FromHex(msg);
    free(buffer);
    return base;
}

bool UeStateLearner::has_sec_ctx() 
{
    if (m_base->nasTask->mm->m_usim->m_currentNsCtx != nullptr)
        return true;
    else
        return false;
}

void UeStateLearner::store_message(nas::PlainMmMessage &msg) 
{
    auto copy = nas::utils::DeepCopyMsg(msg);
    switch (msg.messageType)
    {
    case nas::EMessageType::REGISTRATION_REQUEST:
        if (init_reg)
        {
            registrationRequestIMSI = std::move((nas::RegistrationRequest&) *copy);
            registrationRequestGUTI = std::move((nas::RegistrationRequest&) *copy);
        }   
        break;
    case nas::EMessageType::REGISTRATION_COMPLETE:
        // registrationComplete = std::move((nas::RegistrationComplete&) *copy);
        break;
    case nas::EMessageType::DEREGISTRATION_REQUEST_UE_ORIGINATING:
        deregistrationRequest = std::move((nas::DeRegistrationRequestUeOriginating&) *copy);
        break;
    case nas::EMessageType::SERVICE_REQUEST:
        serviceRequest = std::move((nas::ServiceRequest&) *copy);
        break;
    case nas::EMessageType::SECURITY_MODE_REJECT:
        // securityModeReject = std::move((nas::SecurityModeReject&) *copy);
        break;
    case nas::EMessageType::AUTHENTICATION_RESPONSE:  
        authenticationResponse = std::move((nas::AuthenticationResponse&) *copy);
        break;
    case nas::EMessageType::AUTHENTICATION_FAILURE:
        authenticationFailure = std::move((nas::AuthenticationFailure&) *copy);
        break;
    case nas::EMessageType::DEREGISTRATION_ACCEPT_UE_TERMINATED:
        // deregistrationAccept = std::move((nas::DeRegistrationAcceptUeTerminated&) *copy);
        break;
    case nas::EMessageType::SECURITY_MODE_COMPLETE:
        securityModeComplete = std::move((nas::SecurityModeComplete&) msg);
        break;
    case nas::EMessageType::IDENTITY_RESPONSE:
        identityResponse = std::move((nas::IdentityResponse&) *copy);
        break;
    case nas::EMessageType::FIVEG_MM_STATUS:
        // gmmStatus = std::move((nas::FiveGMmStatus&) *copy);
        break;
    case nas::EMessageType::CONFIGURATION_UPDATE_COMPLETE:
        // configurationUpdateComplete = std::move((nas::ConfigurationUpdateComplete&) *copy);
        break;
    case nas::EMessageType::UL_NAS_TRANSPORT:{
        nas::UlNasTransport ulNasTrans = nas::UlNasTransport{};
        ulNasTrans = std::move((nas::UlNasTransport&) *copy);
        if (ulNasTrans.payloadContainerType.payloadContainerType == nas::EPayloadContainerType::N1_SM_INFORMATION) 
        {
            switch (smMsgType)
            {
            case nas::EMessageType::PDU_SESSION_ESTABLISHMENT_REQUEST:
                pdusessionEstablishmentRequest = std::move(ulNasTrans);
                break;
            case nas::EMessageType::PDU_SESSION_AUTHENTICATION_COMPLETE:
                pdusessionAuthenticationComplete = std::move(ulNasTrans);
                break;
            case nas::EMessageType::PDU_SESSION_MODIFICATION_REQUEST:
                pdusessionModificationRequest = std::move(ulNasTrans);
                break;
            case nas::EMessageType::PDU_SESSION_MODIFICATION_COMPLETE:
                pdusessionModificationComplete = std::move(ulNasTrans);
                break;
            case nas::EMessageType::PDU_SESSION_MODIFICATION_COMMAND_REJECT:
                pdusessionModificationCommandReject = std::move(ulNasTrans);
                break;
            case nas::EMessageType::PDU_SESSION_RELEASE_REQUEST:
                pdusessionReleaseRequest = std::move(ulNasTrans);
                break;
            case nas::EMessageType::PDU_SESSION_RELEASE_COMPLETE:
                pdusessionReleaseComplete = std::move(ulNasTrans);
                break;
            case nas::EMessageType::FIVEG_SM_STATUS:
                gsmStatus = std::move(ulNasTrans);
                break;
            default:
                break;
            }
        }
        else 
        {
            ulNasTransport = std::move(ulNasTrans);
        }
        break;}
    
    default:
        break;
    }
}

nas::IE5gsMobileIdentity UeStateLearner::getOrGenerateId(nas::EIdentityType idType)
{
    switch (idType)
    {
    case nas::EIdentityType::GUTI:
        return m_base->nasTask->mm->m_storage->storedGuti->get();
    case nas::EIdentityType::SUCI:
        return m_base->nasTask->mm->getOrGenerateSuci();
    case nas::EIdentityType::IMEI:{
        nas::IE5gsMobileIdentity res{};
        res.type = nas::EIdentityType::IMEI;
        res.value = *m_base->config->imei;
        return res;}
    case nas::EIdentityType::TMSI:
        // TMSI is already a part of GUTI
        return  m_base->nasTask->mm->m_storage->storedGuti->get();
    case nas::EIdentityType::IMEISV:{
        nas::IE5gsMobileIdentity res{};
        res.type = nas::EIdentityType::IMEISV;
        res.value = *m_base->config->imeiSv;
        return res;}
    case nas::EIdentityType::NO_IDENTITY:{
        nas::IE5gsMobileIdentity res{};
        res.type = nas::EIdentityType::NO_IDENTITY;
        return res;}
    default:
        return nas::IE5gsMobileIdentity{};
    }
}

void* start_socket(void* arg)
{
    // // unix socket
    // struct sockaddr_un addr;
    // int addrlen = sizeof(addr);
    // int fd = socket(AF_UNIX, SOCK_STREAM, 0);
    // if (fd < 0) 
    // {
    //     printf("Could not create socket: %s\n", strerror(errno));
    //     exit(1);
    // }
    // addr.sun_family = AF_UNIX;
    // strcpy(addr.sun_path, "./UE.sock");
    // unlink(addr.sun_path);
    // printf("AF_UNIX\n");
    // internet socket
    struct sockaddr_in addr;
    int addrlen = sizeof(addr);
    int opt = 1;
    int fd = socket(AF_INET, SOCK_STREAM, 0);
    if (fd < 0) 
    {
        printf("Could not create socket: %s\n", strerror(errno));
        exit(1);
    }
    // internet socket
    if (setsockopt(fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &opt, sizeof(opt))) 
    {
        printf("setsockopt failed: %s\n", strerror(errno));
        exit(1);
    }
    addr.sin_family = AF_INET;
    addr.sin_addr.s_addr = INADDR_ANY;
    addr.sin_port = htons(port);
    printf("AF_INET\n");
    if (bind(fd, (struct sockaddr *)&addr, sizeof(addr)) < 0) 
    {
        printf("Could not bind socket: %s\n", strerror(errno));
        exit(1);
    }
    if (listen(fd, 1) < 0) 
    {
        printf("Could not listen on socket: %s\n", strerror(errno));
        exit(1);
    }
    printf("Waiting for connection on port %d\n", port);
    
    state_learner->connfd = accept(fd, (struct sockaddr*)&addr, (socklen_t*)&addrlen);
    if (state_learner->connfd < 0) 
    {
        printf("Could not accept connection: %s\n", strerror(errno));
        exit(1);
    }
    else if (!state_learner->init_reg)
    {
        state_learner->notify_response("DONE");
    }
    
    FILE *fp = fopen("./statelearner.log", "a");
    fprintf(fp, "%s\n", "Connection Accepted");
    fflush(fp);
    fclose(fp);

    for(;;) 
    {
        char buffer[1024] = {0};
        int valread = read(state_learner->connfd, buffer, 1024); // read (block)
        if (valread < 0) 
        {
            printf("Could not read from socket: %s\n", strerror(errno));
            exit(1);
        }
        else if (valread == 0) 
        {
            printf("Connection closed\n");
            close(state_learner->connfd);
            state_learner->connfd = accept(fd, (struct sockaddr*)&addr, (socklen_t*)&addrlen);
            continue;
        }
        else 
        {
            printf("Read %d bytes from socket\n", valread);
            std::string msg(buffer, valread);
            FILE *fp = fopen("./statelearner.log", "a");
            fprintf(fp, "%s\n", msg.c_str());
            fflush(fp);
            fclose(fp);
            if (msg.compare("Hello\n") == 0) 
            {
                state_learner->notify_response("Hi");
                continue;
            }
            state_learner->execute_command(msg);
        }
    }
    close(state_learner->connfd);
    close(fd);
    return NULL;
}

} // namespace nr::ue
