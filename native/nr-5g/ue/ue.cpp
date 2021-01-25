//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "ue.hpp"

#include "ue_nas_task.hpp"
#include "ue_rrc_task.hpp"

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

    taskBase = base;
}

UserEquipment::~UserEquipment()
{
    taskBase->nasTask->quit();
    taskBase->rrcTask->quit();

    delete taskBase->nasTask;
    delete taskBase->rrcTask;

    delete taskBase->logBase;

    delete taskBase;
}

void UserEquipment::start()
{
    taskBase->nasTask->start();
    taskBase->rrcTask->start();
}

} // namespace nr::ue
