import ngap
from pycrate_asn1rt.dictobj import *


def compile_element(element):
    if type(element) is ASN1Dict:
        return compile_object(element)
    if type(element) is str:
        return compile_primitive(element)
    if type(element) is tuple:
        if element[0] == "SEQUENCE":
            return compile_object(element[1])
        elif element[0] == "SEQUENCE OF":
            return compile_array(element[1])
        elif element[0] == "OPEN_TYPE":
            return compile_open_type(element[1])
        elif element[0] == "CHOICE":
            return compile_choice(element[1])
        else:
            raise RuntimeError("not implemented yet")
    raise RuntimeError("not implemented yet")


def compile_object(element):
    if not type(element) is ASN1Dict:
        raise RuntimeError("not implemented yet")
    obj = {
        "type": "object",
        "properties": {}
    }
    for item in element._index:
        if not type(item) is str:
            raise RuntimeError("not implemented yet")
        obj["properties"][item] = compile_element(element._dict[item])
    return obj


def compile_array(element):
    return {"type": "array", "items": compile_element(element)}


def compile_primitive(element):
    return element


def compile_open_type(element):
    if not type(element) is ASN1Dict:
        raise RuntimeError("not implemented yet")
    obj = {
        "type": "open-type",
        "properties": {}
    }
    for item in element._index:
        if type(item) is str:
            obj["properties"][item] = compile_element(element._dict[item])
        elif type(item) is tuple and len(item) == 2:
            if item[0] == "NGAP-IEs":  # todo: burda sıkıntı var gibi
                obj["properties"][item[1]] = compile_element(element._dict[item])
            else:
                raise RuntimeError("not implemented yet")
        else:
            raise RuntimeError("not implemented yet")
    return obj


def compile_choice(element):
    if not type(element) is ASN1Dict:
        raise RuntimeError("not implemented yet")
    obj = {
        "type": "choice",
        "properties": {}
    }
    for item in element._index:
        if not type(item) is str:
            raise RuntimeError("not implemented yet")
        obj["properties"][item] = compile_element(element._dict[item])
    return obj


def compile_proto(element):
    req_proto = element.get_proto(print_recurs=False)  # todo: loop ve ayrı defler
    return compile_element(req_proto)


print(compile_proto(ngap.NGAP_PDU_Contents.NGSetupRequest))
