//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include <lib/nas/msg.hpp>
#include <lib/nas/storage.hpp>

namespace nr::ue
{

class MmStorage
{
  public:
    std::unique_ptr<nas::NasListT1<Tai>> m_forbiddenTaiListRoaming;
    std::unique_ptr<nas::NasListT1<Tai>> m_forbiddenTaiListRps;

  public:
    MmStorage();
};

} // namespace nr::ue
