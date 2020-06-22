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
#include "zuc.h"
#include "jni_utils.h"

extern "C" JNIEXPORT jintArray JNICALL Java_tr_havelsan_ueransim_crypto_ZUC_zuc(JNIEnv *pJniEnv, jclass pCls, jbyteArray key, jbyteArray iv, jint length)
{
    auto K = JniConvert::jbytearray_to_uint8array(pJniEnv, key, 1, nullptr);
    auto IV = JniConvert::jbytearray_to_uint8array(pJniEnv, iv, 1, nullptr);
    auto KS = new uint32_t[length];

    Zuc::Initialize(K, IV);
    Zuc::GenerateKeyStream(KS, static_cast<uint32_t>(length));

    delete[] K;
    delete[] IV;

    auto res = JniConvert::int32array_to_jintarray(pJniEnv, reinterpret_cast<int32_t*>(KS), length);
    delete[] KS;

    return res;
}