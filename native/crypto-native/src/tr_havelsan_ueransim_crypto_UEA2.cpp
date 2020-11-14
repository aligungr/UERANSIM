// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#include <jni.h>
#include "uea2.h"
#include "jni_utils.h"

extern "C" JNIEXPORT jbyteArray JNICALL Java_tr_havelsan_ueransim_crypto_UEA2_uea2(JNIEnv *pJniEnv, jclass pCls, jlong count, jint bearer, jboolean direction, jbyteArray message, jint bitLength, jbyteArray key)
{
    jsize nLength;

    uint8_t *K = JniConvert::jbytearray_to_uint8array(pJniEnv, key, 1, nullptr);
    uint8_t *M = JniConvert::jbytearray_to_uint8array(pJniEnv, message, 1, &nLength);

    UEA2::f8(K, static_cast<uint32_t>(count), static_cast<uint32_t>(bearer), static_cast<uint32_t>(direction), M, static_cast<uint32_t>(bitLength));

    delete[] K;

    jbyteArray jarr = JniConvert::uint8array_to_jbytearray(pJniEnv, M, nLength);
    
    delete[] M;

    return jarr;
}