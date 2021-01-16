#include "gnb_rrc_task.hpp"

namespace nr::gnb
{

GnbRrcTask::GnbRrcTask(logger::LogBase &loggerBase)
{
    logger = loggerBase.makeUniqueLogger("gnb-rrc");
}

void GnbRrcTask::onStart()
{
}

void GnbRrcTask::onLoop()
{
}

void GnbRrcTask::onQuit()
{
}

} // namespace nr::gnb
