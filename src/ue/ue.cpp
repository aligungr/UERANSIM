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

UserEquipment::UserEquipment(UeConfig *config, app::IUeController *ueController, app::INodeListener *nodeListener,
                             NtsTask *cliCallbackTask)
{
    auto *base = new TaskBase();
    base->ue = this;
    base->config = config;
    base->logBase = std::make_unique<LogBase>("logs/ue-" + config->getNodeName() + ".log");
    base->ueController = ueController;
    base->nodeListener = nodeListener;
    base->cliCallbackTask = cliCallbackTask;

    base->task = new UeTask(base);

    taskBase = base;
}

UserEquipment::~UserEquipment()
{
    taskBase->task->quit();

    delete taskBase->task;

    delete taskBase;
}

void UserEquipment::start()
{
    taskBase->task->start();
}

void UserEquipment::pushCommand(std::unique_ptr<app::UeCliCommand> cmd, const InetAddress &address)
{
    taskBase->task->push(std::make_unique<NmUeCliCommand>(std::move(cmd), address));
}

} // namespace nr::ue
