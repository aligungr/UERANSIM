/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "NGAP-IEs.asn"
 * 	`asn1c -pdu=all -fcompound-names -findirect-choice -fno-include-deps -no-gen-OER -gen-PER -no-gen-example -D ngap`
 */

#ifndef	_ASN_NGAP_PathSwitchRequestTransfer_H_
#define	_ASN_NGAP_PathSwitchRequestTransfer_H_


#include <asn_application.h>

/* Including external dependencies */
#include "ASN_NGAP_UPTransportLayerInformation.h"
#include "ASN_NGAP_DL-NGU-TNLInformationReused.h"
#include "ASN_NGAP_QosFlowAcceptedList.h"
#include <constr_SEQUENCE.h>

#ifdef __cplusplus
extern "C" {
#endif

/* Forward declarations */
struct ASN_NGAP_UserPlaneSecurityInformation;
struct ASN_NGAP_ProtocolExtensionContainer;

/* ASN_NGAP_PathSwitchRequestTransfer */
typedef struct ASN_NGAP_PathSwitchRequestTransfer {
	ASN_NGAP_UPTransportLayerInformation_t	 dL_NGU_UP_TNLInformation;
	ASN_NGAP_DL_NGU_TNLInformationReused_t	*dL_NGU_TNLInformationReused;	/* OPTIONAL */
	struct ASN_NGAP_UserPlaneSecurityInformation	*userPlaneSecurityInformation;	/* OPTIONAL */
	ASN_NGAP_QosFlowAcceptedList_t	 qosFlowAcceptedList;
	struct ASN_NGAP_ProtocolExtensionContainer	*iE_Extensions;	/* OPTIONAL */
	/*
	 * This type is extensible,
	 * possible extensions are below.
	 */
	
	/* Context for parsing across buffer boundaries */
	asn_struct_ctx_t _asn_ctx;
} ASN_NGAP_PathSwitchRequestTransfer_t;

/* Implementation */
extern asn_TYPE_descriptor_t asn_DEF_ASN_NGAP_PathSwitchRequestTransfer;

#ifdef __cplusplus
}
#endif

#endif	/* _ASN_NGAP_PathSwitchRequestTransfer_H_ */
#include <asn_internal.h>
