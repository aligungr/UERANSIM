#pragma once

#include <lib/crypt/milenage.hpp>
#include <lib/nas/nas.hpp>
#include <ue/nas/mm/mm.hpp>
#include <ue/nas/sm/sm.hpp>
#include <ue/nas/usim/usim.hpp>
#include <ue/nts.hpp>
#include <ue/types.hpp>
#include <utils/nts.hpp>

namespace nr::ue
{

class NasLayer
{
    TaskBase *base;
    std::unique_ptr<Logger> logger;

    NasTimers timers;
    NasMm *mm;
    NasSm *sm;
    Usim *usim;

    friend class UeCmdHandler;

  public:
    explicit NasLayer(TaskBase *base);
    ~NasLayer() = default;

  public:
    void onStart();
    void onQuit();

  public:
    void handleSapMessage(std::unique_ptr<NtsMessage> msg);

  public:
    void performCycle();
    void performTick();
};

} // namespace nr::ue