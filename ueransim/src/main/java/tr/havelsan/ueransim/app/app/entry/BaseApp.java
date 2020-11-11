package tr.havelsan.ueransim.app.app.entry;

class BaseApp {

    static void main(String[] args) {
        String path = System.getProperty("user.dir") + "/build/";

        System.load(path + "libcrypto-native.so");
        System.load(path + "libngap-native.so");
        System.load(path + "libapp-native.so");
    }
}
