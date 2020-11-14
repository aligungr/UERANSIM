// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#include <jni.h>
#include "eea3_128.h"
#include "jni_utils.h"

extern "C" JNIEXPORT jbyteArray JNICALL Java_tr_havelsan_ueransim_crypto_EEA3_1128_eea3(JNIEnv *pJniEnv, jclass pCls, jlong count, jint bearer, jboolean direction, jbyteArray message, jint bitLength, jbyteArray key)
{
    jsize nWordLen;

    auto CK = JniConvert::jbytearray_to_uint8array(pJniEnv, key, 1, nullptr);
    auto M = JniConvert::jbytearray_to_uint32array(pJniEnv, message, &nWordLen);

    EEA3_128::EEA3(CK, static_cast<uint32_t>(count), static_cast<uint32_t>(bearer), direction, static_cast<uint32_t>(bitLength), M);

    delete[] CK;

    auto res = JniConvert::uint32array_to_jbytearray(pJniEnv, M, nWordLen);
    
    delete[] M;

    return res;
}
