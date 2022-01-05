//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <memory>
#include <optional>
#include <thread>
#include <unordered_map>
#include <vector>

#include <lib/rls/rls_pdu.hpp>
#include <lib/rrc/rrc.hpp>
#include <lib/udp/server_task.hpp>
#include <ue/nas/layer.hpp>
#include <ue/rls/ctl_layer.hpp>
#include <ue/rls/udp_layer.hpp>
#include <ue/rrc/layer.hpp>
#include <ue/types.hpp>
#include <utils/common_types.hpp>
#include <utils/logger.hpp>
#include <utils/nts.hpp>

namespace nr::ue
{

class UeL23Task : public NtsTask
{
  private:
    TaskBase *m_base;
    std::unique_ptr<Logger> m_logger;
    std::unique_ptr<RlsUdpLayer> m_rlsUdp;
    std::unique_ptr<RlsCtlLayer> m_rlsCtl;
    std::unique_ptr<UeRrcLayer> m_rrc;
    std::unique_ptr<NasLayer> m_nas;

    friend class UeCmdHandler;

  public:
    explicit UeL23Task(TaskBase *base);
    ~UeL23Task() override;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;

  public:
    inline RlsUdpLayer &rlsUdp()
    {
        return *m_rlsUdp;
    }

    inline RlsCtlLayer &rlsCtl()
    {
        return *m_rlsCtl;
    }

    inline UeRrcLayer &rrc()
    {
        return *m_rrc;
    }

    inline NasLayer &nas()
    {
        return *m_nas;
    }
};

} // namespace nr::ue