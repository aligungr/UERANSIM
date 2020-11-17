/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.utils;

import org.json.XML;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.yaml.snakeyaml.Yaml;
import tr.havelsan.ueransim.utils.bits.Bit;
import tr.havelsan.ueransim.utils.bits.BitN;
import tr.havelsan.ueransim.utils.exceptions.DecodingException;
import tr.havelsan.ueransim.utils.exceptions.EncodingException;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetN;
import tr.havelsan.ueransim.utils.octets.OctetString;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Utils {

    public static <T> T[] decodeList(OctetInputStream stream, Function<OctetInputStream, T> decoder, int length, Class<T> componentType) {
        int readLen = 0;
        var res = new ArrayList<T>();
        while (readLen < length) {
            int streamIndex = stream.currentIndex();
            res.add(decoder.apply(stream));
            readLen += stream.currentIndex() - streamIndex;
        }
        if (readLen > length)
            throw new DecodingException("Value length exceeds total length!");

        var arr = Array.newInstance(componentType, res.size());
        for (int i = 0; i < res.size(); i++) {
            Array.set(arr, i, res.get(i));
        }
        return (T[]) arr;
    }

    public static int[] fixedBitsToOctetArray(Bit[][] bits) {
        int length = 0;
        for (int i = 0; i < bits.length; i++) {
            for (Bit bit : bits[i]) {
                if (bit != null) {
                    length = Math.max(length, i + 1);
                }
            }
        }

        int[] octets = new int[length];
        for (int i = 0; i < length; i++) {
            int octet = 0;

            for (int j = 0; j < 8; j++) {
                var bit = bits[i][j];
                if (bit == null) {
                    throw new EncodingException(j + "th bit of the " + i
                            + "th octet should not have be null, because that octet contains at least one bit which is not null.");
                }
                octet |= bit.intValue();
                octet <<= 1;
            }

            octets[i] = octet >> 1;
        }

        return octets;
    }

    public static byte[] hexStringToByteArray(String s) {
        if (!isValidHexString(s))
            throw new IllegalArgumentException("hex string contains invalid characters or has bad format: " + s);

        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static boolean isValidHexString(String s) {
        if (s == null || s.length() % 2 != 0)
            return false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') continue;
            if (c >= 'a' && c <= 'f') continue;
            if (c >= 'A' && c <= 'F') continue;
            return false;
        }
        return true;
    }

    public static String byteArrayToHexString(byte[] bytes) {
        final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] getResourceFile(String name) {
        try (var stream = Utils.class.getClassLoader().getResourceAsStream(name)) {
            if (stream == null)
                throw new RuntimeException("resource not found: " + name);
            return stream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getResourceString(String name) {
        return new String(getResourceFile(name), StandardCharsets.UTF_8);
    }

    public static InputStream getResourceStream(String name) {
        return Utils.class.getClassLoader().getResourceAsStream(name);
    }

    public static String normalizeXml(String xml) {
        try {
            var factory = DocumentBuilderFactory.newInstance();
            var builder = factory.newDocumentBuilder();
            var is = new InputSource(new StringReader(xml));
            var document = builder.parse(is);
            document.normalizeDocument();
            trimWhitespace(document.getDocumentElement());
            return nodeToString(document.getDocumentElement(), true, false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String indentXml(String xml) {
        try {
            var factory = DocumentBuilderFactory.newInstance();
            var builder = factory.newDocumentBuilder();
            var is = new InputSource(new StringReader(xml));
            var document = builder.parse(is);
            return nodeToString(document.getDocumentElement(), true, true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void trimWhitespace(Node node) {
        var children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); ++i) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.TEXT_NODE)
                child.setTextContent(child.getTextContent().trim());
            trimWhitespace(child);
        }
    }

    private static String nodeToString(Node node, boolean omitXmlDeclaration, boolean indent) {
        var sw = new StringWriter();
        try {
            var t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, omitXmlDeclaration ? "yes" : "no");
            t.setOutputProperty(OutputKeys.INDENT, indent ? "yes" : "no");
            t.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            t.transform(new DOMSource(node), new StreamResult(sw));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sw.toString();
    }

    public static String padLeft(String string, int totalLength, char character) {
        int need = totalLength - string.length();
        if (need <= 0) return string;
        return String.valueOf(character).repeat(need) + string;
    }

    public static String padRight(String string, int totalLength, char character) {
        int need = totalLength - string.length();
        if (need <= 0) return string;
        return string + String.valueOf(character).repeat(need);
    }

    public static String insertSpaces(String string, int period) {
        if (period < 1) throw new IllegalArgumentException();
        if (string.length() % period != 0) throw new IllegalArgumentException();

        var sb = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            sb.append(string.charAt(i));
            if ((i + 1) % period == 0) sb.append(' ');
        }
        return sb.toString().trim();
    }

    public static boolean unsignedEqual(Object a, Object b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;

        long la = 0, lb = 0;
        boolean difa = false, difb = false;

        if (a instanceof Boolean) la = (boolean) a ? 1 : 0;
        else if (a instanceof Byte) la = (long) (byte) a;
        else if (a instanceof Short) la = (long) (short) a;
        else if (a instanceof Integer) la = (long) (int) a;
        else if (a instanceof Long) la = (long) a;
        else if (a instanceof BitN) la = ((BitN) a).intValue();
        else if (a instanceof OctetN) la = ((OctetN) a).longValue();
        else if (a instanceof IIntValue) la = ((IIntValue) a).intValue();
        else difa = true;

        if (b instanceof Boolean) lb = (boolean) b ? 1 : 0;
        else if (b instanceof Byte) lb = (long) (byte) b;
        else if (b instanceof Short) lb = (long) (short) b;
        else if (b instanceof Integer) lb = (long) (int) b;
        else if (b instanceof Long) lb = (long) b;
        else if (b instanceof BitN) lb = ((BitN) b).intValue();
        else if (b instanceof OctetN) lb = ((OctetN) b).longValue();
        else if (b instanceof IIntValue) lb = ((IIntValue) b).intValue();
        else difb = true;

        if (difa && difb) throw new IllegalArgumentException();
        if (difa || difb) return false;

        return la == lb;
    }

    public static <T> List<T> streamToList(Stream<T> stream) {
        return stream.collect(Collectors.toList());
    }

    /**
     * Padded left, and uses big endian
     */
    public static long toLong(byte[] arr) {
        long value = 0;
        for (int i = 0; i < arr.length; i++) {
            value |= arr[i] & 0xFF;
            if (i != arr.length - 1)
                value <<= 8;
        }
        return value;
    }

    /**
     * Padded left, and uses big endian
     */
    public static long toLong(String hex) {
        return toLong(hexStringToByteArray(hex));
    }

    /**
     * Padded left, and uses big endian
     */
    public static int toInt(byte[] arr) {
        int value = 0;
        for (int i = 0; i < arr.length; i++) {
            value |= arr[i] & 0xFF;
            if (i != arr.length - 1)
                value <<= 8;
        }
        return value;
    }

    /**
     * Padded left, and uses big endian
     */
    public static int toInt(String hex) {
        return toInt(hexStringToByteArray(hex));
    }

    /**
     * Returns an intuitive type name for given class
     * Format: [Enclosing Class Simple Name].[Inner Class Simple Name]
     */
    public static String getTypeName(Class<?> type) {
        if (type.getEnclosingClass() == null) {
            return type.getSimpleName();
        } else {
            return getTypeName(type.getEnclosingClass()) + "." + type.getSimpleName();
        }
    }

    /**
     * Recursively finds null public fields of given object.
     */
    public static void findNullPublicFields(Object object, List<String> output) {
        findNullPublicFields(object, output, "");
    }

    /**
     * Recursively finds null public fields of given object.
     */
    private static void findNullPublicFields(Object object, List<String> output, String currentName) {
        if (object == null)
            return;
        var type = object.getClass();
        var fields = type.getDeclaredFields();

        for (var field : fields) {
            if (!Modifier.isPublic(field.getModifiers()))
                continue;
            if (Modifier.isStatic(field.getModifiers()))
                continue;

            Object value;
            try {
                value = field.get(object);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            String fieldName = currentName.length() == 0 ? field.getName() : currentName + "." + field.getName();
            if (value == null) {
                output.add(fieldName);
            } else {
                findNullPublicFields(value, output, fieldName);
            }
        }
    }

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String xmlToJson(String xml) {
        var jsonString = XML.toJSONObject(xml).toString(2);

        var replace = new HashMap<String, String>();

        Pattern pattern = Pattern.compile("\\{\"([a-zA-Z0-9_-]*)\": \"\"}");
        Matcher m = pattern.matcher(jsonString);
        while (m.find()) {
            if (m.groupCount() >= 1) {
                replace.put(m.group(0), m.group(1));
            }
        }

        for (var entry : replace.entrySet()) {
            jsonString = jsonString.replace(entry.getKey(), Json.toJson(entry.getValue()));
        }

        return jsonString;
    }

    public static String stackTraceString(Exception e) {
        var sw = new StringWriter();
        var pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    /**
     * Converts yaml string to json string
     */
    public static String convertYamlToJson(String yaml) {
        Yaml y = new Yaml();
        Map<String, Object> map = (Map<String, Object>) y.load(yaml);
        return Json.toJson(map);
    }

    public static String getCommandLineOption(String[] args, String flag) {
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals(flag)) {
                return args[i + 1];
            }
        }
        return null;
    }

    /**
     * Returns new octet string as 1-octet length is added at beginning of the given octet string.
     * (The length is added using Big Endian)
     * <p>
     * See also:
     * {@link #insertLeadingLength2(OctetString, boolean)}
     * {@link #insertLeadingLength4(OctetString, boolean)}
     */
    public static OctetString insertLeadingLength1(OctetString octetString, boolean bitLength) {
        int length = octetString.length;
        if (bitLength) length *= 8;
        if ((length & 0xFF) != length) {
            throw new IllegalStateException("octet string length cannot fit into 1-octet");
        }

        Octet[] res = new Octet[octetString.length + 1];
        for (int i = 0; i < octetString.length; i++) {
            res[1 + i] = octetString.get1(i);
        }
        res[0] = new Octet(length & 0xFF);
        return new OctetString(res);
    }

    /**
     * Returns new octet string as 2-octet length is added at beginning of the given octet string.
     * (The length is added using Big Endian)
     * <p>
     * See also:
     * {@link #insertLeadingLength1(OctetString, boolean)}
     * {@link #insertLeadingLength4(OctetString, boolean)}
     */
    public static OctetString insertLeadingLength2(OctetString octetString, boolean bitLength) {
        int length = octetString.length;
        if (bitLength) length *= 8;
        if ((length & 0xFFFF) != length) {
            throw new IllegalStateException("octet string length cannot fit into 2-octets");
        }

        Octet[] res = new Octet[octetString.length + 2];
        for (int i = 0; i < octetString.length; i++) {
            res[2 + i] = octetString.get1(i);
        }
        res[0] = new Octet(length >> 8 & 0xFF);
        res[1] = new Octet(length & 0xFF);
        return new OctetString(res);
    }

    /**
     * Returns new octet string as 4-octet length is added at beginning of the given octet string.
     * (The length is added using Big Endian)
     * <p>
     * See also:
     * {@link #insertLeadingLength1(OctetString, boolean)}
     * {@link #insertLeadingLength2(OctetString, boolean)}
     */
    public static OctetString insertLeadingLength4(OctetString octetString, boolean bitLength) {
        int length = octetString.length;
        if (bitLength) length *= 8;

        Octet[] res = new Octet[octetString.length + 4];
        for (int i = 0; i < octetString.length; i++) {
            res[4 + i] = octetString.get1(i);
        }
        res[0] = new Octet(length >> 24 & 0xFF);
        res[1] = new Octet(length >> 16 & 0xFF);
        res[2] = new Octet(length >> 8 & 0xFF);
        res[3] = new Octet(length & 0xFF);
        return new OctetString(res);
    }

    /**
     * Returns the IPv4 address of the local host
     */
    public static byte[] getLocalAddress() {
        try {
            return Inet4Address.getLocalHost().getAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the IPv4 address ib bytes of the given host
     */
    public static byte[] getAddress(String host) {
        try {
            return Inet4Address.getByName(host).getAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> merge(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<>(list1);
        list.addAll(list2);
        return list;
    }

    public static String int32ToIp4String(int addr) {
        return byteArrayToIpString(new byte[]{
                (byte) ((addr >> 24) & 0xFF),
                (byte) ((addr >> 16) & 0xFF),
                (byte) ((addr >> 8) & 0xFF),
                (byte) ((addr) & 0xFF),
        });
    }

    public static String byteArrayToIpString(byte[] ipAddress) {
        if (ipAddress.length == 4) {
            return String.format("%d.%d.%d.%d", ipAddress[0] & 0xFF, ipAddress[1] & 0xFF, ipAddress[2] & 0xFF, ipAddress[3] & 0xFF);
        } else if (ipAddress.length == 16) {
            try {
                return Inet6Address.getByAddress(ipAddress).getHostAddress();
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
        } else throw new IllegalArgumentException("invalid ipAddress");
    }
}
