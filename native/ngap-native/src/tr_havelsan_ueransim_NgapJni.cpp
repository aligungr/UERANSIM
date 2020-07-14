#include <jni.h>
#include <NGAP_NGAP-PDU.h>

static constexpr int RESULT_OK = 0;
static constexpr int RESULT_DECODING_FAILED = 1;
static constexpr int RESULT_ENCODING_FAILED = 2;

extern "C" JNIEXPORT jbyteArray JNICALL Java_tr_havelsan_ueransim_NgapJni_convertEncoding(JNIEnv *pEnv, jclass cls, jbyteArray data, jint fromEncoding, jint toEncoding, jintArray result)
{
  jbyte *buffer = pEnv->GetByteArrayElements(data, nullptr);
  size_t buffer_size = static_cast<size_t>(pEnv->GetArrayLength(data));

  NGAP_NGAP_PDU_t *pdu = (NGAP_NGAP_PDU_t *)calloc(1, sizeof(NGAP_NGAP_PDU_t));
  asn_dec_rval_t decode_res = asn_decode(NULL, static_cast<asn_transfer_syntax>(fromEncoding), &asn_DEF_NGAP_NGAP_PDU, (void **)&pdu, buffer, buffer_size);
  pEnv->ReleaseByteArrayElements(data, buffer, 0);

  if (decode_res.code != asn_dec_rval_code_e::RC_OK)
  {
    jint *res_arr = pEnv->GetIntArrayElements(result, nullptr);
    res_arr[0] = RESULT_DECODING_FAILED;
    pEnv->ReleaseIntArrayElements(result, res_arr, 0);
    return nullptr;
  }

  asn_encode_to_new_buffer_result_t encode_res =
      asn_encode_to_new_buffer(NULL, static_cast<asn_transfer_syntax>(toEncoding), &asn_DEF_NGAP_NGAP_PDU, pdu);

  if (encode_res.result.encoded == -1)
  {
    jint *res_arr = pEnv->GetIntArrayElements(result, nullptr);
    res_arr[0] = RESULT_ENCODING_FAILED;
    pEnv->ReleaseIntArrayElements(result, res_arr, 0);
    return nullptr;
  }

  jbyteArray ret = pEnv->NewByteArray(encode_res.result.encoded);
  pEnv->SetByteArrayRegion(ret, 0, encode_res.result.encoded, (jbyte*)encode_res.buffer);

  free(encode_res.buffer);

  return ret;
}