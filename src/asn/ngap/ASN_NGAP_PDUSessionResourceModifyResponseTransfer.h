/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "NGAP-IEs.asn"
 * 	`asn1c -pdu=all -fcompound-names -findirect-choice -fno-include-deps -no-gen-OER -gen-PER -no-gen-example -D ngap`
 */

#ifndef	_ASN_NGAP_PDUSessionResourceModifyResponseTransfer_H_
#define	_ASN_NGAP_PDUSessionResourceModifyResponseTransfer_H_


#include <asn_application.h>

/* Including external dependencies */
#include <constr_SEQUENCE.h>

#ifdef __cplusplus
extern "C" {
#endif

/* Forward declarations */
struct ASN_NGAP_UPTransportLayerInformation;
struct ASN_NGAP_QosFlowAddOrModifyResponseList;
struct ASN_NGAP_QosFlowPerTNLInformationList;
struct ASN_NGAP_QosFlowListWithCause;
struct ASN_NGAP_ProtocolExtensionContainer;

/* ASN_NGAP_PDUSessionResourceModifyResponseTransfer */
typedef struct ASN_NGAP_PDUSessionResourceModifyResponseTransfer {
	struct ASN_NGAP_UPTransportLayerInformation	*dL_NGU_UP_TNLInformation;	/* OPTIONAL */
	struct ASN_NGAP_UPTransportLayerInformation	*uL_NGU_UP_TNLInformation;	/* OPTIONAL */
	struct ASN_NGAP_QosFlowAddOrModifyResponseList	*qosFlowAddOrModifyResponseList;	/* OPTIONAL */
	struct ASN_NGAP_QosFlowPerTNLInformationList	*additionalDLQosFlowPerTNLInformation;	/* OPTIONAL */
	struct ASN_NGAP_QosFlowListWithCause	*qosFlowFailedToAddOrModifyList;	/* OPTIONAL */
	struct ASN_NGAP_ProtocolExtensionContainer	*iE_Extensions;	/* OPTIONAL */
	/*
	 * This type is extensible,
	 * possible extensions are below.
	 */
	
	/* Context for parsing across buffer boundaries */
	asn_struct_ctx_t _asn_ctx;
} ASN_NGAP_PDUSessionResourceModifyResponseTransfer_t;

/* Implementation */
extern asn_TYPE_descriptor_t asn_DEF_ASN_NGAP_PDUSessionResourceModifyResponseTransfer;

#ifdef __cplusplus
}
#endif

#endif	/* _ASN_NGAP_PDUSessionResourceModifyResponseTransfer_H_ */
#include <asn_internal.h>
