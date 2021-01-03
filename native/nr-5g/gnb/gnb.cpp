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
