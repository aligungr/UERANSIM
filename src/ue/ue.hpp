//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "types.hpp"
#include <lib/app/cli_cmd.hpp>
#include <memory>
#include <utils/network.hpp>
#include <utils/nts.hpp>

namespace nr::ue
{

class UserEquipment
{
  private:
    TaskBase *taskBase;

  public:
    UserEquipment(UeConfig *config, app::IUeController *ueController, app::INodeListener *nodeListener,
                  NtsTask *cliCallbackTask);
    virtual ~UserEquipment();

  public:
    void start();
    void pushCommand(std::unique_ptr<app::UeCliCommand> cmd, const InetAddress &address);
};

} // namespace nr::ue