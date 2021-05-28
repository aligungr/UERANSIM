//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <optional>
#include <string>

namespace app
{

enum class ConnectionType
{
    SCTP,
    NGAP
};

enum class NodeType
{
    NA,
    UE,
    GNB,
    AMF,
    UPF
};

enum class StateType
{
    MM,
    MM_SUB,
    RM,
    CM,
    U5,
    RRC
};

class INodeListener
{
  public:
    virtual void onConnected(NodeType subjectType, const std::string &subjectId, NodeType objectType,
                             const std::string &objectId) = 0;

    virtual void onReceive(NodeType subjectType, const std::string &subjectId, NodeType objectType,
                           const std::string &objectId, ConnectionType connectionType, std::string message) = 0;

    virtual void onSend(NodeType subjectType, const std::string &subjectId, NodeType objectType,
                        const std::string &objectId, ConnectionType connectionType, std::string message) = 0;

    virtual void onSwitch(NodeType subjectType, const std::string &subjectId, StateType stateType,
                          const std::string &fromState, const std::string &toState) = 0;
};

} // namespace app