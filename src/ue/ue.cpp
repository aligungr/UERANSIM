//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "ue.hpp"

#include "app/task.hpp"
#include "l3/task.hpp"
#include "rls/task.hpp"

namespace nr::ue
{

UserEquipment::UserEquipment(UeConfig *config, app::IUeController *ueController, app::INodeListener *nodeListener,
                             NtsTask *cliCallbackTask)
{
    auto *base = new TaskBase();
    base->ue = this;
    base->config = config;
    base->logBase = new LogBase("logs/ue-" + config->getNodeName() + ".log");
    base->ueController = ueController;
    base->nodeListener = nodeListener;
    base->cliCallbackTask = cliCallbackTask;

    base->l3Task = new UeL3Task(base);
    base->appTask = new UeAppTask(base);
    base->rlsTask = new UeRlsTask(base);
    base->tunLayer = new TunLayer(base);

    taskBase = base;
}

UserEquipment::~UserEquipment()
{
    taskBase->l3Task->quit();
    taskBase->rlsTask->quit();
    taskBase->appTask->quit();

    delete taskBase->l3Task;
    delete taskBase->rlsTask;
    delete taskBase->appTask;

    delete taskBase->logBase;

    delete taskBase;
}

void UserEquipment::start()
{
    taskBase->l3Task->start();
    taskBase->rlsTask->start();
    taskBase->appTask->start();
}

void UserEquipment::pushCommand(std::unique_ptr<app::UeCliCommand> cmd, const InetAddress &address)
{
    taskBase->appTask->push(std::make_unique<NmUeCliCommand>(std::move(cmd), address));
}

} // namespace nr::ue
