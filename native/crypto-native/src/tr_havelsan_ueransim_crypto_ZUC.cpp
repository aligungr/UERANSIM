// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

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