#include "tr_havelsan_ueransim_crypto_EEA3_128.h"
#include "eea3_128.h"

static uint8_t *jbyteArrayToUint8Array(JNIEnv *env, jbyteArray jba, jsize alignment, jsize *resLength)
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

static uint32_t *jbyteArrayToUint32Array(JNIEnv *env, jbyteArray jba, jsize *resLength)
{
    jsize len4;

    uint8_t *arr = jbyteArrayToUint8Array(env, jba, 4, &len4);
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

static jbyteArray uint32ArrayToJbyteArray(JNIEnv *env, uint32_t *arr, jsize wordLength)
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

#include <iostream>
#include <string>

static char nibbleToHexChar(uint8_t nibble)
{
    if (nibble >= 0 && nibble <= 9)
        return '0' + nibble;
    return 'a' + nibble - 10;
}

static std::string uint8ToHexString(uint8_t value)
{
    uint8_t right = value & 0xF;
    uint8_t left = (value >> 4) & 0xF;
    return std::string(1, nibbleToHexChar(left)) + std::string(1, nibbleToHexChar(right));
}

static std::string uint32ToHexString(uint32_t value)
{
    uint8_t octet3 = (value >> 24) & 0xFF;
    uint8_t octet2 = (value >> 16) & 0xFF;
    uint8_t octet1 = (value >> 8) & 0xFF;
    uint8_t octet0 = value & 0xFF;
    return uint8ToHexString(octet3) + uint8ToHexString(octet2) + uint8ToHexString(octet1) + uint8ToHexString(octet0);
}

JNIEXPORT jbyteArray JNICALL Java_tr_havelsan_ueransim_crypto_EEA3_1128_encrypt(JNIEnv *env, jclass cls, jlong count, jint bearer, jboolean direction, jbyteArray message, jint bitLength, jbyteArray key)
{

    jsize mWordLen;

    auto CK = jbyteArrayToUint8Array(env, key, 1, nullptr);
    auto M = jbyteArrayToUint32Array(env, message, &mWordLen);
    auto C = new uint32_t[mWordLen];

    EEA3_128::EEA3(CK, static_cast<uint32_t>(count), direction, static_cast<uint32_t>(bearer), static_cast<uint32_t>(bitLength), M, C);

    //////
    for (int i = 0; i < mWordLen; i++)
        std::cerr << uint32ToHexString(M[i]) << " ";
    std::cerr << std::endl;
    for (int i = 0; i < mWordLen; i++)
        std::cerr << uint32ToHexString(C[i]) << " ";
    std::cerr << std::endl;
    //////

    delete[] CK;
    delete[] M;

    auto res = uint32ArrayToJbyteArray(env, C, mWordLen);

    delete[] C;

    return res;
}

JNIEXPORT jbyteArray JNICALL Java_tr_havelsan_ueransim_crypto_EEA3_1128_decrypt(JNIEnv *env, jclass cls, jlong count, jint bearer, jboolean direction, jbyteArray message, jint bitLength, jbyteArray key)
{
    return message;
}
