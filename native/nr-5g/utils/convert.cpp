#include "convert.hpp"
#include <cassert>

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
