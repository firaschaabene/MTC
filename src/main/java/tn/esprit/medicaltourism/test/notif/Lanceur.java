package tn.esprit.medicaltourism.test.notif;

public class Lanceur {

    public static void main(String a) {
        System.out.println(a);
        Notifieur notifieur = new Notifieur(
                a,
                "",
                TypeNotification.INFO);
        notifieur.start();
    }
}
