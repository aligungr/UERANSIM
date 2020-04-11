#include "tr_havelsan_ueransim_crypto_EIA3_128.h"
#include "eia3_128.h"
#include "utils.h"

extern "C" JNIEXPORT jint JNICALL Java_tr_havelsan_ueransim_crypto_EIA3_1128_computeMac(JNIEnv *env, jclass cls, jlong count, jint bearer, jboolean direction, jbyteArray message, jint bitLength, jbyteArray key)
{
  auto IK = Utils::jbyteArrayToUint8Array(env, key, 1, nullptr);
  auto M = Utils::jbyteArrayToUint32Array(env, message, nullptr);

  auto MAC = EIA3_128::EIA3(IK, static_cast<uint32_t>(count), direction, static_cast<uint32_t>(bearer), static_cast<uint32_t>(bitLength), M);

  delete[] IK;
  delete[] M;

  return static_cast<jint>(MAC);
}