#pragma once

#include <jni.h>

extern "C" JNIEXPORT jint JNICALL Java_tr_havelsan_ueransim_crypto_UIA2_computeMac
  (JNIEnv *, jclass, jlong, jlong, jboolean, jbyteArray, jint, jbyteArray);
