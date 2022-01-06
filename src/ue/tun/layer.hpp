//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <array>
#include <cstddef>
#include <cstdint>
#include <memory>
#include <string>

#include <ue/types.hpp>
#include <utils/scoped_thread.hpp>

namespace nr::ue
{

class TunLayer
{
  private:
    UeTask *m_ue;

  public:
    explicit TunLayer(UeTask *ue);
    ~TunLayer();

  public:
    std::string allocate(int psi, const std::string &ipAddress, bool configureRouting, std::string &outError);
    void release(int psi);
};

} // namespace nr::ue
