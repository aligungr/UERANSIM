/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-PDU-Contents"
 * 	found in "NGAP-PDU-Contents.asn"
 * 	`asn1c -pdu=all -fcompound-names -findirect-choice -fno-include-deps -no-gen-OER -gen-PER -no-gen-example -D ngap`
 */

#ifndef	_ASN_NGAP_DistributionReleaseRequest_H_
#define	_ASN_NGAP_DistributionReleaseRequest_H_


#include <asn_application.h>

/* Including external dependencies */
#include "ASN_NGAP_ProtocolIE-Container.h"
#include <constr_SEQUENCE.h>

#ifdef __cplusplus
extern "C" {
#endif

/* ASN_NGAP_DistributionReleaseRequest */
typedef struct ASN_NGAP_DistributionReleaseRequest {
	ASN_NGAP_ProtocolIE_Container_123P112_t	 protocolIEs;
	/*
	 * This type is extensible,
	 * possible extensions are below.
	 */
	
	/* Context for parsing across buffer boundaries */
	asn_struct_ctx_t _asn_ctx;
} ASN_NGAP_DistributionReleaseRequest_t;

/* Implementation */
extern asn_TYPE_descriptor_t asn_DEF_ASN_NGAP_DistributionReleaseRequest;
extern asn_SEQUENCE_specifics_t asn_SPC_ASN_NGAP_DistributionReleaseRequest_specs_1;
extern asn_TYPE_member_t asn_MBR_ASN_NGAP_DistributionReleaseRequest_1[1];

#ifdef __cplusplus
}
#endif

#endif	/* _ASN_NGAP_DistributionReleaseRequest_H_ */
#include <asn_internal.h>
