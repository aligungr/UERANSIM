//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "ue.hpp"
#include "task.hpp"

namespace nr::ue
{

UserEquipment::UserEquipment(std::unique_ptr<UeConfig> &&config, app::IUeController *ueController,
                             app::INodeListener *nodeListener, NtsTask *cliCallbackTask)
{
    ue = new UeTask(std::move(config), ueController, nodeListener, cliCallbackTask);
}

UserEquipment::~UserEquipment()
{
    ue->quit();
    delete ue;
}

void UserEquipment::start()
{
    ue->start();
}

void UserEquipment::pushCommand(std::unique_ptr<app::UeCliCommand> cmd, const InetAddress &address)
{
    ue->push(std::make_unique<NmUeCliCommand>(std::move(cmd), address));
}

} // namespace nr::ue
