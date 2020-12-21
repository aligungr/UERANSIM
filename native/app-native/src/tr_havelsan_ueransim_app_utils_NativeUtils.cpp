// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#include "jni_utils.hpp"

#include <unistd.h>
#include <sys/types.h>

extern "C" JNIEXPORT jboolean JNICALL Java_tr_havelsan_ueransim_app_utils_NativeUtils_isRoot(JNIEnv *pEnv, jclass cls)
{
  return geteuid() == 0;
}