//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "gnb.hpp"

#include <utility>

nr::gnb::GNodeB::GNodeB(GnbConfig *config, app::INodeListener *nodeListener)
{
    auto *base = new TaskBase();
    base->config = config;
    base->logBase = new LogBase("logs/" + config->name + ".log");
    base->nodeListener = nodeListener;

    base->appTask = new GnbAppTask(base);
    base->sctpTask = new SctpTask(base);
    base->ngapTask = new NgapTask(base);
    base->rrcTask = new GnbRrcTask(base);
    base->gtpTask = new GtpTask(base);
    base->mrTask = new GnbMrTask(base);

    taskBase = base;
}

nr::gnb::GNodeB::~GNodeB()
{
    taskBase->appTask->quit();
    taskBase->sctpTask->quit();
    taskBase->ngapTask->quit();
    taskBase->rrcTask->quit();
    taskBase->gtpTask->quit();
    taskBase->mrTask->quit();

    delete taskBase->appTask;
    delete taskBase->sctpTask;
    delete taskBase->ngapTask;
    delete taskBase->rrcTask;
    delete taskBase->gtpTask;
    delete taskBase->mrTask;

    delete taskBase->logBase;

    delete taskBase;
}

void nr::gnb::GNodeB::start()
{
    taskBase->appTask->start();
    taskBase->sctpTask->start();
    taskBase->ngapTask->start();
    taskBase->rrcTask->start();
    taskBase->mrTask->start();
    taskBase->gtpTask->start();
}
