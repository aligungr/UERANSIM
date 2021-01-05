//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "asn_ngap_msg.hpp"

#include <algorithm>
#include <asn_SEQUENCE_OF.h>
#include <asn_application.h>
#include <vector>

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
}

template <typename T>
inline ASN_NGAP_NGAP_PDU *NewMessagePdu(std::vector<typename NgapMessageToIeType<T>::value *> ies)
{
    // Protocol IE fields must be sorted according to ASN definition order.
    // Using 'present' here because it is consistent with ASN definition order;
    std::stable_sort(ies.begin(), ies.end(),
                     [](typename NgapMessageToIeType<T>::value *a, typename NgapMessageToIeType<T>::value *b) {
                         return a->value.present < b->value.present;
                     });

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
    int found = 0;

    for (int i = 0; i < msg->protocolIEs.list.count; i++)
    {
        auto &item = msg->protocolIEs.list.array[i];
        if (item->id == id)
        {
            order++;
            if (order == found)
                return (typename asn::ngap::NgapMessageToIeUnionType<T>::value *)item;
        }
    }

    return nullptr;
}

} // namespace asn::ngap