import os
import shutil

import asn2json
import encoder
import otn
from flask import *
from pycrate_asn1rt.err import *

asn2json.test()
app = Flask(__name__)


def bad_request(message, cause=None):
    return json.dumps({"cause": cause, "error": message}), 400, {'Content-Type': 'application/json'}


def success(data):
    return json.dumps({"data": data}), 200, {'Content-Type': 'application/json'}


@app.route("/get-all-schemas")
def get_all_schemas():
    return success(asn2json.get_all_schemas())


@app.route("/get-schema-by-name")
def get_schema_by_name():
    user_input = request.args.get('name').split(".")
    asn_type = asn2json.get_type_by_name(user_input[0], user_input[1])
    compiled = asn2json.compile_element(asn_type, None)
    return success(compiled)


@app.route("/encode", methods=["POST"])
def encode():
    schema_input = request.args.get("schema")
    if schema_input is None or len(schema_input) == 0:
        return bad_request("'schema name' is expected as url parameter", "bad-request")
    schema_input = schema_input.split(".")
    if len(schema_input) != 2:
        return bad_request("'schema name' has bad format", "bad-request")
    try:
        asn_type = asn2json.get_type_by_name(schema_input[0], schema_input[1])
    except AssertionError:
        return bad_request("schema not found", "bad-request")
    if not request.is_json:
        return bad_request("json body is expected", "bad-request")
    request.get_json(force=True)
    try:
        py_val = otn.json_obj_to_py(request.json)
    except AssertionError as e:
        return bad_request(str(e), "bad-value")
    try:
        res = encoder.aper_encode(asn_type, py_val)
    except (ASN1Err, ASN1ObjErr, ASN1NotSuppErr, ASN1CodecErr, ASN1ASNEncodeErr, ASN1ASNDecodeErr, ASN1PEREncodeErr,
            ASN1PERDecodeErr) as e:
        return bad_request(str(e), "asn-error")
    return success(res)


def dump_all_schemas(root_folder):
    if root_folder.endswith("/"):
        root_folder = root_folder[:-1]

    # Preparing the directory
    if not os.path.exists(root_folder):
        os.makedirs(root_folder)
    for root, dirs, files in os.walk('/path/to/folder'):
        for f in files:
            os.unlink(os.path.join(root, f))
        for d in dirs:
            shutil.rmtree(os.path.join(root, d))

    # Dumping
    for x in asn2json.get_all_schemas():
        name = x.split(".")
        asn_type = asn2json.get_type_by_name(name[0], name[1])
        compiled = asn2json.compile_element(asn_type, None)

        dirName = name[0]
        fileName = name[1]

        if not os.path.exists(root_folder + "/" + dirName):
            os.makedirs(root_folder + "/" + dirName)

        with open(root_folder + "/" + dirName + "/" + fileName + '.json', 'w') as f:
            json.dump(compiled, f, indent=2)

        print(x, "DUMPED")

# if __name__ == '__main__':
#    dump_all_schemas("/home/ali/Desktop/asn2json")
