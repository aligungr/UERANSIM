#include "tr_havelsan_ueransim_crypto_EIA3_128.h"
#include "eia3_128.h"

static uint8_t *jbyteArrayToUint8Array(JNIEnv *env, jbyteArray jba, jsize alignment, jsize *resLength)
{
  jboolean isCopy;
  jbyte *bytes = env->GetByteArrayElements(jba, &isCopy);
  jsize len = env->GetArrayLength(jba);
  jsize padding = (alignment - (len % alignment)) % alignment;

  uint8_t *data = new uint8_t[len + padding];
  for (jsize i = 0; i < len; i++)
    data[i] = static_cast<uint8_t>(bytes[i]);

  env->ReleaseByteArrayElements(jba, bytes, 0);
  if (resLength)
    *resLength = len + padding;
  return data;
}

static uint32_t *jbyteArrayToUint32Array(JNIEnv *env, jbyteArray jba)
{
  jsize len4;

  uint8_t *arr = jbyteArrayToUint8Array(env, jba, 4, &len4);
  uint32_t *res = new uint32_t[len4];

  for (jsize i = 0; i < len4; i++)
  {
    uint8_t b0 = arr[4 * i + 0];
    uint8_t b1 = arr[4 * i + 1];
    uint8_t b2 = arr[4 * i + 2];
    uint8_t b3 = arr[4 * i + 3];

    uint32_t w = (b0 << 24) | (b1 << 16) | (b2 << 8) | (b3 << 0);
    res[i] = w;
  }

  delete[] arr;
  return res;
}

extern "C" JNIEXPORT jint JNICALL Java_tr_havelsan_ueransim_crypto_EIA3_1128_computeMac(JNIEnv *env, jclass cls, jlong count, jint bearer, jboolean direction, jbyteArray message, jint bitLength, jbyteArray key)
{
  auto IK = jbyteArrayToUint8Array(env, key, 1, nullptr);
  auto M = jbyteArrayToUint32Array(env, message);

  auto MAC = EIA3_128::EIA3(IK, static_cast<uint32_t>(count), direction, static_cast<uint32_t>(bearer), static_cast<uint32_t>(bitLength), M);

  delete[] IK;
  delete[] M;

  return static_cast<jint>(MAC);
}