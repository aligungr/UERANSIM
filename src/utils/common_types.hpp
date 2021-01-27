//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <memory>
#include <octet.hpp>
#include <optional>
#include <utility>
#include <vector>

enum class EPagingDrx
{
    V32 = 32,
    V64 = 64,
    V128 = 128,
    V256 = 256
};

struct Plmn
{
    int mcc;
    int mnc;
    bool isLongMnc;
};

struct SliceSupport
{
    octet sst{};
    std::optional<octet3> sd{};
};

enum class PduSessionType
{
    IPv4,
    IPv6,
    IPv4v6,
    ETHERNET,
    UNSTRUCTURED
};

struct PlmnSupport
{
    Plmn plmn;
    std::vector<std::unique_ptr<SliceSupport>> sliceSupportList;
};

struct TmsiMobileIdentity
{
    int amfSetId : 10;
    int amfPointer : 6;
    octet4 tmsi;

    TmsiMobileIdentity() : amfSetId{}, amfPointer{}, tmsi{}
    {
    }

    TmsiMobileIdentity(int amfSetId, int amfPointer, const octet4 &tmsi)
        : amfSetId(amfSetId), amfPointer(amfPointer), tmsi(tmsi)
    {
    }
};

struct GutiMobileIdentity
{
    Plmn plmn;
    octet amfRegionId;
    int amfSetId : 10;
    int amfPointer : 6;
    octet4 tmsi;

    GutiMobileIdentity() : plmn{}, amfRegionId{}, amfSetId{}, amfPointer{}, tmsi{}
    {
    }

    GutiMobileIdentity(const Plmn &plmn, const octet &amfRegionId, int amfSetId, int amfPointer, const octet4 &tmsi)
        : plmn(plmn), amfRegionId(amfRegionId), amfSetId(amfSetId), amfPointer(amfPointer), tmsi(tmsi)
    {
    }
};

struct ImsiMobileIdentity
{
    Plmn plmn;
    std::string routingIndicator;
    int protectionSchemaId : 4;
    octet homeNetworkPublicKeyIdentifier;
    std::string schemeOutput;

    ImsiMobileIdentity()
        : plmn{}, routingIndicator{}, protectionSchemaId{}, homeNetworkPublicKeyIdentifier{}, schemeOutput{}
    {
    }

    ImsiMobileIdentity(const Plmn &plmn, std::string routingIndicator, int protectionSchemaId,
                       const octet &homeNetworkPublicKeyIdentifier, std::string schemeOutput)
        : plmn(plmn), routingIndicator(std::move(routingIndicator)), protectionSchemaId(protectionSchemaId),
          homeNetworkPublicKeyIdentifier(homeNetworkPublicKeyIdentifier), schemeOutput(std::move(schemeOutput))
    {
    }
};

struct Supi
{
    std::string type;
    std::string value;

    Supi(std::string type, std::string value) : type(std::move(type)), value(std::move(value))
    {
    }

    static Supi Parse(const std::string& supi);
};