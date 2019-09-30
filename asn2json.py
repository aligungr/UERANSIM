import ngap
from pycrate_asn1rt.dictobj import *
from pycrate_asn1rt.asnobj_construct import *
from pycrate_asn1rt.asnobj_basic import *
from pycrate_asn1rt.asnobj_ext import *
from pycrate_asn1rt.asnobj_class import *


# not: 'constraint belirten' asn1set'in içinde hep tek eleman var kabul edip ona göre yazdık.
# ilerde bir yerde sorun çıkarsa buna da bak

def compile_element(element, parent):
    assert element._ext == None or len(element._ext) == 0

    possible_values_detected, val = False, None
    if hasattr(element, "_const_tab") and element._const_tab:
        const_tab = element._const_tab
        assert type(const_tab) is CLASS
        if hasattr(const_tab, "_val"):
            val = const_tab._val
            assert type(val) is ASN1Set
            assert val.ext == None or len(val.ext) == 0
            if val.root:
                assert type(val.root) is list
                if len(val.root) > 0:
                    possible_values_detected = True
    if possible_values_detected and parent is not None:
        assert type(parent) is dict
        parent["possible-values"] = []
        for item in val.root:
            entry = {}
            for key in item:
                assert type(key) is str
                value = item[key]
                if type(value) is int:
                    entry[key] = value
                elif type(value) is str:
                    entry[key] = value
                else:
                    entry[key] = compile_element(value, entry)
            parent["possible-values"].append(entry)

    result = None

    if type(element) is SEQ:
        result = compile_sequence(element, parent)
    if type(element) is SEQ_OF:
        result = compile_sequence_of(element, parent)
    if type(element) is INT:
        result = compile_int(element, parent)
    if type(element) is ENUM:
        result = compile_enum(element, parent)
    if type(element) is OPEN:
        result = compile_open(element, parent)
    if type(element) is OCT_STR:
        result = compile_octet_string(element, parent)
    if type(element) is CHOICE:
        result = compile_choice(element, parent)
    if type(element) is BIT_STR:
        result = compile_bit_string(element, parent)
    if type(element) is STR_PRINT:
        result = compile_printible_string(element, parent)
    if type(element) is OID:
        result = compile_object_identifier(element, parent)
    if type(element) is CLASS:
        result = compile_class(element, parent)

    if result is not None:
        if hasattr(element, "_typeref") and element._typeref is not None:
            assert element._typeref.ced_path is not None
            assert type(element._typeref.called) == tuple and len(element._typeref.called) == 2
            result["type-ref"] = {
                "called": [element._typeref.called[0], element._typeref.called[1]],
                "ced_path": element._typeref.ced_path
            }
        return result
    assert False, "type not implemented"


def compile_sequence(element, parent):
    assert not element._const_tab
    assert not element._const_val
    if element._cont is None:
        return {}  # dikkat: bazı şeyler birden fazla var ve bazılarında içeriği None
    assert type(element._cont) is ASN1Dict
    assert not element._ext or len(element._ext) == 0
    obj = {
        "type": "sequence",
        "properties": {},
        "mandatory": [],
        "optional": []
    }
    for item in element._root_mand:
        assert type(item) is str
        obj["mandatory"].append(item)
    for item in element._root_opt:
        assert type(item) is str
        obj["optional"].append(item)
    for item in element._cont._dict:
        assert type(item) is str
        value = compile_element(element._cont._dict[item], obj)
        obj["properties"][item] = value
    return obj


def compile_sequence_of(element, parent):
    assert not element._const_tab
    assert not element._const_val
    assert not element._root
    assert not element._ext
    obj = {
        "type": "sequence-of"
    }
    if element._const_sz:  # size constraint is only defined for some types
        assert type(element._const_sz.ra) is int
        obj["max-length"] = element._const_sz.ra

    if element._cont is None:
        obj["items"] = {}  # dikkat: ProtocolIE-Container birden fazla var ve bazılarında içeriği None
    else:
        obj["items"] = compile_element(element._cont, obj)
    return obj


def compile_int(element, parent):
    obj = {
        "type": "integer"
    }
    if element._const_val:
        lb = element._const_val.lb
        ub = element._const_val.ub
        obj["min"] = lb
        obj["max"] = ub
    return obj


def compile_enum(element, parent):
    assert not element._const_val
    obj = {
        "type": "enum",
        "values": {}
    }
    for item in element._cont_rev:
        key = element._cont_rev[item]
        obj["values"][key] = item
    return obj


def compile_open(element, parent):
    assert not element._const_val
    obj = {
        "type": "open-type"
    }
    return obj


def compile_octet_string(element, parent):
    assert not element._const_tab
    assert not element._const_val
    obj = {
        "type": "octet-string"
    }
    if element._const_sz:
        assert type(element._const_sz.ra) is int
        obj["max-length"] = element._const_sz.ra
    if element._const_cont:
        assert not element._const_cont_enc
        obj["content"] = compile_element(element._const_cont, obj)
    return obj


def compile_bit_string(element, parent):
    assert not element._const_cont
    assert not element._const_tab
    assert not element._const_val
    obj = {
        "type": "bit-string"
    }
    if element._const_sz:
        assert type(element._const_sz.ra) is int
        obj["max-length"] = element._const_sz.ra
    return obj


def compile_choice(element, parent):
    # assert not element._const_tab --bazılarında varmış ama ignore edildi
    assert not element._const_val
    obj = {
        "type": "choice",
        "values": {}
    }
    assert type(element._cont) is ASN1Dict
    for item in element._cont._dict:
        assert type(item) is str
        obj["values"][item] = compile_element(element._cont._dict[item], obj)
    return obj


def compile_printible_string(element, parent):
    assert not element._const_tab
    assert not element._const_val
    obj = {
        "type": "printible-string"
    }
    if element._const_sz:
        assert type(element._const_sz.ra) is int
        obj["max-length"] = element._const_sz.ra
    return obj


def compile_object_identifier(element, parent):
    assert not element._const_tab
    assert not element._const_val
    obj = {
        "type": "object-identifier"
    }
    return obj


def compile_class(element, parent):
    assert not element._const_tab
    assert not element._const_val
    assert type(element._cont) is ASN1Dict
    obj = {
        "type": "class",
        "properties": {}
    }
    for item in element._cont._dict:
        assert type(item) is str
        obj["properties"][item] = compile_element(element._cont._dict[item], obj)
    return obj


modules = {
    "NGAP_Constants": ngap.NGAP_Constants,
    "NGAP_CommonDataTypes": ngap.NGAP_CommonDataTypes,
    "NGAP_Containers": ngap.NGAP_Containers,
    "NGAP_IEs": ngap.NGAP_IEs,
    "NGAP_PDU_Contents": ngap.NGAP_PDU_Contents,
    "NGAP_PDU_Descriptions": ngap.NGAP_PDU_Descriptions
}


def test():
    print("====================== Test Started ======================")
    for module_name in modules:
        module = modules[module_name]
        items = getattr(module, "_obj_")
        for item in items:
            compile_element(getattr(module, item.replace("-", "_")), None)
            print(module_name + "." + item + " is OK")
    print("====================== Test Success ======================")
    print()


def get_type_by_name(module_name, type_name):
    module = modules[module_name.replace("-", "_")]
    items = getattr(module, "_obj_")
    for item in items:
        if item == type_name:
            return getattr(module, type_name.replace("-", "_"))
    assert False, "type not found"
