//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "sra_pdu.hpp"
#include <utils/constants.hpp>

namespace sra
{

static void AppendPlmn(const Plmn &plmn, OctetString &stream)
{
    stream.appendOctet2(plmn.mcc);
    stream.appendOctet2(plmn.mnc);
    stream.appendOctet(plmn.isLongMnc ? 1 : 0);
}

static void AppendGlobalNci(const GlobalNci &nci, OctetString &stream)
{
    AppendPlmn(nci.plmn, stream);
    stream.appendOctet8(nci.nci);
}

static Plmn DecodePlmn(const OctetView &stream)
{
    Plmn res{};
    res.mcc = stream.read2I();
    res.mnc = stream.read2I();
    res.isLongMnc = stream.readI() != 0;
    return res;
}

static GlobalNci DecodeGlobalNci(const OctetView &stream)
{
    GlobalNci res{};
    res.plmn = DecodePlmn(stream);
    res.nci = stream.read8L();
    return res;
}

void EncodeSraMessage(const SraMessage &msg, OctetString &stream)
{
    stream.appendOctet(0x03); // (Just for old RLS compatibility)

    stream.appendOctet(cons::Major);
    stream.appendOctet(cons::Minor);
    stream.appendOctet(cons::Patch);
    stream.appendOctet(static_cast<uint8_t>(msg.msgType));
    if (msg.msgType == EMessageType::CELL_INFO_REQUEST)
    {
        auto &m = (const SraCellInfoRequest &)msg;
        stream.appendOctet4(m.simPos.x);
        stream.appendOctet4(m.simPos.y);
        stream.appendOctet4(m.simPos.z);
    }
    else if (msg.msgType == EMessageType::CELL_INFO_RESPONSE)
    {
        auto &m = (const SraCellInfoResponse &)msg;
        AppendGlobalNci(m.cellId, stream);
        stream.appendOctet4(m.tac);
        stream.appendOctet4(m.dbm);
        stream.appendOctet4(static_cast<int>(m.gnbName.size()));
        stream.appendUtf8(m.gnbName);
        stream.appendOctet4(static_cast<int>(m.linkIp.size()));
        stream.appendUtf8(m.linkIp);
    }
    else if (msg.msgType == EMessageType::PDU_DELIVERY)
    {
        auto &m = (const SraPduDelivery &)msg;
        stream.appendOctet8(m.sti);
        stream.appendOctet(static_cast<uint8_t>(m.pduType));
        stream.appendOctet4(m.pdu.length());
        stream.append(m.pdu);
        stream.appendOctet4(m.payload.length());
        stream.append(m.payload);
    }
}

std::unique_ptr<SraMessage> DecodeSraMessage(const OctetView &stream)
{
    auto first = stream.readI(); // (Just for old RLS compatibility)
    if (first != 3)
        return nullptr;

    if (stream.read() != cons::Major)
        return nullptr;
    if (stream.read() != cons::Minor)
        return nullptr;
    if (stream.read() != cons::Patch)
        return nullptr;

    auto msgType = static_cast<EMessageType>(stream.readI());
    if (msgType == EMessageType::CELL_INFO_REQUEST)
    {
        auto res = std::make_unique<SraCellInfoRequest>();
        res->simPos.x = stream.read4I();
        res->simPos.y = stream.read4I();
        res->simPos.z = stream.read4I();
        return res;
    }
    else if (msgType == EMessageType::CELL_INFO_RESPONSE)
    {
        auto res = std::make_unique<SraCellInfoResponse>();
        res->cellId = DecodeGlobalNci(stream);
        res->tac = stream.read4I();
        res->dbm = stream.read4I();
        res->gnbName = stream.readUtf8String(stream.read4I());
        res->linkIp = stream.readUtf8String(stream.read4I());
        return res;
    }
    else if (msgType == EMessageType::PDU_DELIVERY)
    {
        auto res = std::make_unique<SraPduDelivery>();
        res->sti = stream.read8UL();
        res->pduType = static_cast<EPduType>(stream.readI());
        res->pdu = stream.readOctetString(stream.read4I());
        res->payload = stream.readOctetString(stream.read4I());
        return res;
    }

    return nullptr;
}

} // namespace sra
