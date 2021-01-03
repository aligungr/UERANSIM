#pragma once

#include "gnb_sctp_task.hpp"

#include <logger.hpp>
#include <string>

namespace nr::gnb
{

class GNodeB
{
  private:
    std::string nodeName;
    logger::LogBase *logBase;

    SctpTask *sctpTask;

  public:
    explicit GNodeB(std::string nodeName);
    virtual ~GNodeB();

  public:
    void start();
};

} // namespace nr::gnb