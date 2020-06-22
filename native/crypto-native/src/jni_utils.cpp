/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * @author Ali Güngör (aligng1620@gmail.com)
 */

#include "jni_utils.h"

uint8_t *JniConvert::jbytearray_to_uint8array(JNIEnv *pJniEnv, jbyteArray pJba, jsize alignment, jsize *pOutLength)
{
    jboolean isCopy;
    jbyte *bytes = pJniEnv->GetByteArrayElements(pJba, &isCopy);
    jsize len = pJniEnv->GetArrayLength(pJba);
    jsize padding = (alignment - (len % alignment)) % alignment;

    uint8_t *data = new uint8_t[len + padding];
    for (jsize i = 0; i < len; i++)
        data[i] = static_cast<uint8_t>(bytes[i]);

    pJniEnv->ReleaseByteArrayElements(pJba, bytes, 0);
    if (pOutLength)
        *pOutLength = len + padding;
    return data;
}

uint32_t *JniConvert::jbytearray_to_uint32array(JNIEnv *pJniEnv, jbyteArray pJba, jsize *pOutLength)
{
    jsize len4;

    uint8_t *arr = JniConvert::jbytearray_to_uint8array(pJniEnv, pJba, 4, &len4);
    len4 /= 4;
    if (pOutLength)
        *pOutLength = len4;

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

jbyteArray JniConvert::uint32array_to_jbytearray(JNIEnv *pJniEnv, uint32_t *pArray, jsize nLength)
{
    jsize octetLength = nLength * 4;

    jbyte *data = new jbyte[octetLength];
    for (jsize i = 0; i < nLength; i++)
    {
        uint32_t word = pArray[i];

        uint8_t octet3 = (word >> 24) & 0xFF;
        uint8_t octet2 = (word >> 16) & 0xFF;
        uint8_t octet1 = (word >> 8) & 0xFF;
        uint8_t octet0 = word & 0xFF;

        data[i * 4 + 0] = static_cast<jbyte>(octet3);
        data[i * 4 + 1] = static_cast<jbyte>(octet2);
        data[i * 4 + 2] = static_cast<jbyte>(octet1);
        data[i * 4 + 3] = static_cast<jbyte>(octet0);
    }

    jbyteArray ret = pJniEnv->NewByteArray(octetLength);
    pJniEnv->SetByteArrayRegion(ret, 0, octetLength, data);
    delete[] data;

    return ret;
}

jintArray JniConvert::int32array_to_jintarray(JNIEnv *pJniEnv, int32_t *pArray, jsize nLength)
{
    jintArray ret = pJniEnv->NewIntArray(nLength);
    pJniEnv->SetIntArrayRegion(ret, 0, nLength, pArray);
    return ret;
}

jbyteArray JniConvert::uint8array_to_jbytearray(JNIEnv *pJniEnv, uint8_t *pArray, jsize nLength)
{
    jbyteArray ret = pJniEnv->NewByteArray(nLength);
    pJniEnv->SetByteArrayRegion(ret, 0, nLength, reinterpret_cast<jbyte *>(pArray));
    return ret;
}