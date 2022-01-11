//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <lib/crypt/milenage.hpp>
#include <lib/nas/nas.hpp>
#include <ue/nas/mm/mm.hpp>
#include <ue/nas/sm/sm.hpp>
#include <ue/nas/usim/usim.hpp>
#include <ue/types.hpp>
#include <utils/nts.hpp>

namespace nr::ue
{

class NasLayer
{
    UeTask *m_ue;
    std::unique_ptr<Logger> m_logger;

    NasTimers m_timers;
    NasMm *m_mm;
    NasSm *m_sm;
    Usim *m_usim;

    friend class UeCmdHandler;

  public:
    explicit NasLayer(UeTask *ue);
    ~NasLayer() = default;

  public:
    void onStart();
    void onQuit();

  public:
    void performCycle();
    void handleRrcConnectionSetup();
    void handleRrcConnectionRelease();
    void handlePaging(const std::vector<GutiMobileIdentity> &tmsiIds);
    void handleRrcFallbackIndication();
    void handleRadioLinkFailure();
    void handleRrcEstablishmentFailure();
    void handleActiveCellChange(const Tai &prevTai);
    void handleNasDelivery(const OctetString& data);
    void handleUplinkDataRequest(int psi, uint8_t* buffer, size_t size);
    void handleDownlinkDataRequest(int psi, OctetString &&data);
};

} // namespace nr::ue