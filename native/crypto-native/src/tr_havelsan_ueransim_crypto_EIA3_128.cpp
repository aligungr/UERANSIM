#include <jni.h>
#include "eia3_128.h"
#include "jni_utils.h"

extern "C" JNIEXPORT jint JNICALL Java_tr_havelsan_ueransim_crypto_EIA3_1128_computeMac(JNIEnv *pJniEnv, jclass pCls, jlong count, jint bearer, jboolean direction, jbyteArray message, jint bitLength, jbyteArray key)
{
  auto IK = JniConvert::jbytearray_to_uint8array(pJniEnv, key, 1, nullptr);
  auto M = JniConvert::jbytearray_to_uint32array(pJniEnv, message, nullptr);

  auto MAC = EIA3_128::EIA3(IK, static_cast<uint32_t>(count), direction, static_cast<uint32_t>(bearer), static_cast<uint32_t>(bitLength), M);

  delete[] IK;
  delete[] M;

  return static_cast<jint>(MAC);
}