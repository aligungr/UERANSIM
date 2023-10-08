//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "proto_conf.hpp"

#include <utils/octet_view.hpp>

bool nas::ProtocolConfigurationOptions::IsProtocolId(int id)
{
    return id == 0xC021 || id == 0xC023 || id == 0xC223 || id == 0x8021;
}

bool nas::ProtocolConfigurationOptions::IsTwoOctetLengthContainerId(int id, bool isUplink)
{
    return !isUplink && (id == 0x0023 || id == 0x0024 || id == 0x0030);
}

OctetString nas::ProtocolConfigurationOptions::encode()
{
    OctetString stream;

    for (auto &item : configurationProtocols)
    {
        stream.appendOctet2((int)item->id);
        stream.appendOctet(item->content.length());
        stream.append(item->content);
    }

    for (auto &item : additionalParams)
    {
        stream.appendOctet2((int)item->id);
        if (IsTwoOctetLengthContainerId((int)item->id, item->isUplink))
            stream.appendOctet2(item->content.length());
        else
            stream.appendOctet(item->content.length());
        stream.append(item->content);
    }

    return stream;
}

std::unique_ptr<nas::ProtocolConfigurationOptions> nas::ProtocolConfigurationOptions::Decode(uint8_t *data, size_t len,
                                                                                             bool isUplink)
{
    auto res = std::make_unique<ProtocolConfigurationOptions>();

    OctetView buff{data, len};

    while (buff.hasNext())
    {
        int id = buff.read2I();
        if (IsProtocolId(id))
        {
            int length = buff.readI();
            auto contents = buff.readOctetString(length);
            res->configurationProtocols.push_back(std::make_unique<ProtocolConfigurationItem>(
                static_cast<EProtocolConfigId>(id), isUplink, std::move(contents)));
        }
        else
        {
            int length = IsTwoOctetLengthContainerId(id, isUplink) ? buff.read2I() : buff.readI();
            auto contents = buff.readOctetString(length);
            res->additionalParams.push_back(std::make_unique<ProtocolConfigurationItem>(
                static_cast<EProtocolConfigId>(id), isUplink, std::move(contents)));
        }
    }

    return res;
}
