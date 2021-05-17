//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include <lib/nas/msg.hpp>
#include <lib/nas/storage.hpp>
#include <ue/types.hpp>

namespace nr::ue
{

class MmStorage
{
  private:
    TaskBase *m_base;

  public:
    std::unique_ptr<nas::NasList<Tai>> m_forbiddenTaiListRoaming;
    std::unique_ptr<nas::NasList<Tai>> m_forbiddenTaiListRps;
    std::unique_ptr<nas::NasSlot<nas::IEServiceAreaList>> m_serviceAreaList;

  public:
    explicit MmStorage(TaskBase *base);
};

} // namespace nr::ue
