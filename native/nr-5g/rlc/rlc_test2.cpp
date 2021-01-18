//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "rlc_test2.hpp"
#include "rlc.hpp"

#include <cinttypes>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <functional>

using namespace rlc;

enum action
{
    GNB_AM,
    UE_AM,
    GNB_UM,
    UE_UM,
    GNB_TM,
    UE_TM,
    TIME,
    GNB_SDU,
    UE_SDU,
    GNB_PDU,
    UE_PDU,
    GNB_PDU_SIZE,
    UE_PDU_SIZE,
    GNB_RECV_FAILS,
    UE_RECV_FAILS,
    MUST_FAIL,
    GNB_BUFFER_STATUS,
    UE_BUFFER_STATUS,
    GNB_DISCARD_SDU,
    UE_DISCARD_SDU,
    RE_ESTABLISH
};

static int *test;

static int64_t tCurrent(IRlcEntity *entity)
{
    return entity->debug_getCurrentTime();
}

static void deliver_sdu_gnb_am(void *deliver_sdu_data, IRlcEntity *entity, char *buf, int size)
{
    printf("TEST: GNB: %" PRIu64 ": deliver SDU size %d [", tCurrent(entity), size);
    for (int i = 0; i < size; i++)
        printf(" %2.2x", (unsigned char)buf[i]);
    printf("]\n");
}

static void deliver_sdu_gnb_um(void *deliver_sdu_data, IRlcEntity *entity, char *buf, int size)
{
    printf("TEST: GNB: %" PRIu64 ": deliver SDU size %d [", tCurrent(entity), size);
    for (int i = 0; i < size; i++)
        printf(" %2.2x", (unsigned char)buf[i]);
    printf("]\n");
}

static void deliver_sdu_gnb_tm(void *deliver_sdu_data, IRlcEntity *entity, char *buf, int size)
{
    printf("TEST: GNB: %" PRIu64 ": deliver SDU size %d [", tCurrent(entity), size);
    for (int i = 0; i < size; i++)
        printf(" %2.2x", (unsigned char)buf[i]);
    printf("]\n");
}

static void successful_delivery_gnb(void *successful_delivery_data, IRlcEntity *entity, int sdu_id)
{
    printf("TEST: GNB: %" PRIu64 ": SDU %d was successfully delivered.\n", tCurrent(entity), sdu_id);
}

static void max_retx_reached_gnb(void *max_retx_reached_data, IRlcEntity *entity)
{
    printf("TEST: GNB: %" PRIu64 ": max RETX reached! radio link failure!\n", tCurrent(entity));
    exit(1);
}

static void deliver_sdu_ue_am(void *deliver_sdu_data, IRlcEntity *entity, char *buf, int size)
{
    printf("TEST: UE: %" PRIu64 ": deliver SDU size %d [", tCurrent(entity), size);
    for (int i = 0; i < size; i++)
        printf(" %2.2x", (unsigned char)buf[i]);
    printf("]\n");
}

static void deliver_sdu_ue_um(void *deliver_sdu_data, IRlcEntity *entity, char *buf, int size)
{
    printf("TEST: UE: %" PRIu64 ": deliver SDU size %d [", tCurrent(entity), size);
    for (int i = 0; i < size; i++)
        printf(" %2.2x", (unsigned char)buf[i]);
    printf("]\n");
}

static void deliver_sdu_ue_tm(void *deliver_sdu_data, IRlcEntity *entity, char *buf, int size)
{
    printf("TEST: UE: %" PRIu64 ": deliver SDU size %d [", tCurrent(entity), size);
    for (int i = 0; i < size; i++)
        printf(" %2.2x", (unsigned char)buf[i]);
    printf("]\n");
}

static void successful_delivery_ue(void *successful_delivery_data, IRlcEntity *entity, int sdu_id)
{
    printf("TEST: UE: %" PRIu64 ": SDU %d was successfully delivered.\n", tCurrent(entity), sdu_id);
}

