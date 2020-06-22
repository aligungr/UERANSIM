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

#include <jni.h>
#include "uea2.h"
#include "jni_utils.h"

extern "C" JNIEXPORT jint JNICALL Java_tr_havelsan_ueransim_crypto_UIA2_computeMac(JNIEnv *pJniEnv, jclass pCls, jlong count, jlong fresh, jboolean direction, jbyteArray message, jint bitLength, jbyteArray key)
{
    uint8_t *K = JniConvert::jbytearray_to_uint8array(pJniEnv, key, 1, nullptr);
    uint8_t *M = JniConvert::jbytearray_to_uint8array(pJniEnv, message, 1, nullptr);

    uint32_t MAC = UEA2::f9(K, static_cast<uint32_t>(count), static_cast<uint32_t>(fresh), static_cast<uint32_t>(direction), M, static_cast<uint64_t>(bitLength));

    delete[] K;
    delete[] M;

    return static_cast<jint>(MAC);
}
