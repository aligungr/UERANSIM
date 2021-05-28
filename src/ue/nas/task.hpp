//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <ue/nas/usim/usim.hpp>
#include <lib/crypt/milenage.hpp>
#include <lib/nas/nas.hpp>
#include <ue/nas/mm/mm.hpp>
#include <ue/nas/sm/sm.hpp>
#include <ue/nts.hpp>
#include <ue/types.hpp>
#include <utils/nts.hpp>

namespace nr::ue
{

class NasTask : public NtsTask
{
  private:
    TaskBase *base;
    std::unique_ptr<Logger> logger;

    NasTimers timers;
    NasMm *mm;
    NasSm *sm;
    Usim *usim;

    friend class UeCmdHandler;

  public:
    explicit NasTask(TaskBase *base);
    ~NasTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;

  private:
    void performTick();
};

} // namespace nr::ue