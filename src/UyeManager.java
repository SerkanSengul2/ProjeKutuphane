import java.util.*;

public class UyeManager extends Veritabani {

    static Scanner scan = new Scanner(System.in);

    public static void uyeMenu() throws InterruptedException {
        String tercih;

        while (true) {
            System.out.println(
                    "\n========== TECHNO STUDY CONFLUENCE =========\n" +
                            "================= UYE MENU =================\n" +
                            "\n" +
                            "\t   1- Uye Listesi Yazdir\t\n" +
                            "\t   2- Soyisimden Uye Bulma\n" +
                            "\t   3- Sehire Gore Uye Bulma\n" +
                            "\t   4- Bilgilerini Girerek Uye Ekleme\n" +
                            "\t   5- Kimlik No Ile Kayit Silme \t\n" +
                            "\t   A- ANAMENU\n" +
                            "\t   Q- CIKIS");
            System.out.print("\t   Tercihinizi yapiniz = ");

            tercih = scan.nextLine();

            switch (tercih) {
                case "1":
                    uyeListesiYazdir();
                    break;
                case "2":
                    soyisimdenUyeBulma();
                    break;
                case "3":
                    sehreGoreUyeBulma();
                    break;
                case "4":
                    uyeEkleme();
                    break;
                case "5":
                    tcNoIleUyeSil();
                    break;
                case "A":
                    Helper.anaMenu();
                    return;
                case "Q":
                    Helper.projeDurdur();
                    break;
                default:
                    System.out.println();
                    System.out.println("\u001B[31mLutfen gecerli bir tercih yapiniz.\u001B[0m");
            }
        }
    }


    public static void tcNoIleUyeSil() throws InterruptedException {

        System.out.print("Silmek istediğiniz üyenin TC No'sunu giriniz = ");
        String TcNo = scan.nextLine();

        try {

            if (uyelerMap.containsKey(TcNo)) {
                uyelerMap.remove(TcNo);
                System.out.print(TcNo + " siliniyor...");
                for (int i = 0; i < 20; i++) {
                    Thread.sleep(100);
                    System.out.print(">");
                }
                System.out.println();
                System.out.println("\u001B[32m" + TcNo + "\u001B[0m" + " TcNo'lu üye silindi.");

            } else {
                System.out.println();
                System.out.println("\u001B[31mSilmek istediğiniz TC No sistemde bulunamadı.\u001B[0m");
            }

        } catch (InterruptedException e) {
            System.out.println("Üye silme işlemi gerçekleşmedi.");
        }
        Helper.anaMenu();
    }

    public static void uyeEkleme() throws InterruptedException {

        System.out.print("Eklemek istediğiniz üyenin TC No'su = ");
        String TcNO = scan.nextLine();

        if (uyelerMap.containsKey(TcNO)) {
            System.out.println("\u001B[32m" + TcNO + "\u001B[0m" + " \u001B[31mTcNo'lu üye zaten sistemde kayıtlı.\u001B[0m");
            Helper.anaMenu();
        }
        System.out.print("İsim = ");
        String Isim = scan.nextLine();
        System.out.print("Soyisim = ");
        String Soyisim = scan.nextLine();
        System.out.print("Şehir = ");
        String sehir = scan.nextLine();
        System.out.print("Doğum Tarihi = ");
        String DogumTarihi = scan.nextLine();
        System.out.println();
        String Uyebilgileri = Isim + ", " + Soyisim + ", " + sehir + ", " + DogumTarihi;
        uyelerMap.put(TcNO, Uyebilgileri);
        System.out.println(("\u001B[32m" + TcNO + "\u001B[0m" + " TcNo'lu Yeni üye eklendi."));
        Helper.anaMenu();
    }

    public static void sehreGoreUyeBulma() throws InterruptedException {

        System.out.print("Aradığınız üyenin şehrini giriniz = ");
        String arananSehir = scan.nextLine().toLowerCase();
        boolean Varmi = false;

        for (Map.Entry<String, String> bul : uyelerMap.entrySet()) {
            String SehirBilgisi = bul.getValue();
            String[] bilgiler = SehirBilgisi.split(", ");
            String uyesehri = bilgiler[2].toLowerCase();

            if (uyesehri.contains(arananSehir)) {
                System.out.println("Aradığınız şehirdeki üyeler = " + bul.getValue());
                Varmi = true;
            }
        }
        if (!Varmi) {
            System.out.println("Aranan şehir bulunamadı.");
        }
        Helper.anaMenu();
    }

    public static void soyisimdenUyeBulma() throws InterruptedException {

        System.out.print("Aradığınız üyenin soyismini giriniz = ");
        String Aranansoyisim = scan.nextLine().toLowerCase();
        boolean Varmi = false;

        for (Map.Entry<String, String> bul : uyelerMap.entrySet()) {
            String UyeBilgisi = bul.getValue();
            String[] bilgiler = UyeBilgisi.split(", ");
            String Soyisim = bilgiler[1].toLowerCase();

            if (Soyisim.contains(Aranansoyisim)) {
                System.out.println("Aradığınız soyisimdeki üyeler = " + bul.getValue());
                Varmi = true;
            }
        }
        if (!Varmi) {
            System.out.println("Aranan üye bulunamadı.");
        }
        Helper.anaMenu();
    }

    public static void uyeListesiYazdir() throws InterruptedException {

        Set<Map.Entry<String, String>> uyelerEntrySet = uyelerMap.entrySet();

        System.out.print("Uye Listesi yazdiriliyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }
        System.out.println(
                "\n========== TECHNO STUDY CONFLUENCE =========\n" +
                        "=============== UYE LISTESI ================\n" +
                        "TcNo : Isim , Soyisim , Sehir , D.Yili");

        for (Map.Entry<String, String> each : uyelerEntrySet) {
            String eachKey = each.getKey();
            String eachValue = each.getValue();
            System.out.println(eachKey + " : " + eachValue + " | ");
        }
        Helper.anaMenu();
    }
}
