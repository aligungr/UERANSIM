fun main() {
    val client = SCTPClient
    client.send("::1", 3457, 60, 1, byteArrayOf(65, 65, 65))
}