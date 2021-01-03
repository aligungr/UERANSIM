//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "gnb.hpp"

#include <utility>

nr::gnb::GNodeB::GNodeB(std::string nodeName) : nodeName(std::move(nodeName))
{
    logBase = new logger::LogBase("logs/" + this->nodeName + ".log");

    sctpTask = new SctpTask(*logBase);
}

nr::gnb::GNodeB::~GNodeB()
{
    sctpTask->quit();
    delete sctpTask;

    delete logBase;
}

void nr::gnb::GNodeB::start()
{
    sctpTask->start();
}
