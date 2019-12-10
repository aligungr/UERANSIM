package tr.havelsan.ueransim.web;

@FunctionalInterface
public interface ISender {
    void send(String type, Object element);
}
