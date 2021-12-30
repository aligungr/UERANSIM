//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <ue/nas/layer.hpp>
#include <ue/nts.hpp>
#include <ue/rrc/layer.hpp>

namespace nr::ue
{

class UeL3Task : public NtsTask
{
  private:
    std::unique_ptr<UeRrcLayer> m_rrc;
    std::unique_ptr<NasLayer> m_nas;

    friend class UeCmdHandler;

  public:
    explicit UeL3Task(TaskBase *base);
    ~UeL3Task() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;

  public:
    UeRrcLayer& rrc();
    NasLayer& nas();
};

} // namespace nr::ue
