//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "sas_pdu.hpp"
#include <utils/constants.hpp>

namespace sas
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

void EncodeSasMessage(const SasMessage &msg, OctetString &stream)
{
    stream.appendOctet(0x03); // (Just for old RLS compatibility)

    stream.appendOctet(cons::Major);
    stream.appendOctet(cons::Minor);
    stream.appendOctet(cons::Patch);
    stream.appendOctet(static_cast<uint8_t>(msg.msgType));
    if (msg.msgType == SasMessageType::CELL_INFO_REQUEST)
    {
        auto m = (const SasCellInfoRequest &)msg;
        stream.appendOctet4(m.simPos.x);
        stream.appendOctet4(m.simPos.y);
        stream.appendOctet4(m.simPos.z);
    }
    else if (msg.msgType == SasMessageType::CELL_INFO_RESPONSE)
    {
        auto m = (const SasCellInfoResponse &)msg;
        AppendGlobalNci(m.cellId, stream);
        stream.appendOctet4(m.tac);
        stream.appendOctet4(m.dbm);
    }
}

std::unique_ptr<SasMessage> DecodeSasMessage(const OctetView &stream)
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

    auto msgType = static_cast<SasMessageType>(stream.readI());
    if (msgType == SasMessageType::CELL_INFO_REQUEST)
    {
        auto res = std::make_unique<SasCellInfoRequest>();
        res->simPos.x = stream.read4I();
        res->simPos.y = stream.read4I();
        res->simPos.z = stream.read4I();
        return res;
    }
    else if (msgType == SasMessageType::CELL_INFO_RESPONSE)
    {
        auto res = std::make_unique<SasCellInfoResponse>();
        res->cellId = DecodeGlobalNci(stream);
        res->tac = stream.read4I();
        res->dbm = stream.read4I();
        return res;
    }

    return nullptr;
}

} // namespace sas
