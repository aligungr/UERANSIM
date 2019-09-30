import asn2json
from flask import *

asn2json.test()

app = Flask(__name__)


@app.route("/get-all-schemas")
def get_all_schemas():
    return jsonify(asn2json.get_all_schemas())


@app.route("/get-schema-by-name")
def get_schema_by_name():
    user_input = request.args.get('name').split(".")
    asn_type = asn2json.get_type_by_name(user_input[0], user_input[1])
    compiled = asn2json.compile_element(asn_type, None)
    return jsonify(compiled)
