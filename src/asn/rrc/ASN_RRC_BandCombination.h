/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NR-RRC-Definitions"
 * 	found in "asn/nr-rrc-15.6.0.asn1"
 * 	`asn1c -fcompound-names -pdu=all -findirect-choice -fno-include-deps -gen-PER -no-gen-OER -no-gen-example -D rrc`
 */

#ifndef	_ASN_RRC_BandCombination_H_
#define	_ASN_RRC_BandCombination_H_


#include <asn_application.h>

/* Including external dependencies */
#include "ASN_RRC_FeatureSetCombinationId.h"
#include <BIT_STRING.h>
#include <NativeEnumerated.h>
#include <asn_SEQUENCE_OF.h>
#include <constr_SEQUENCE_OF.h>
#include <constr_SEQUENCE.h>

#ifdef __cplusplus
extern "C" {
#endif

/* Dependencies */
typedef enum ASN_RRC_BandCombination__powerClass_v1530 {
	ASN_RRC_BandCombination__powerClass_v1530_pc2	= 0
} e_ASN_RRC_BandCombination__powerClass_v1530;

/* Forward declarations */
struct ASN_RRC_CA_ParametersEUTRA;
struct ASN_RRC_CA_ParametersNR;
struct ASN_RRC_MRDC_Parameters;
struct ASN_RRC_BandParameters;

/* ASN_RRC_BandCombination */
typedef struct ASN_RRC_BandCombination {
	struct ASN_RRC_BandCombination__bandList {
		A_SEQUENCE_OF(struct ASN_RRC_BandParameters) list;
		
		/* Context for parsing across buffer boundaries */
		asn_struct_ctx_t _asn_ctx;
	} bandList;
	ASN_RRC_FeatureSetCombinationId_t	 featureSetCombination;
	struct ASN_RRC_CA_ParametersEUTRA	*ca_ParametersEUTRA;	/* OPTIONAL */
	struct ASN_RRC_CA_ParametersNR	*ca_ParametersNR;	/* OPTIONAL */
	struct ASN_RRC_MRDC_Parameters	*mrdc_Parameters;	/* OPTIONAL */
	BIT_STRING_t	*supportedBandwidthCombinationSet;	/* OPTIONAL */
	long	*powerClass_v1530;	/* OPTIONAL */
	
	/* Context for parsing across buffer boundaries */
	asn_struct_ctx_t _asn_ctx;
} ASN_RRC_BandCombination_t;

/* Implementation */
/* extern asn_TYPE_descriptor_t asn_DEF_ASN_RRC_powerClass_v1530_9;	// (Use -fall-defs-global to expose) */
extern asn_TYPE_descriptor_t asn_DEF_ASN_RRC_BandCombination;
extern asn_SEQUENCE_specifics_t asn_SPC_ASN_RRC_BandCombination_specs_1;
extern asn_TYPE_member_t asn_MBR_ASN_RRC_BandCombination_1[7];

#ifdef __cplusplus
}
#endif

#endif	/* _ASN_RRC_BandCombination_H_ */
#include <asn_internal.h>