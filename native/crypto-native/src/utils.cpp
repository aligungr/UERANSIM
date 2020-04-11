#include "utils.h"

char Utils::nibbleToHexChar(uint8_t nibble)
{
    if (nibble >= 0 && nibble <= 9)
        return '0' + nibble;
    return 'a' + nibble - 10;
}

std::string Utils::uint8ToHexString(uint8_t value)
{
    uint8_t right = value & 0xF;
    uint8_t left = (value >> 4) & 0xF;
    return std::string(1, Utils::nibbleToHexChar(left)) + std::string(1, Utils::nibbleToHexChar(right));
}

uint8_t *Utils::jbyteArrayToUint8Array(JNIEnv *env, jbyteArray jba, jsize alignment, jsize *resLength)
{
    jboolean isCopy;
    jbyte *bytes = env->GetByteArrayElements(jba, &isCopy);
    jsize len = env->GetArrayLength(jba);
    jsize padding = (alignment - (len % alignment)) % alignment;

    uint8_t *data = new uint8_t[len + padding];
    for (jsize i = 0; i < len; i++)
        data[i] = static_cast<uint8_t>(bytes[i]);

    env->ReleaseByteArrayElements(jba, bytes, 0);
    if (resLength)
        *resLength = len + padding;
    return data;
}

uint32_t *Utils::jbyteArrayToUint32Array(JNIEnv *env, jbyteArray jba, jsize *resLength)
{
    jsize len4;

    uint8_t *arr = Utils::jbyteArrayToUint8Array(env, jba, 4, &len4);
    len4 /= 4;
    if (resLength)
    {
        *resLength = len4;
    }

    uint32_t *res = new uint32_t[len4];

    for (jsize i = 0; i < len4; i++)
    {
        uint8_t b0 = arr[4 * i + 0];
        uint8_t b1 = arr[4 * i + 1];
        uint8_t b2 = arr[4 * i + 2];
        uint8_t b3 = arr[4 * i + 3];

        uint32_t w = (b0 << 24) | (b1 << 16) | (b2 << 8) | (b3 << 0);
        res[i] = w;
    }

    delete[] arr;
    return res;
}

jbyteArray Utils::uint32ArrayToJbyteArray(JNIEnv *env, uint32_t *arr, jsize wordLength)
{
    jsize octetLength = wordLength * 4;

    jbyte *data = new jbyte[octetLength];
    for (jsize i = 0; i < wordLength; i++)
    {
        uint32_t word = arr[i];

        uint8_t octet3 = (word >> 24) & 0xFF;
        uint8_t octet2 = (word >> 16) & 0xFF;
        uint8_t octet1 = (word >> 8) & 0xFF;
        uint8_t octet0 = word & 0xFF;

        data[i * 4 + 0] = static_cast<jbyte>(octet3);
        data[i * 4 + 1] = static_cast<jbyte>(octet2);
        data[i * 4 + 2] = static_cast<jbyte>(octet1);
        data[i * 4 + 3] = static_cast<jbyte>(octet0);
    }

    jbyteArray ret = env->NewByteArray(octetLength);
    env->SetByteArrayRegion(ret, 0, octetLength, data);
    delete[] data;

    return ret;
}

std::string Utils::uint32ToHexString(uint32_t value)
{
    uint8_t octet3 = (value >> 24) & 0xFF;
    uint8_t octet2 = (value >> 16) & 0xFF;
    uint8_t octet1 = (value >> 8) & 0xFF;
    uint8_t octet0 = value & 0xFF;
    return Utils::uint8ToHexString(octet3) + Utils::uint8ToHexString(octet2) + Utils::uint8ToHexString(octet1) + Utils::uint8ToHexString(octet0);
}

jintArray Utils::int32ArrayToJintArray(JNIEnv *env, int32_t *arr, jsize length)
{
    jintArray ret = env->NewIntArray(length);
    env->SetIntArrayRegion(ret, 0, length, arr);
    return ret;
}

std::string Utils::byteArrayToHexString(uint8_t *arr, size_t length)
{
    std::string s("");
    for (size_t i = 0; i < length; i++)
        s += Utils::uint8ToHexString(arr[i]);
    return s;
}

jbyteArray Utils::byteArrayToJByteArray(JNIEnv *env, uint8_t *arr, jsize length)
{
    jbyteArray ret = env->NewByteArray(length);
    env->SetByteArrayRegion(ret, 0, length, reinterpret_cast<jbyte *>(arr));
    return ret;
}