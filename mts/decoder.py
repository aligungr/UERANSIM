import ngap
from pycrate_asn1rt.dictobj import *
from pycrate_asn1rt.asnobj_construct import *
from pycrate_asn1rt.asnobj_basic import *
from pycrate_asn1rt.asnobj_ext import *
from pycrate_asn1rt.asnobj_class import *
import binascii

#hexs = "00000400550002000500260021207e004171000d010011000000000099898877f71001002e04804080402f0201010079000f400001100000011000000110000075005a400118"
#asn_type = ngap.NGAP_PDU_Contents.InitialUEMessage

hexs = "00044018000003000A0002000C00550002000100260005047E00446F"
asn_type = ngap.NGAP_PDU_Descriptions.InitiatingMessage

asn_type.from_aper(binascii.unhexlify(hexs))
print(asn_type.to_json())
