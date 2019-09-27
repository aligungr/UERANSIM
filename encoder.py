import ngap
from pycrate_asn1rt.dictobj import *
from pycrate_asn1rt.asnobj_construct import *
from pycrate_asn1rt.asnobj_basic import *
from pycrate_asn1rt.asnobj_ext import *
from pycrate_asn1rt.asnobj_class import *
import binascii

asn_type = ngap.NGAP_PDU_Contents.InitialUEMessage
value = {
    "protocolIEs": [
        {
            "id": 85,
            "criticality": "reject",
            "value": ("UserLocationInformation", ("choice-Extensions", {
                "id": 12,
                "criticality": "ignore",
                "value": ("INTEGER", 14)
            }))
        }
    ]
}

asn_type.set_val(value)
print(asn_type.to_aper())
