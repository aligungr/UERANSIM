//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "test.hpp"
#include "rlc.hpp"

#include <string>
#include <cstdio>

#include <utils/nts.hpp>

enum class EntityType
{
    TM,
    UM,
    AM
};

static constexpr int SN_LENGTH = 12;
static constexpr int TX_MAX_SIZE = 1024 * 32;
static constexpr int RX_MAX_SIZE = 1024 * 32;
static constexpr int MAX_RET = 8;
static constexpr int POLL_RETRANSMIT_PERIOD = 400;
static constexpr int REASSEMBLY_PERIOD = 400;
static constexpr int STATUS_PROHIBIT_PERIOD = 400;

// UPPER LAYER
static constexpr int TRANSMISSION_SIZE = 2000;

// LOWER LAYER
static constexpr int OPPORTUNITY_SIZE_MIN = 10000;
static constexpr int OPPORTUNITY_SIZE_MAX = 30000;

// GENERAL
static constexpr int MAX_PACKET_SEND_COUNT = 60000;
static constexpr EntityType ENTITY = EntityType::AM;

struct NwRadioUplink : NtsMessage
{
    size_t size{};
    uint8_t *data{};

    NwRadioUplink() : NtsMessage(NtsMessageType::RESERVED_END)
    {
    }

    ~NwRadioUplink() override = default;
};

static uint64_t currentTimeMs()
{
    auto now = std::chrono::high_resolution_clock::now().time_since_epoch();
    return std::chrono::duration_cast<std::chrono::milliseconds>(now).count();
}

namespace rlc
{

struct RlcTask : NtsTask, IRlcConsumer
{
    IRlcEntity *m_pEntity;
    std::string tag;
    RlcTask *pair;

    int packetId;
    int receivedCounter;

    bool continueTesting = true;

    RlcTask()
    {
        switch (ENTITY)
        {
        case EntityType::TM:
            m_pEntity = NewTmEntity(this, TX_MAX_SIZE);
            break;
        case EntityType::UM:
            m_pEntity = NewUmEntity(this, SN_LENGTH, REASSEMBLY_PERIOD, TX_MAX_SIZE, RX_MAX_SIZE);
            break;
        case EntityType::AM:
            m_pEntity = NewAmEntity(this, SN_LENGTH, TX_MAX_SIZE, RX_MAX_SIZE, -1, -1, MAX_RET, POLL_RETRANSMIT_PERIOD,
                                    REASSEMBLY_PERIOD, STATUS_PROHIBIT_PERIOD);
            break;
        }
    }

    void onStart() override
    {
    }

    void onLoop() override
    {
        auto msg = poll();
        if (msg)
        {
            if (msg->msgType == NtsMessageType::RESERVED_END)
            {
                auto w = ((NwRadioUplink &)*msg);
                m_pEntity->receivePdu(w.data, w.size);
            }
        }

        m_pEntity->timerCycle(currentTimeMs());

        auto opSize = (OPPORTUNITY_SIZE_MAX + OPPORTUNITY_SIZE_MIN) / 2;
        static uint8_t buffer[OPPORTUNITY_SIZE_MAX];

        auto written = m_pEntity->createPdu(buffer, opSize);
        if (written)
        {
            auto uplink = std::make_unique<NwRadioUplink>();
            uplink->size = written;
            uplink->data = buffer;
            pair->push(std::move(uplink));
        }

        if (continueTesting)
            upperTransmission();
    }

    void onQuit() override
    {
    }

    void upperTransmission()
    {
        packetId++;

        if (packetId >= MAX_PACKET_SEND_COUNT)
        {
            puts("ENOUGH TESTING TODAY");
            tag = tag + "XX"; // it means transmission is done
            continueTesting = false;
            return;
        }

        static uint8_t packet[TRANSMISSION_SIZE];

        m_pEntity->receiveSdu(packet, TRANSMISSION_SIZE, packetId);
    }

    void deliverSdu(IRlcEntity *e, uint8_t *data, int size) override
    {
        receivedCounter++;
        printf("[%s] PDU RECEIVED, TOTAL COUNT %d B\n", tag.c_str(), receivedCounter * TRANSMISSION_SIZE);
        if (receivedCounter * TRANSMISSION_SIZE == 119998000)
        {
            printf("%ld\n", currentTimeMs());
            exit(1);
        }

        fflush(stdout);
    }

    void maxRetransmissionReached(IRlcEntity *e) override
    {
        printf("[%s] RADIO LINK FAILURE\n", tag.c_str());
        fflush(stdout);
    }

    void sduSuccessfulDelivery(IRlcEntity *e, int sduId) override
    {
        printf("[%s] SDU %d DELIVERED\n", tag.c_str(), sduId);
        fflush(stdout);
    }
};

void rlcTestMain()
{
    auto ueRlc = new RlcTask();
    auto gnbRlc = new RlcTask();

    ueRlc->pair = gnbRlc;
    gnbRlc->pair = ueRlc;

    ueRlc->tag = "UE";
    gnbRlc->tag = "GNB";

    printf("%ld\n", currentTimeMs());
    ueRlc->start();
    gnbRlc->start();
}
} // namespace rlc
