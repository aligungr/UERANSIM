// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#pragma once

#include <string>
#include <cstdint>
#include <jni.h>

namespace JniConvert
{
    uint8_t *jbytearray_to_uint8array(JNIEnv *pJniEnv, jbyteArray pJba, jsize alignment, jsize *pOutLength);

    uint32_t *jbytearray_to_uint32array(JNIEnv *pJniEnv, jbyteArray pJba, jsize *pOutLength);

    jbyteArray uint32array_to_jbytearray(JNIEnv *pJniEnv, uint32_t *pArray, jsize nLength);

    jintArray int32array_to_jintarray(JNIEnv *pJniEnv, int32_t *pArray, jsize nLength);

    jbyteArray uint8array_to_jbytearray(JNIEnv *pJniEnv, uint8_t *pArray, jsize nLength);

    char *jstring2string(JNIEnv *pJniEnv, jstring pString);
}