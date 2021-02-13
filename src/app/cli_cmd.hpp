//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <memory>
#include <string>
#include <vector>

namespace app
{

struct GnbCliCommand
{
    enum PR
    {
        STATUS,
        INFO,
        AMF_LIST,
        AMF_STATUS
    } present;

    // AMF_STATUS
    int amfId{};

    explicit GnbCliCommand(PR present) : present(present)
    {
    }
};

std::unique_ptr<GnbCliCommand> ParseGnbCliCommand(std::vector<std::string> &&tokens, std::string &error,
                                                  std::string &output);

} // namespace app