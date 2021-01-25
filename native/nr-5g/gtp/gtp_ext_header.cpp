//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "gtp_ext_header.hpp"

namespace gtp
{

std::unique_ptr<PduSessionInformation> gtp::PduSessionInformation::Decode(const OctetBuffer &stream)
{
    int startIndex = stream.currentIndex();

    uint8_t octet = stream.read();

    int type = bits::BitRange8<4, 7>(octet);
    if (type != 0 && type != 1)
        return nullptr;

    if (type == 0)
    {
        auto res = std::make_unique<DlPduSessionInformation>();

        auto snp = bits::BitAt<2>(octet);
        res->qmp = bits::BitAt<3>(octet);

        octet = stream.read();

        res->qfi = bits::BitRange8<0, 5>(octet);
        res->rqi = bits::BitAt<6>(octet);
        auto ppp = bits::BitAt<7>(octet);

        if (ppp)
        {
            octet = stream.read();
            res->ppi = bits::BitRange8<5, 7>(octet);
        }

        if (res->qmp)
            res->dlSendingTs = stream.read8L();

        if (snp)
            res->dlQfiSeq = stream.read3I();

        // Consuming padding if any. See 5.5.3.5
        size_t read = stream.currentIndex() - startIndex;
        if ((read - 2) % 4 != 0)
        {
            size_t padding = 4 - ((read - 2) % 4);
            stream.readOctetString(padding);
        }

        return res;
    }
    else
    {
        auto res = std::make_unique<UlPduSessionInformation>();

        auto snp = bits::BitAt<0>(octet);
        auto ulDelay = bits::BitAt<1>(octet);
        auto dlDelay = bits::BitAt<2>(octet);
        res->qmp = bits::BitAt<3>(octet);

        octet = stream.read();
        res->qfi = bits::BitRange8<5, 7>(octet);

        if (res->qmp)
        {
            res->dlSendingTsRepeated = stream.read8L();
            res->dlReceivedTs = stream.read8L();
            res->ulSendingTs = stream.read8L();
        }

        if (dlDelay)
            res->dlDelayResult = stream.read4UI();

        if (ulDelay)
            res->ulDelayResult = stream.read4UI();

        if (snp)
            res->ulQfiSeq = stream.read3I();

        // Consuming padding if any. See 5.5.3.5
        size_t read = stream.currentIndex() - startIndex;
        if ((read - 2) % 4 != 0)
        {
            size_t padding = 4 - ((read - 2) % 4);
            stream.readOctetString(padding);
        }

        return res;
    }
}

bool PduSessionInformation::Encode(const PduSessionInformation &pdu, OctetString &stream)
{
    if (pdu.pduType != 0 && pdu.pduType != 1)
        return false;

    int initialLength = stream.length();

    if (pdu.pduType == 0)
    {
        auto &dl = dynamic_cast<const DlPduSessionInformation &>(pdu);

        auto snp = dl.dlQfiSeq.has_value();

        stream.appendOctet(bits::Ranged8({
            {4, pdu.pduType},
            {1, dl.qmp},
            {1, snp},
            {2, 0},
        }));

        stream.appendOctet(bits::Ranged8({
            {1, dl.ppi.has_value()},
            {1, dl.rqi},
            {6, dl.qfi},
        }));

        if (dl.ppi.has_value())
        {
            stream.appendOctet(bits::Ranged8({
                {3, dl.ppi.value()},
                {5, 0},
            }));
        }

        if (dl.qmp)
            stream.appendOctet8(dl.dlSendingTs.has_value() ? dl.dlSendingTs.value() : 0L);

        if (snp)
            stream.appendOctet3(dl.dlQfiSeq.value());
    }
    else
    {
        auto &ul = dynamic_cast<const UlPduSessionInformation &>(pdu);

        auto snp = ul.ulQfiSeq.has_value();

        stream.appendOctet(bits::Ranged8({
            {4, pdu.pduType},
            {1, ul.qmp},
            {1, ul.dlDelayResult.has_value()},
            {1, ul.ulDelayResult.has_value()},
            {1, snp},
        }));

        stream.appendOctet(bits::Ranged8({
            {2, 0},
            {6, ul.qfi},
        }));

        if (ul.qmp)
        {
            stream.appendOctet8(!ul.dlSendingTsRepeated.has_value() ? 0 : ul.dlSendingTsRepeated.value());
            stream.appendOctet8(!ul.dlReceivedTs.has_value() ? 0 : ul.dlReceivedTs.value());
            stream.appendOctet8(!ul.ulSendingTs.has_value() ? 0 : ul.ulSendingTs.value());
        }

        if (ul.dlDelayResult.has_value())
            stream.appendOctet4(ul.dlDelayResult.value());

        if (ul.ulDelayResult.has_value())
            stream.appendOctet4(ul.ulDelayResult.value());

        if (snp)
            stream.appendOctet3(ul.ulQfiSeq.value());
    }

    // Adjusting padding. See 5.5.3.5
    int written = stream.length() - initialLength;
    if ((written - 2) % 4 != 0)
    {
        int padding = 4 - ((written - 2) % 4);
        stream.appendPadding(padding);
    }
    return true;
}

} // namespace gtp
