//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "ue.hpp"

#include "app/task.hpp"
#include "nas/task.hpp"
#include "rls/task.hpp"
#include "rrc/task.hpp"

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

    base->nasTask = new NasTask(base);
    base->rrcTask = new UeRrcTask(base);
    base->appTask = new UeAppTask(base);
    base->rlsTask = new UeRlsTask(base);

    taskBase = base;
}

UserEquipment::~UserEquipment()
{
    taskBase->nasTask->quit();
    taskBase->rrcTask->quit();
    taskBase->rlsTask->quit();
    taskBase->appTask->quit();

    delete taskBase->nasTask;
    delete taskBase->rrcTask;
    delete taskBase->rlsTask;
    delete taskBase->appTask;

    delete taskBase->logBase;

    delete taskBase;
}

void UserEquipment::start()
{
    taskBase->nasTask->start();
    taskBase->rrcTask->start();
    taskBase->rlsTask->start();
    taskBase->appTask->start();
}

void UserEquipment::pushCommand(std::unique_ptr<app::UeCliCommand> cmd, const InetAddress &address)
{
    taskBase->appTask->push(std::make_unique<NmUeCliCommand>(std::move(cmd), address));
}

} // namespace nr::ue
