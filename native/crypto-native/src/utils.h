#pragma once

#include <string>
#include <cstdint>
#include <jni.h>

namespace Utils
{
    char nibbleToHexChar(uint8_t nibble);

    std::string uint8ToHexString(uint8_t value);

    std::string uint32ToHexString(uint32_t value);

    std::string byteArrayToHexString(uint8_t* arr, size_t length);

    uint8_t *jbyteArrayToUint8Array(JNIEnv *env, jbyteArray jba, jsize alignment, jsize *resLength);

    uint32_t *jbyteArrayToUint32Array(JNIEnv *env, jbyteArray jba, jsize *resLength);

    jbyteArray uint32ArrayToJbyteArray(JNIEnv *env, uint32_t *arr, jsize wordLength);

    jintArray int32ArrayToJintArray(JNIEnv *env, int32_t *arr, jsize length);
}