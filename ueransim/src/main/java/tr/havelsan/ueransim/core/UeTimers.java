package tr.havelsan.ueransim.core;

public class UeTimers {
    public NasTimer t3346;
    public NasTimer t3502;
    public NasTimer t3510;
    public NasTimer t3511;
    public NasTimer t3512; // periodic registration update timer
    public NasTimer t3517;
    public NasTimer t3519; // transmission with fresh SUCI timer
    public NasTimer t3520;
    public NasTimer t3521;

    public UeTimers() {
        this.t3346 = new NasTimer();
        this.t3502 = new NasTimer();
        this.t3510 = new NasTimer();
        this.t3511 = new NasTimer();
        this.t3512 = new NasTimer();
        this.t3517 = new NasTimer();
        this.t3519 = new NasTimer();
        this.t3520 = new NasTimer();
        this.t3521 = new NasTimer();
    }
}
