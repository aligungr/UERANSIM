package tr.havelsan.ueransim.core;

public class UeTimers {
    public final NasTimer t3346; /* MM - ... */
    public final NasTimer t3396; /* SM - ... */

    public final NasTimer t3444; /* MM - ... */
    public final NasTimer t3445; /* MM - ... */

    public final NasTimer t3502; /* MM - ... */
    public final NasTimer t3510; /* MM - Registration Request transmission timer */
    public final NasTimer t3511; /* MM - ... */
    public final NasTimer t3512; /* MM - Periodic registration update timer */
    public final NasTimer t3516; /* MM - 5G AKA - RAND and RES* storing timer */
    public final NasTimer t3517; /* MM - Service Request transmission timer */
    public final NasTimer t3519; /* MM - Transmission with fresh SUCI timer */
    public final NasTimer t3520; /* MM - ... */
    public final NasTimer t3521; /* MM - De-registration transmission timer for not switch off */
    public final NasTimer t3525; /* MM - ... */
    public final NasTimer t3540; /* MM - ... */

    public final NasTimer t3580; /* SM - ... */
    public final NasTimer t3581; /* SM - ... */
    public final NasTimer t3582; /* SM - ... */
    public final NasTimer t3583; /* SM - ... */
    public final NasTimer t3584; /* SM - ... */
    public final NasTimer t3585; /* SM - ... */

    public UeTimers() {
        this.t3346 = new NasTimer();
        this.t3396 = new NasTimer();
        this.t3444 = new NasTimer();
        this.t3445 = new NasTimer();
        this.t3502 = new NasTimer();
        this.t3510 = new NasTimer();
        this.t3511 = new NasTimer();
        this.t3512 = new NasTimer();
        this.t3516 = new NasTimer();
        this.t3517 = new NasTimer();
        this.t3519 = new NasTimer();
        this.t3520 = new NasTimer();
        this.t3521 = new NasTimer();
        this.t3525 = new NasTimer();
        this.t3540 = new NasTimer();
        this.t3580 = new NasTimer();
        this.t3581 = new NasTimer();
        this.t3582 = new NasTimer();
        this.t3583 = new NasTimer();
        this.t3584 = new NasTimer();
        this.t3585 = new NasTimer();
    }
}
