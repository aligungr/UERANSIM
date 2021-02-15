//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "ue.hpp"

#include "app/task.hpp"
#include "mr/task.hpp"
#include "nas/task.hpp"
#include "rrc/task.hpp"

namespace nr::ue
{

UserEquipment::UserEquipment(UeConfig *config, app::INodeListener *nodeListener)
{
    auto *base = new TaskBase();
    base->config = config;
    base->logBase = new LogBase("logs/ue-" + config->getNodeName() + ".log");
    base->nodeListener = nodeListener;

    base->nasTask = new NasTask(base);
    base->rrcTask = new UeRrcTask(base);
    base->mrTask = new UeMrTask(base);
    base->appTask = new UeAppTask(base);

    taskBase = base;
}

UserEquipment::~UserEquipment()
{
    taskBase->nasTask->quit();
    taskBase->rrcTask->quit();
    taskBase->mrTask->quit();
    taskBase->appTask->quit();

    delete taskBase->nasTask;
    delete taskBase->rrcTask;
    delete taskBase->mrTask;
    delete taskBase->appTask;

    delete taskBase->logBase;

    delete taskBase;
}

void UserEquipment::start()
{
    taskBase->nasTask->start();
    taskBase->rrcTask->start();
    taskBase->mrTask->start();
    taskBase->appTask->start();
}

void UserEquipment::pushCommand(std::unique_ptr<app::UeCliCommand> cmd, const InetAddress &address,
                                NtsTask *callbackTask)
{
    taskBase->appTask->push(new NwUeCliCommand(std::move(cmd), address, callbackTask));
}

} // namespace nr::ue
