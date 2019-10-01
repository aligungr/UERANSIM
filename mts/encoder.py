import ngap
from pycrate_asn1rt.dictobj import *
from pycrate_asn1rt.asnobj_construct import *
from pycrate_asn1rt.asnobj_basic import *
from pycrate_asn1rt.asnobj_ext import *
from pycrate_asn1rt.asnobj_class import *
import binascii


# asn_type = ngap.NGAP_PDU_Contents.InitialUEMessage
# value = {
#    "protocolIEs": [
#        {
#            "id": 85,
#            "criticality": "reject",
#            "value": ("RAN-UE-NGAP-ID", 5)
#        },
#        {
#            "id": 38,
#            "criticality": "reject",
#            "value": ("NAS-PDU", unhexlify("7e004171000d010011000000000099898877f71001002e04804080402f020101"))
#        },
#        {
#            "id": 121,
#            "criticality": "reject",
#            "value": ("UserLocationInformation", (
#                "userLocationInformationNR", {
#                    "nR-CGI": {
#                        "pLMNIdentity": unhexlify("000110"),
#                        "nRCellIdentity": (0b000000000000000000010001000000000000, 36) # possible bug
#                    },
#                    "tAI": {
#                        "pLMNIdentity": unhexlify("000110"),
#                        "tAC": unhexlify("000075")
#                    }
#                }
#            ))
#        },
#        {
#            "id": 90,
#            "criticality": "ignore",
#            "value": ("RRCEstablishmentCause", "mo-Signalling")
#        }
#    ]
# }
#
# asn_type.set_val(value)
# print(hexlify(asn_type.to_aper()))

def aper_encode(asn_type, value):
    asn_type.set_val(value)
    h = hexlify(asn_type.to_aper()).hex()
    # asn_type.set_val(None) # kirli bırakılıyor
    return h
