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
    std::unique_ptr<nas::NasSlot<E5UState>> uState;

    std::unique_ptr<nas::NasSlot<nas::IE5gsMobileIdentity>> storedSuci;
    std::unique_ptr<nas::NasSlot<nas::IE5gsMobileIdentity>> storedGuti;

    std::unique_ptr<nas::NasList<Plmn>> equivalentPlmnList;
    std::unique_ptr<nas::NasList<Plmn>> forbiddenPlmnList;

    std::unique_ptr<nas::NasSlot<nas::IE5gsTrackingAreaIdentityList>> taiList;
    std::unique_ptr<nas::NasSlot<Tai>> lastVisitedRegisteredTai;
    std::unique_ptr<nas::NasList<Tai>> forbiddenTaiListRoaming;
    std::unique_ptr<nas::NasList<Tai>> forbiddenTaiListRps;

    std::unique_ptr<nas::NasSlot<nas::IEServiceAreaList>> serviceAreaList;

    std::unique_ptr<nas::NasSlot<NetworkSlice>> defConfiguredNssai;
    std::unique_ptr<nas::NasSlot<NetworkSlice>> configuredNssai;
    std::unique_ptr<nas::NasSlot<NetworkSlice>> allowedNssai;
    std::unique_ptr<nas::NasSlot<NetworkSlice>> rejectedNssaiInPlmn;
    std::unique_ptr<nas::NasSlot<NetworkSlice>> rejectedNssaiInTa;

    std::unique_ptr<nas::NasSlot<std::optional<nas::IENetworkName>>> networkFullName;
    std::unique_ptr<nas::NasSlot<std::optional<nas::IENetworkName>>> networkShortName;
    std::unique_ptr<nas::NasSlot<std::optional<nas::IETimeZone>>> localTimeZone;
    std::unique_ptr<nas::NasSlot<std::optional<nas::IETimeZoneAndTime>>> universalTimeAndLocalTimeZone;
    std::unique_ptr<nas::NasSlot<std::optional<nas::IEDaylightSavingTime>>> networkDaylightSavingTime;

  public:
    explicit MmStorage(TaskBase *base);
};

} // namespace nr::ue
