#pragma once

#include <ue/nts.hpp>
#include <ue/types.hpp>

namespace nr::ue
{

enum class MsgType
{
    // mmMsg
    // registrationRequest,
    registrationRequestIMSI,
    registrationRequestGUTI,
    registrationComplete,
    deregistrationRequest,
    serviceRequest,
    securityModeReject,
    authenticationResponse,
    authenticationResponseEmpty,
    authenticationFailure,
    deregistrationAccept,
    securityModeComplete,
    identityResponse,
    configurationUpdateComplete,

    gmmStatus,
    ulNasTransport,

    // smMsg
    PDUSessionEstablishmentRequest,
    PDUSessionAuthenticationComplete,
    PDUSessionModificationRequest,
    PDUSessionModificationComplete,
    PDUSessionModificationCommandReject,
    PDUSessionReleaseRequest,
    PDUSessionReleaseComplete,
    gsmStatus,

    // fuzzing
    enableFuzzing,
    incomingMessage,
    testMessage,
    rawMessage
};

enum class ShtType
{
    nosec,
    intonly,
    _protected,
    replay
};

// construct return message to fuzzing controller
typedef struct response_t
{
    std::string ret_type = "";
    std::string ret_msg = "";
    std::string new_msg = "";
    int sht = 0;
    int secmod = 0;
    std::string mm_status = "";
    bool byte_mut = false;
    // serialize to json
    std::string ToJson()
    {
        std::string ret = "{";
        ret += "\"ret_type\": \"" + ret_type + "\",";
        ret += "\"ret_msg\": \"" + ret_msg + "\",";
        ret += "\"new_msg\": \"" + new_msg + "\",";
        ret += "\"sht\": " + std::to_string(sht) + ",";
        ret += "\"secmod\": " + std::to_string(secmod) + ",";
        ret += "\"mm_status\": \"" + mm_status + "\"" + ",";
        ret += "\"byte_mut\": " + std::to_string(byte_mut);
        ret += "}";
        return ret;
    }
} response_t;

class UeStateLearner
{
  private:
    TaskBase *m_base;

    std::unordered_map<std::string, MsgType> msgMap = 
    {
        {"registrationRequest"        , MsgType::registrationRequestIMSI    },
        {"registrationRequestIMSI"    , MsgType::registrationRequestIMSI    },
        {"registrationRequestGUTI"    , MsgType::registrationRequestGUTI    },
        {"registrationComplete"       , MsgType::registrationComplete       },
        {"deregistrationRequest"      , MsgType::deregistrationRequest      },
        {"serviceRequest"             , MsgType::serviceRequest             },
        {"securityModeReject"         , MsgType::securityModeReject         },
        {"authenticationResponse"     , MsgType::authenticationResponse     },
        {"authenticationResponseEmpty", MsgType::authenticationResponseEmpty},
        {"authenticationFailure"      , MsgType::authenticationFailure      },
        {"deregistrationAccept"       , MsgType::deregistrationAccept       },
        {"securityModeComplete"       , MsgType::securityModeComplete       },
        {"identityResponse"           , MsgType::identityResponse           },
        {"configurationUpdateComplete", MsgType::configurationUpdateComplete},

        {"gmmStatus"                  , MsgType::gmmStatus                  },
        {"ulNasTransport"             , MsgType::ulNasTransport             },

        {"PDUSessionEstablishmentRequest"     , MsgType::PDUSessionEstablishmentRequest     },
        {"PDUSessionAuthenticationComplete"   , MsgType::PDUSessionAuthenticationComplete   },
        {"PDUSessionModificationRequest"      , MsgType::PDUSessionModificationRequest      },
        {"PDUSessionModificationComplete"     , MsgType::PDUSessionModificationComplete     },
        {"PDUSessionModificationCommandReject", MsgType::PDUSessionModificationCommandReject},
        {"PDUSessionReleaseRequest"           , MsgType::PDUSessionReleaseRequest           },
        {"PDUSessionReleaseComplete"          , MsgType::PDUSessionReleaseComplete          },
        {"gsmStatus"                          , MsgType::gsmStatus                          },

        {"enableFuzzing"              , MsgType::enableFuzzing              },
        {"incomingMessage"            , MsgType::incomingMessage            },
        {"testMessage"                , MsgType::testMessage                },
        {"rawMessage"                 , MsgType::rawMessage                 }
    };

    std::unordered_map<std::string, ShtType> shtMap = 
    {
        {"nosec",     ShtType::nosec      },
        {"intonly",   ShtType::intonly    },
        {"protected", ShtType::_protected },
        {"replay",    ShtType::replay     }
    };

    // stored messages
    nas::RegistrationRequest                registrationRequestIMSI;
    nas::RegistrationRequest                registrationRequestGUTI;
    // nas::RegistrationComplete               registrationComplete;
    nas::DeRegistrationRequestUeOriginating deregistrationRequest;
    nas::ServiceRequest                     serviceRequest;
    nas::SecurityModeReject                 securityModeReject;
    nas::AuthenticationResponse             authenticationResponse;
    nas::AuthenticationFailure              authenticationFailure;
    // nas::DeRegistrationAcceptUeTerminated   deregistrationAccept;
    nas::SecurityModeComplete               securityModeComplete;
    nas::SecurityModeComplete               securityModeComplete_replay;
    nas::IdentityResponse                   identityResponse;
    nas::FiveGMmStatus                      gmmStatus;
    // nas::ConfigurationUpdateComplete        configurationUpdateComplete;
    nas::UlNasTransport                     ulNasTransport;

    // store SM messages
    nas::UlNasTransport                     pdusessionEstablishmentRequest;
    nas::UlNasTransport                     pdusessionAuthenticationComplete;
    nas::UlNasTransport                     pdusessionModificationRequest;
    nas::UlNasTransport                     pdusessionModificationComplete;
    nas::UlNasTransport                     pdusessionModificationCommandReject;
    nas::UlNasTransport                     pdusessionReleaseRequest;
    nas::UlNasTransport                     pdusessionReleaseComplete;
    nas::UlNasTransport                     gsmStatus;

 

  public:
    explicit UeStateLearner(TaskBase *base) : m_base(base)
    {
    }

    void startThread();
    void execute_command(std::string msg);
    void notify_response(std::string msg);
    void store_message(nas::PlainMmMessage &msg);

    // functions for fuzzing
    void send_response_message(response_t* response);
    OctetString recv_incoming_message(size_t size);

    bool has_sec_ctx();
    // generate id
    nas::IE5gsMobileIdentity getOrGenerateId(nas::EIdentityType idType);

    volatile int connfd;
    int storedMsgCount[13] = {0};
    bool init_reg = true;

    // flag for enable fuzzing
    bool enableFuzzing = false;
    response_t* response;
    // for reproduce fuzzing message
    int control_sht = 0;
    bool testMessage = false;
    // flag to indicate if the message decoded from fuzzer
    bool dec_before_mut = false;
    // flag to ignore some message in replay detection
    bool is_ignored = false;

    // current SM message type
    nas::EMessageType smMsgType = (nas::EMessageType)0;
};

extern UeStateLearner *state_learner;

extern int FLAG_SECMOD; // 0: default , 1: no_security, 2: integrity_only, 3: integrity_and_ciphering
extern bool FLAG_REPLAY; // 0: default , 1: replayed

// port for state learner
extern int port;

void* start_socket(void* arg);

} // namespace nr::ue

