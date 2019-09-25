import sys
sys.path.insert(1, 'pycrate')

from binascii import unhexlify, hexlify
import generated

InitiatingMessage = generated.NGAP_PDU_Descriptions.InitiatingMessage

print(InitiatingMessage.get_proto())




exit(0)


b = unhexlify("00000400550002000500260021207e004171000d010011000000000099898877f71001002e04804080402f0201010079000f400001100000011000000110000075005a400118")
InitialUEMessage = generated.NGAP_PDU_Contents.InitialUEMessage
InitialUEMessage.from_aper(b)
print(InitialUEMessage.to_json())

exit(0)

print(InitialUEMessage.get_proto())

Value = {
    "protocolIEs": [
        {
            "id": 85,
            "criticality": "reject",
            "value": ("RAN-UE-NGAP-ID", 5)
        },
        {
            "id": 38,
            "criticality": "reject",
            "value": ("NAS-PDU", unhexlify("7e004171000d010011000000000099898877f71001002e04804080402f020101"))
        },
        #{
        #    "id": 121,
        #    "criticality": "reject",
        #    "value": ("UserLocationInformation", unhexlify("400001100000011000000110000075"))
        #}
    ]
}
InitialUEMessage.set_val(Value)
print(InitialUEMessage.to_asn1())
print(hexlify(InitialUEMessage.to_aper()))


#
#b = unhexlify('000f404600000400550002000500260021207e004171000d010011000000000099898877f71001002e04804080402f0201010079000f400001100000011000000110000075005a400118')
#c = generated.NGAP_PDU_Contents.InitialUEMessage
#c.from_aper(b)

