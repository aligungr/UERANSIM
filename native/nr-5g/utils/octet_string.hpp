#pragma once

#include <cstddef>
#include <cstdint>
#include <memory>

class OctetString
{
    size_t offset;
    size_t length;
    std::shared_ptr<uint8_t[]> data;

  private:
    OctetString(size_t offset, size_t length, std::shared_ptr<uint8_t[]> &data)
        : offset(offset), length(length), data(data)
    {
    }

  public:
    static OctetString *copyBuffer(uint8_t *data, size_t offset, size_t length);

    static OctetString *copyBuffer(uint8_t *data, size_t length);

    static OctetString *wrapBuffer(std::shared_ptr<uint8_t[]> &data, size_t offset, size_t length);

    static OctetString *wrapBuffer(std::shared_ptr<uint8_t[]> &data, size_t length);

    static OctetString *wrapBuffer(std::shared_ptr<uint8_t[]> &&data, size_t length);

    inline int size() const;

    inline uint8_t &at(size_t i);

    inline uint8_t &at(size_t i) const;

    inline OctetString *sub(size_t index);

    inline OctetString *sub(size_t index, size_t length);
};