static void max_retx_reached_ue(void *max_retx_reached_data, IRlcEntity *entity)
{
    printf("TEST: UE: %" PRIu64 ", max RETX reached! radio link failure!\n", tCurrent(entity));
    exit(1);
}

using Delegate1 = std::function<void(void *, IRlcEntity *, char *, int)>;
using Delegate2 = std::function<void(void *, IRlcEntity *, int)>;
using Delegate3 = std::function<void(void *, IRlcEntity *)>;

struct CRlcConsumer : IRlcConsumer
{
    Delegate1 x;
    Delegate2 y;
    Delegate3 z;

    CRlcConsumer(Delegate1 &x, Delegate2 &y, Delegate3 &z) : x(x), y(y), z(z)
    {
    }

    void sduSuccessfulDelivery(IRlcEntity *entity, int sduId) override
    {
        (y)(nullptr, entity, sduId);
    }

    void maxRetransmissionReached(IRlcEntity *entity) override
    {
        (z)(nullptr, entity);
    }

    void deliverSdu(IRlcEntity *entity, uint8_t *data, int size) override
    {
        (x)(nullptr, entity, reinterpret_cast<char *>(data), size);
    }
};

static IRlcEntity *new_nr_rlc_entity_tm(int txMaxSize, Delegate1 deliver_sdu_gnb_tm, void *unused)
{
    Delegate2 a{};
    Delegate3 b{};

    auto cons = new CRlcConsumer(deliver_sdu_gnb_tm, a, b);
    return NewTmEntity(cons, txMaxSize);
}

static IRlcEntity *new_nr_rlc_entity_um(int rx_maxsize, int tx_maxsize, Delegate1 deliver_sdu_gnb_um, void *unused,
                                        int t_reassembly, int sn_field_length)
{
    Delegate2 a{};
    Delegate3 b{};

    auto cons = new CRlcConsumer(deliver_sdu_gnb_um, a, b);
    return NewUmEntity(cons, sn_field_length, t_reassembly, tx_maxsize, rx_maxsize);
}

static IRlcEntity *new_nr_rlc_entity_am(int rxMaxSize, int txMaxSize, Delegate1 deliver_sdu_gnb_am, void *not_used1,
                                        Delegate2 successful_delivery_gnb, void *not_used2,
                                        Delegate3 max_retx_reached_gnb, void *not_used, int polRetransmit,
                                        int reassembly, int prohibit, int pollPdu, int pollByte, int maxRetxTresh,
                                        int snFieldLen)
{
    auto cons = new CRlcConsumer(deliver_sdu_gnb_am, successful_delivery_gnb, max_retx_reached_gnb);
    return NewAmEntity(cons, snFieldLen, txMaxSize, rxMaxSize, pollByte, pollByte, maxRetxTresh, polRetransmit,
                       reassembly, prohibit);
}

