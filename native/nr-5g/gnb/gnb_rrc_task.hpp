#pragma once

#include <logger.hpp>
#include <memory>
#include <nts.hpp>
#include <thread>
#include <unordered_map>
#include <vector>

namespace nr::gnb
{

class GnbRrcTask : public NtsTask
{
  private:
    std::unique_ptr<logger::Logger> logger;

  public:
    explicit GnbRrcTask(logger::LogBase &loggerBase);
    ~GnbRrcTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;
};

}