//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <string>

namespace nr::gnb
{

enum class EAmfState
{
    NOT_CONNECTED,
    WAITING_NG_SETUP,
    CONNECTED
};

struct SctpAssociation
{
    int associationId;
};

struct Guami
{
    Plmn plmn;
    int amfRegionId; // 8-bit
    int amfSetId;    // 10-bit
    int amfPointer;  // 6-bit
};

struct ServedGuami
{
    Guami guami;
    std::string backupAmfName;
};

struct NgapAmfContext
{
    int ctxId;
    int sctpClientId;
    SctpAssociation association;
    int nextStream; // next available SCTP stream for uplink
    std::string address;
    uint16_t port;
    std::string amfName;
    long relativeCapacity;
    EAmfState state;
    std::vector<ServedGuami *> servedGuamiList;
};

} // namespace nr::gnb