int nr::rlc::test_main(int arr[])
{
    test = arr;
    IRlcEntity *gnb = NULL;
    IRlcEntity *ue = NULL;
    int i;
    int k;
    char *sdu;
    char *pdu;
    RlcDataVolume buffer_status;
    int gnb_do_buffer_status = 0;
    int ue_do_buffer_status = 0;
    int size;
    int pos;
    int next_byte_gnb = 0;
    int next_byte_ue = 0;
    int gnb_recv_fails = 0;
    int ue_recv_fails = 0;
    int gnb_pdu_size = 1000;
    int ue_pdu_size = 1000;

    printf("TEST: start\n");

    sdu = static_cast<char *>(malloc(16001));
    pdu = static_cast<char *>(malloc(3000));
    if (sdu == nullptr || pdu == nullptr)
    {
        printf("out of memory\n");
        exit(1);
    }

    for (i = 0; i < 16001; i++)
        sdu[i] = i & 255;

    pos = 0;
    if (test[pos] != TIME)
    {
        printf("%s:%d:%s: fatal\n", __FILE__, __LINE__, __FUNCTION__);
        exit(1);
    }

    for (i = 1; i < 1000; i++)
    {
        if (i == test[pos + 1])
        {
            pos += 2;
            while (test[pos] != TIME)
                switch (test[pos])
                {
                default:
                    printf("fatal: unknown action\n");
                    exit(1);
                case GNB_AM:
                    gnb = new_nr_rlc_entity_am(test[pos + 1], test[pos + 2], deliver_sdu_gnb_am, NULL,
                                               successful_delivery_gnb, NULL, max_retx_reached_gnb, NULL, test[pos + 3],
                                               test[pos + 4], test[pos + 5], test[pos + 6], test[pos + 7],
                                               test[pos + 8], test[pos + 9]);
                    pos += 10;
                    break;
                case UE_AM:
                    ue = new_nr_rlc_entity_am(test[pos + 1], test[pos + 2], deliver_sdu_ue_am, NULL,
                                              successful_delivery_ue, NULL, max_retx_reached_ue, NULL, test[pos + 3],
                                              test[pos + 4], test[pos + 5], test[pos + 6], test[pos + 7], test[pos + 8],
                                              test[pos + 9]);
                    pos += 10;
                    break;
                case GNB_UM:
                    gnb = new_nr_rlc_entity_um(test[pos + 1], test[pos + 2], deliver_sdu_gnb_um, NULL, test[pos + 3],
                                               test[pos + 4]);
                    pos += 5;
                    break;
                case UE_UM:
                    ue = new_nr_rlc_entity_um(test[pos + 1], test[pos + 2], deliver_sdu_ue_um, NULL, test[pos + 3],
                                              test[pos + 4]);
                    pos += 5;
                    break;
                case GNB_TM:
                    gnb = new_nr_rlc_entity_tm(test[pos + 1], deliver_sdu_gnb_tm, NULL);
                    pos += 2;
                    break;
                case UE_TM:
                    ue = new_nr_rlc_entity_tm(test[pos + 1], deliver_sdu_ue_tm, NULL);
                    pos += 2;
                    break;
                case GNB_SDU:
                    for (k = 0; k < test[pos + 2]; k++, next_byte_gnb++)
                        sdu[k] = next_byte_gnb;
                    printf("TEST: GNB: %d: recv_sdu (id %d): size %d: [", i, test[pos + 1], test[pos + 2]);
                    for (k = 0; k < test[pos + 2]; k++)
                        printf(" %2.2x", (unsigned char)sdu[k]);
                    printf("]\n");
                    gnb->receiveSdu(reinterpret_cast<uint8_t *>(sdu), test[pos + 2], test[pos + 1]);
                    pos += 3;
                    break;
                case UE_SDU:
                    for (k = 0; k < test[pos + 2]; k++, next_byte_ue++)
                        sdu[k] = next_byte_ue;
                    printf("TEST: UE: %d: recv_sdu (id %d): size %d: [", i, test[pos + 1], test[pos + 2]);
                    for (k = 0; k < test[pos + 2]; k++)
                        printf(" %2.2x", (unsigned char)sdu[k]);
                    printf("]\n");
                    ue->receiveSdu(reinterpret_cast<uint8_t *>(sdu), test[pos + 2], test[pos + 1]);
                    pos += 3;
                    break;
                case GNB_PDU:
                    for (k = 0; k < test[pos + 1]; k++)
                        pdu[k] = test[pos + 2 + k];
                    printf("TEST: GNB: %d: custom PDU: size %d: [", i, test[pos + 1]);
                    for (k = 0; k < test[pos + 1]; k++)
                        printf(" %2.2x", (unsigned char)pdu[k]);
                    printf("]\n");
                    if (!ue_recv_fails)
                        ue->receivePdu(reinterpret_cast<uint8_t *>(pdu), test[pos + 1]);
                    pos += 2 + test[pos + 1];
                    break;
                case UE_PDU:
                    for (k = 0; k < test[pos + 1]; k++)
                        pdu[k] = test[pos + 2 + k];
                    printf("TEST: UE: %d: custom PDU: size %d: [", i, test[pos + 1]);
                    for (k = 0; k < test[pos + 1]; k++)
                        printf(" %2.2x", (unsigned char)pdu[k]);
                    printf("]\n");
                    if (!gnb_recv_fails)
                        gnb->receivePdu(reinterpret_cast<uint8_t *>(pdu), test[pos + 1]);
                    pos += 2 + test[pos + 1];
                    break;
                case GNB_PDU_SIZE:
                    gnb_pdu_size = test[pos + 1];
                    pos += 2;
                    break;
                case UE_PDU_SIZE:
                    ue_pdu_size = test[pos + 1];
                    pos += 2;
                    break;
                case GNB_RECV_FAILS:
                    gnb_recv_fails = test[pos + 1];
                    pos += 2;
                    break;
                case UE_RECV_FAILS:
                    ue_recv_fails = test[pos + 1];
                    pos += 2;
                    break;
                case MUST_FAIL:
                    /* do nothing, only used by caller */
                    pos++;
                    break;
                case GNB_BUFFER_STATUS:
                    gnb_do_buffer_status = 1;
                    pos++;
                    break;
                case UE_BUFFER_STATUS:
                    ue_do_buffer_status = 1;
                    pos++;
                    break;
                case GNB_DISCARD_SDU:
                    printf("TEST: GNB: %d: discard SDU %d\n", i, test[pos + 1]);
                    gnb->discardSdu(test[pos + 1]);
                    pos += 2;
                    break;
                case UE_DISCARD_SDU:
                    printf("TEST: UE: %d: discard SDU %d\n", i, test[pos + 1]);
                    ue->discardSdu(test[pos + 1]);
                    pos += 2;
                    break;
                case RE_ESTABLISH:
                    printf("TEST: %d: re-establish eNB and UE\n", i);
                    gnb->reestablishment();
                    ue->reestablishment();
                    pos++;
                    break;
                }
        }

        gnb->timerCycle(i);
        ue->timerCycle(i);

        if (gnb_do_buffer_status)
        {
            gnb_do_buffer_status = 0;
            gnb->calculateDataVolume(buffer_status);
            printf("TEST: GNB: %d: buffer_status: status_size %d tx_size %d retx_size %d\n", i,
                   buffer_status.statusSize, buffer_status.transmissionSize, buffer_status.retransmissionSize);
        }

        size = gnb->createPdu(reinterpret_cast<uint8_t *>(pdu), gnb_pdu_size);
        if (size)
        {
            printf("TEST: GNB: %d: generate_pdu: size %d: [", i, size);
            for (k = 0; k < size; k++)
                printf(" %2.2x", (unsigned char)pdu[k]);
            printf("]\n");
            if (!ue_recv_fails)
                ue->receivePdu(reinterpret_cast<uint8_t *>(pdu), size);
        }

        if (ue_do_buffer_status)
        {
            ue_do_buffer_status = 0;
            ue->calculateDataVolume(buffer_status);
            printf("TEST: UE: %d: buffer_status: status_size %d tx_size %d retx_size %d\n", i, buffer_status.statusSize,
                   buffer_status.transmissionSize, buffer_status.retransmissionSize);
        }

        size = ue->createPdu(reinterpret_cast<uint8_t *>(pdu), ue_pdu_size);
        if (size)
        {
            printf("TEST: UE: %d: generate_pdu: size %d: [", i, size);
            for (k = 0; k < size; k++)
                printf(" %2.2x", (unsigned char)pdu[k]);
            printf("]\n");
            if (!gnb_recv_fails)
                gnb->receivePdu(reinterpret_cast<uint8_t *>(pdu), size);
        }
    }

    // gnb->delete (gnb);
    // ue->delete (ue);

    free(sdu);
    free(pdu);

    return 0;
}
