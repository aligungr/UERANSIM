//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "gnb.hpp"

#include <utility>

nr::gnb::GNodeB::GNodeB(GnbConfig *config) : config(config)
{
    logBase = new logger::LogBase("logs/" + config->name + ".log");

    sctpTask = new SctpTask(*logBase);
    ngapTask = new NgapTask(config, *logBase);
    mrTask = new GnbMrTask(*logBase);

    ngapTask->setExternalTasks(sctpTask);
}

nr::gnb::GNodeB::~GNodeB()
{
    sctpTask->quit();
    ngapTask->quit();
    mrTask->quit();

    delete sctpTask;
    delete ngapTask;
    delete mrTask;

    delete logBase;
}

void nr::gnb::GNodeB::start()
{
    sctpTask->start();
    ngapTask->start();
    mrTask->start();
}
