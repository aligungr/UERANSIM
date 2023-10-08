//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "gnb.hpp"
#include "app/task.hpp"
#include "gtp/task.hpp"
#include "ngap/task.hpp"
#include "rls/task.hpp"
#include "rrc/task.hpp"
#include "sctp/task.hpp"

#include <lib/app/cli_base.hpp>

namespace nr::gnb
{

GNodeB::GNodeB(GnbConfig *config, app::INodeListener *nodeListener, NtsTask *cliCallbackTask)
{
    auto *base = new TaskBase();
    base->config = config;
    base->logBase = new LogBase("logs/" + config->name + ".log");
    base->nodeListener = nodeListener;
    base->cliCallbackTask = cliCallbackTask;

    base->appTask = new GnbAppTask(base);
    base->sctpTask = new SctpTask(base);
    base->ngapTask = new NgapTask(base);
    base->rrcTask = new GnbRrcTask(base);
    base->gtpTask = new GtpTask(base);
    base->rlsTask = new GnbRlsTask(base);

    taskBase = base;
}

GNodeB::~GNodeB()
{
    taskBase->appTask->quit();
    taskBase->sctpTask->quit();
    taskBase->ngapTask->quit();
    taskBase->rrcTask->quit();
    taskBase->gtpTask->quit();
    taskBase->rlsTask->quit();

    delete taskBase->appTask;
    delete taskBase->sctpTask;
    delete taskBase->ngapTask;
    delete taskBase->rrcTask;
    delete taskBase->gtpTask;
    delete taskBase->rlsTask;

    delete taskBase->logBase;

    delete taskBase;
}

void GNodeB::start()
{
    taskBase->appTask->start();
    taskBase->sctpTask->start();
    taskBase->ngapTask->start();
    taskBase->rrcTask->start();
    taskBase->rlsTask->start();
    taskBase->gtpTask->start();
}

void GNodeB::pushCommand(std::unique_ptr<app::GnbCliCommand> cmd, const InetAddress &address)
{
    taskBase->appTask->push(std::make_unique<NmGnbCliCommand>(std::move(cmd), address));
}

} // namespace nr::gnb
