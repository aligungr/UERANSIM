#pragma once

#include <jni.h>

extern "C" JNIEXPORT jintArray JNICALL Java_tr_havelsan_ueransim_crypto_ZUC_zuc
  (JNIEnv *, jclass, jbyteArray, jbyteArray, jint);
