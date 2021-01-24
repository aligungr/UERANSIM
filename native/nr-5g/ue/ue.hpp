//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "ue_types.hpp"

namespace nr::ue
{

class UserEquipment
{
  private:
    TaskBase *taskBase;

  public:
    UserEquipment(UeConfig *config, app::INodeListener *nodeListener);
    virtual ~UserEquipment();

  public:
    void start();
};

} // namespace nr::ue