//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

namespace nr::ue
{

class UserEquipment;

}

namespace app
{

class IUeController
{
  public:
    virtual void performSwitchOff(nr::ue::UserEquipment *ue) = 0;
};

} // namespace app
