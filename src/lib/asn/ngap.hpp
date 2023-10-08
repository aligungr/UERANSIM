//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include "ngap_msg.hpp"

#include <algorithm>
#include <exception>
#include <functional>
#include <vector>

#include <asn_SEQUENCE_OF.h>
#include <asn_application.h>

namespace asn::ngap
{

ASN_NGAP_NGAP_PDU *NgapPduFromPduDescription(ASN_NGAP_InitiatingMessage *desc);
ASN_NGAP_NGAP_PDU *NgapPduFromPduDescription(ASN_NGAP_SuccessfulOutcome *desc);
ASN_NGAP_NGAP_PDU *NgapPduFromPduDescription(ASN_NGAP_UnsuccessfulOutcome *desc);

int GetPduDescription(NgapMessageType messageType);
int GetProcedureCode(NgapMessageType messageType);
int GetProcedureCriticality(NgapMessageType messageType);
int GetProcedurePresent(NgapMessageType messageType);

void *NewDescFromMessageType(NgapMessageType type, void *&pOutDescription);

template <typename TMessage>
inline void AddProtocolIe(TMessage &msg, typename NgapMessageToIeType<TMessage>::value *element)
{
    ASN_SEQUENCE_ADD(&msg.protocolIEs.list, element);

    // Protocol IE fields must be sorted according to ASN definition order.
    // Using 'present' here because it is consistent with ASN definition order;
    // This is not a constant-time operation.
    std::stable_sort(
        msg.protocolIEs.list.array, msg.protocolIEs.list.array + msg.protocolIEs.list.count,
        [](typename NgapMessageToIeType<TMessage>::value *a, typename NgapMessageToIeType<TMessage>::value *b) {
            return a->value.present < b->value.present;
        });
}

template <typename T>
inline ASN_NGAP_NGAP_PDU *NewMessagePdu(std::vector<typename NgapMessageToIeType<T>::value *> ies)
{
    auto msgType = static_cast<NgapMessageType>(NgapMessageTypeToEnum<T>::V);

    void *pDescription = nullptr;
    void *pMessage = NewDescFromMessageType(msgType, pDescription);

    for (auto &ie : ies)
        AddProtocolIe(*reinterpret_cast<T *>(pMessage), ie);

    int desc = GetPduDescription(msgType);
    switch (desc)
    {
    case 0:
        return NgapPduFromPduDescription(reinterpret_cast<ASN_NGAP_InitiatingMessage *>(pDescription));
    case 1:
        return NgapPduFromPduDescription(reinterpret_cast<ASN_NGAP_SuccessfulOutcome *>(pDescription));
    case 2:
        return NgapPduFromPduDescription(reinterpret_cast<ASN_NGAP_UnsuccessfulOutcome *>(pDescription));
    default:
        std::terminate();
    }
}

template <typename T>
inline typename asn::ngap::NgapMessageToIeUnionType<T>::value *GetProtocolIe(T *msg, int id, int order = 0)
{
    int found = -1;

    for (int i = 0; i < msg->protocolIEs.list.count; i++)
    {
        auto &item = msg->protocolIEs.list.array[i];
        if (item->id == id)
        {
            found++;
            if (order == found)
                return (typename asn::ngap::NgapMessageToIeUnionType<T>::value *)(&item->value.choice);
        }
    }

    return nullptr;
}

bool IsProtocolIeUsable(const ASN_NGAP_NGAP_PDU &pdu, const asn_TYPE_descriptor_t &ieType);

void *FindProtocolIeInPdu(const ASN_NGAP_NGAP_PDU &pdu, const asn_TYPE_descriptor_t &ieType, int protocolIeId);

bool AddProtocolIeIfUsable(const ASN_NGAP_NGAP_PDU &pdu, const asn_TYPE_descriptor_t &ieType, int protocolIeId,
                           int criticality, const std::function<void(void *)> &ieCreator);

} // namespace asn::ngap