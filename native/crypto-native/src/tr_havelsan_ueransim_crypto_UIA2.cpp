// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

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
