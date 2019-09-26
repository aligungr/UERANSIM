import ngap
import json
from pycrate_asn1rt.dictobj import *
from pycrate_asn1rt.asnobj_construct import *
from pycrate_asn1rt.asnobj_basic import *
from pycrate_asn1rt.asnobj_ext import *

MAP = {}


def make_ref_id_int(element):
    return "$" + element._tr._typeref.called[0] + "." + element._tr._typeref.called[1]


def make_ref_id_enum(element):
    return "$" + element._tr._typeref.called[0] + "." + element._tr._typeref.called[1]


def make_constraints(constraint_list):
    res = []
    for item in constraint_list:
        if type(item) is ASN1RangeInt:
            res.append({
                "constraint": "range-int",
                "lower-bound": item.lb,
                "upper-bound": item.ub
            })
        else:
            raise RuntimeError("not implemented yet")
    return res


def translate_element(element):
    if type(element) is SEQ:
        return translate_sequence(element)
    elif type(element) is SEQ_OF:
        return translate_sequence_of(element)
    elif type(element) is INT:
        return translate_int(element)
    elif type(element) is ENUM:
        return translate_enum(element)
    elif type(element) is OPEN:
        return translate_open(element)
    else:
        raise RuntimeError("not implemented yet")


def translate_sequence(element):
    assert not element._const_tab
    assert not element._const_val
    assert not element._tr
    if type(element._cont) is ASN1Dict:
        obj = {
            "type": "sequence",
            "properties": {}
        }
        for item in element._cont:
            if type(item) is str:
                obj["properties"][item] = translate_element(element._cont[item])
            else:
                raise RuntimeError("not implemented yet")
        return obj
    else:
        raise RuntimeError("not implemented yet")


def translate_sequence_of(element):
    obj = {
        "type": "sequence_of",
        "items": translate_element(element._cont)
    }
    return obj


def translate_int(element):
    ref_id = make_ref_id_int(element)
    obj = {
        "type": "integer",
        "constraints": make_constraints(element._tr._const_val.root)
    }
    MAP[ref_id] = obj
    return ref_id


def translate_enum(element):
    ref_id = make_ref_id_enum(element)
    obj = {
        "type": "enum",
        "values": {}
    }
    for item in element._cont:
        obj["values"][str(item)] = element._cont[item]
    MAP[ref_id] = obj
    return ref_id


def translate_open(element):
    return 45


print(json.dumps(translate_element(ngap.NGAP_PDU_Contents.NGSetupRequest)))

print()
print(json.dumps(MAP["$NGAP-CommonDataTypes.ProtocolIE-ID"]))
