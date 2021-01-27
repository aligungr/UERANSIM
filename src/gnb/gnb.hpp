//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "gnb_types.hpp"

#include <app_monitor.hpp>
#include <logger.hpp>
#include <string>

namespace nr::gnb
{

class GNodeB
{
  private:
    TaskBase *taskBase;

  public:
    GNodeB(GnbConfig *config, app::INodeListener *nodeListener);
    virtual ~GNodeB();

  public:
    void start();
};

} // namespace nr::gnb