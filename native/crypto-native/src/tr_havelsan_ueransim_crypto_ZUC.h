#include <jni.h>

#pragma once

extern "C" JNIEXPORT jintArray JNICALL Java_tr_havelsan_ueransim_crypto_ZUC_zuc
  (JNIEnv *, jclass, jbyteArray, jbyteArray, jint);
