//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
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