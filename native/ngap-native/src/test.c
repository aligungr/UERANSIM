#include "stdio.h"
#include "stdlib.h"
#include "string.h"
#include <NGAP_NGAP-PDU.h>
#include <NGAP_InitiatingMessage.h>
#include <NGAP_AMFConfigurationUpdate.h>

unsigned char HexChar (char c)
{
    if ('0' <= c && c <= '9') return (unsigned char)(c - '0');
    if ('A' <= c && c <= 'F') return (unsigned char)(c - 'A' + 10);
    if ('a' <= c && c <= 'f') return (unsigned char)(c - 'a' + 10);
    return 0xFF;
}

int HexToBin (const char* s, unsigned char * buff, int length)
{
    int result;
    if (!s || !buff || length <= 0) return -1;

    for (result = 0; *s; ++result)
    {
        unsigned char msn = HexChar(*s++);
        if (msn == 0xFF) return -1;
        unsigned char lsn = HexChar(*s++);
        if (lsn == 0xFF) return -1;
        unsigned char bin = (msn << 4) + lsn;

        if (length-- <= 0) return -1;
        *buff++ = bin;
    }
    return result;
}

char* hex2bin(char* hex) {
    size_t len = strlen(hex) / 2;
    char* buf = malloc(len);

    HexToBin(hex, buf, len);

    return buf;
}

int main(int argc, char **argv)
{
    char *HEX = "002e4067000004000a40020001005500034003e80026003c3b7e04a50e2f3d007e005e7700083d653908534683907100237e004171000d010011000000000000000000f12e04f0f0f0f02f05040109afaf530101007940135000011001b2c3d4e00001100000755b5fa680";

    void* buffer = hex2bin(HEX);
    size_t bufferSize = strlen(HEX) / 2;

    NGAP_NGAP_PDU_t * pdu = calloc(1, sizeof(NGAP_NGAP_PDU_t));
    asn_dec_rval_t dec = asn_decode(NULL, ATS_ALIGNED_CANONICAL_PER, &asn_DEF_NGAP_NGAP_PDU, &pdu, buffer, bufferSize);

    printf("%ld\n", dec.consumed);
    printf("%d\n", dec.code);

    asn_encode_to_new_buffer_result_t result =
        asn_encode_to_new_buffer(NULL, ATS_CANONICAL_XER, &asn_DEF_NGAP_NGAP_PDU, pdu);

    ssize_t encoded = result.result.encoded;
    printf("%ld\n", result.result.encoded);

    for (ssize_t i = 0; i < encoded; i++) {
        printf("%c", ((char*)result.buffer)[i]);
    }

    /*NGAP_InitiatingMessage_t *initiating_message = calloc(1, sizeof(NGAP_InitiatingMessage_t));
    initiating_message->procedureCode = NGAP_ProcedureCode_id_AMFConfigurationUpdate;
    initiating_message->criticality = NGAP_Criticality_ignore;
    initiating_message->value.present = NGAP_InitiatingMessage__value_PR_AMFConfigurationUpdate;
    initiating_message->value.choice.AMFConfigurationUpdate;

    NGAP_NGAP_PDU_t *pdu = calloc(1, sizeof(NGAP_NGAP_PDU_t));
    pdu->present = NGAP_NGAP_PDU_PR_initiatingMessage;
    pdu->choice.initiatingMessage = initiating_message;

    asn_encode_to_new_buffer_result_t result =
        asn_encode_to_new_buffer(NULL, ATS_NONSTANDARD_PLAINTEXT, &asn_DEF_NGAP_NGAP_PDU, pdu);

    ssize_t encoded = result.result.encoded;
    printf("%ld\n", result.result.encoded);

    for (ssize_t i = 0; i < encoded; i++) {
        printf("%c", ((char*)result.buffer)[i]);
    }
    puts("");*/
}