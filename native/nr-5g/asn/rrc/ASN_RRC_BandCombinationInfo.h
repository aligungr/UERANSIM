/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NR-InterNodeDefinitions"
 * 	found in "asn/nr-rrc-15.6.0.asn1"
 * 	`asn1c -fcompound-names -pdu=all -findirect-choice -fno-include-deps -gen-PER -no-gen-OER -no-gen-example -D rrc`
 */

#ifndef	_ASN_RRC_BandCombinationInfo_H_
#define	_ASN_RRC_BandCombinationInfo_H_


#include <asn_application.h>

/* Including external dependencies */
#include "ASN_RRC_BandCombinationIndex.h"
#include "ASN_RRC_FeatureSetEntryIndex.h"
#include <asn_SEQUENCE_OF.h>
#include <constr_SEQUENCE_OF.h>
#include <constr_SEQUENCE.h>

#ifdef __cplusplus
extern "C" {
#endif

/* ASN_RRC_BandCombinationInfo */
typedef struct ASN_RRC_BandCombinationInfo {
	ASN_RRC_BandCombinationIndex_t	 bandCombinationIndex;
	struct ASN_RRC_BandCombinationInfo__allowedFeatureSetsList {
		A_SEQUENCE_OF(ASN_RRC_FeatureSetEntryIndex_t) list;
		
		/* Context for parsing across buffer boundaries */
		asn_struct_ctx_t _asn_ctx;
	} allowedFeatureSetsList;
	
	/* Context for parsing across buffer boundaries */
	asn_struct_ctx_t _asn_ctx;
} ASN_RRC_BandCombinationInfo_t;

/* Implementation */
extern asn_TYPE_descriptor_t asn_DEF_ASN_RRC_BandCombinationInfo;
extern asn_SEQUENCE_specifics_t asn_SPC_ASN_RRC_BandCombinationInfo_specs_1;
extern asn_TYPE_member_t asn_MBR_ASN_RRC_BandCombinationInfo_1[2];

#ifdef __cplusplus
}
#endif

#endif	/* _ASN_RRC_BandCombinationInfo_H_ */
#include <asn_internal.h>