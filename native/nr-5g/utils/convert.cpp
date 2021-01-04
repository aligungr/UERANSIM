//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "convert.hpp"
#include <cassert>
#include <regex>

int Convert::GetIpVersion(const std::string &address)
{
    const std::regex regex4(R"(\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\.|$)){4}\b)");
    const std::regex regex6(
        "(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-"
        "9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-"
        "fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-"
        "9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|"
        "fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,"
        "1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:(("
        "25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))");

    if (std::regex_match(address, regex4))
        return 4;
    if (std::regex_match(address, regex6))
        return 6;
    return 0;
}

std::vector<uint8_t> Convert::HexStringToVector(const std::string &hex)
{
    assert(hex.length() % 2 == 0);

    std::vector<uint8_t> bytes;
    for (unsigned int i = 0; i < hex.length(); i += 2)
    {
        std::string byteString = hex.substr(i, 2);
        char byte = (char)strtol(byteString.c_str(), nullptr, 16);
        bytes.push_back(byte);
    }
    return bytes;
}

octet3 Convert::PlmnToOctet3(const Plmn &plmn)
{
    int mcc = plmn.mcc;
    int mnc = plmn.mnc;
    bool isLongMnc = plmn.isLong;

    uint8_t oct0 = (((mcc / 10) % 10) << 4) | (isLongMnc ? mnc / 100 : 15);
    uint8_t oct1 = ((isLongMnc ? mnc / 100 : 15) << 4) | (mcc % 10);
    uint8_t oct2 = ((mnc % 10) << 4) | ((mnc / 10) % 10);
    return {oct0, oct1, oct2};
}