/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "NGAP-IEs.asn"
 * 	`asn1c -pdu=all -fcompound-names -findirect-choice -fno-include-deps -no-gen-OER -gen-PER -no-gen-example -D ngap`
 */

#include "ASN_NGAP_InterSystemHOReport.h"

#include "ASN_NGAP_ProtocolExtensionContainer.h"
asn_TYPE_member_t asn_MBR_ASN_NGAP_InterSystemHOReport_1[] = {
	{ ATF_NOFLAGS, 0, offsetof(struct ASN_NGAP_InterSystemHOReport, handoverReportType),
		(ASN_TAG_CLASS_CONTEXT | (0 << 2)),
		+1,	/* EXPLICIT tag at current level */
		&asn_DEF_ASN_NGAP_InterSystemHandoverReportType,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"handoverReportType"
		},
	{ ATF_POINTER, 1, offsetof(struct ASN_NGAP_InterSystemHOReport, iE_Extensions),
		(ASN_TAG_CLASS_CONTEXT | (1 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_ASN_NGAP_ProtocolExtensionContainer_174P142,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"iE-Extensions"
		},
};
static const int asn_MAP_ASN_NGAP_InterSystemHOReport_oms_1[] = { 1 };
static const ber_tlv_tag_t asn_DEF_ASN_NGAP_InterSystemHOReport_tags_1[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
static const asn_TYPE_tag2member_t asn_MAP_ASN_NGAP_InterSystemHOReport_tag2el_1[] = {
    { (ASN_TAG_CLASS_CONTEXT | (0 << 2)), 0, 0, 0 }, /* handoverReportType */
    { (ASN_TAG_CLASS_CONTEXT | (1 << 2)), 1, 0, 0 } /* iE-Extensions */
};
asn_SEQUENCE_specifics_t asn_SPC_ASN_NGAP_InterSystemHOReport_specs_1 = {
	sizeof(struct ASN_NGAP_InterSystemHOReport),
	offsetof(struct ASN_NGAP_InterSystemHOReport, _asn_ctx),
	asn_MAP_ASN_NGAP_InterSystemHOReport_tag2el_1,
	2,	/* Count of tags in the map */
	asn_MAP_ASN_NGAP_InterSystemHOReport_oms_1,	/* Optional members */
	1, 0,	/* Root/Additions */
	2,	/* First extension addition */
};
asn_TYPE_descriptor_t asn_DEF_ASN_NGAP_InterSystemHOReport = {
	"InterSystemHOReport",
	"InterSystemHOReport",
	&asn_OP_SEQUENCE,
	asn_DEF_ASN_NGAP_InterSystemHOReport_tags_1,
	sizeof(asn_DEF_ASN_NGAP_InterSystemHOReport_tags_1)
		/sizeof(asn_DEF_ASN_NGAP_InterSystemHOReport_tags_1[0]), /* 1 */
	asn_DEF_ASN_NGAP_InterSystemHOReport_tags_1,	/* Same as above */
	sizeof(asn_DEF_ASN_NGAP_InterSystemHOReport_tags_1)
		/sizeof(asn_DEF_ASN_NGAP_InterSystemHOReport_tags_1[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_ASN_NGAP_InterSystemHOReport_1,
	2,	/* Elements count */
	&asn_SPC_ASN_NGAP_InterSystemHOReport_specs_1	/* Additional specs */
};
