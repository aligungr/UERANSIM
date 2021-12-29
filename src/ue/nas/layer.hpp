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
    TaskBase *m_base;
    std::unique_ptr<Logger> m_logger;

    NasTimers m_timers;
    NasMm *m_mm;
    NasSm *m_sm;
    Usim *m_usim;

    friend class UeCmdHandler;

  public:
    explicit NasLayer(TaskBase *base);
    ~NasLayer() = default;

  public:
    void onStart();
    void onQuit();

  public:
    void performCycle();
    void handleSapMessage(std::unique_ptr<NtsMessage> msg);
    void handleRrcConnectionSetup();
    void handleRrcConnectionRelease();
    void handlePaging(const std::vector<GutiMobileIdentity> &tmsiIds);
    void handleRrcFallbackIndication();
    void handleRadioLinkFailure();
};

} // namespace nr::ue