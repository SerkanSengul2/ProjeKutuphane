import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class KitapManager extends Veritabani {

    static Scanner scan = new Scanner(System.in);

    public static void kitapMenu() throws InterruptedException {

        String tercih = "";
        while (true) {
            {// TODO Kullanıcı Çıkış Yapmadığı sürece menüde kalalım...
                System.out.println(
                        "\n============ TECHNO STUDY BOOTCAMP ============\n" +
                                "================== KITAP MENU =================\n" +
                                "\t   1- Kitap Listesi Yazdir\n" +
                                "\t   2- Yazardan Kitap Bulma\n" +
                                "\t   3- Kitap Turu veya Yayin Tarihi Ile Kitap Bulma\n" +
                                "\t   4- Bilgilerini Girerek Kitap Ekleme\n" +
                                "\t   5- Kitap Ismi Ile Kayit Silme \t\n" +
                                "\t   6- Kitap Odunc Al \t\n" +
                                "\t   7- Kitap Iade Et \t\n" +
                                "\t   A- ANAMENU\n" +
                                "\t   Q- CIKIS");
                System.out.print("\t   Tercihinizi yapınız = ");

                //TODO Kullanıcıdan alacağınız tercihe göre ilgili menü metodlarına yönlendirmeler yapın...
                tercih = scan.nextLine();
                switch (tercih) {
                    case "1":
                        kitapListesiYazdir();
                        break;
                    case "2":
                        yazardanKitapBulma();
                        break;
                    case "3":
                        turVeyaYayinTarihiIleKitapBulma();
                        break;
                    case "4":
                        kitapEkle();
                        break;
                    case "5":
                        isimIleKitapSilme();
                        break;
                    case "6":
                        kitapOduncAl();
                        break;
                    case "7":
                        kitapIadeEt();
                        break;
                    case "A":
                        Helper.anaMenu();
                        return;
                    case "Q":
                        Helper.projeDurdur();
                        break;
                    default:
                        System.out.println();
                        System.out.println("\u001B[31mLütfen geçerli bir tercih yapınız.\u001B[0m");
                        break;
                }
            }
        }
    }

    public static void kitapOduncAl() throws InterruptedException {
        System.out.print("Ödünç almak istediğiniz kitabın ismini giriniz = ");
        String kitapIsmi = scan.nextLine();

        if (kitaplarMap.containsKey(kitapIsmi)) {
            String Kitapisimleri = kitaplarMap.get(kitapIsmi);
            kitaplarMap.remove(kitapIsmi);
            oduncalinankitaplar.put(kitapIsmi, Kitapisimleri);
            System.out.println();
            System.out.println("\u001B[32m" + kitapIsmi + "\u001B[0m" + " isimli kitap ödünç alındı.");
        } else {
            System.out.println();
            System.out.println("\u001B[32m" + kitapIsmi + "\u001B[0m" + " isimli kitap kütüphanemizde bulunamadı.");
        }
        Helper.anaMenu();
    }

    public static void kitapIadeEt() throws InterruptedException {

        System.out.print("İade etmek istediğiniz kitabın ismini giriniz = ");
        String kitapIsmi = scan.nextLine();
        if (oduncalinankitaplar.containsKey(kitapIsmi)) {
            String Kitapisimleri = oduncalinankitaplar.get(kitapIsmi);
            oduncalinankitaplar.remove(kitapIsmi);
            kitaplarMap.put(kitapIsmi, Kitapisimleri);
            System.out.println("\u001B[32m" + kitapIsmi + "\u001B[0m" + " isimli kitap iade edildi.");

        } else {
            System.out.println();
            System.out.println("\u001B[32m" + kitapIsmi + "\u001B[0m" + " isimli kitap kütüphanemizde bulunamadı.");
        }

        //TODO Kullanıcıdan alacağınız kitap ismiyle (Map te var olmalı)
        //TODO kitap iade etme metodunu tamamlayın...
        //NOT: Veritabanı'nda iade etmeyle alakalı ikinci bir map 'i tampon gibi kullanmalısınız...
        //Kitap iade edildiğinde,  kitaplarMap 'e geri eklenmeli...

        Helper.anaMenu();
    }

    private static void isimIleKitapSilme() throws InterruptedException {//İPUCU METODU... Bu metodu değiştirmenize gerek yok.
        System.out.print("Silinecek kitabin ismini giriniz = ");
        String silinecekKitap = scan.nextLine();
        String silinecekValue = kitaplarMap.get(silinecekKitap);
        String sonucValue = kitaplarMap.remove(silinecekKitap);
        System.out.print(("\u001B[32m" + silinecekKitap + "\u001B[0m" + " Siliniyor..."));
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }
        System.out.println();
        System.out.print(("\u001B[32m" + silinecekKitap + "\u001B[0m" + " sistemden silindi."));

        //ARTIK UYGUN YERLERDE BEKLEMEDİĞİNİZ SONUÇLAR İÇİN TRY CATCH KULLANABİLİRSİNİZ...
        //////////////////////////////////////////////////////////////////////////////////////////////////
        try {
            boolean sonuc = sonucValue.equals(silinecekValue);
        } catch (Exception e) {
            System.out.println();
            System.out.println("\u001B[31mSilmek istediğiniz kitap sistemde bulunamadı.\u001B[0m");
        }/////////////////////////////////////////////////////////////////////////////////////////////////
    }

    private static void kitapEkle() throws InterruptedException {

        System.out.print("Eklemek istediğiniz kitabın adı = ");
        String KitapAdi = scan.nextLine();
        System.out.print("Yazar adı = ");
        String YazarAdi = scan.nextLine();
        System.out.print("Yayınlanma Tarihi = ");
        String YayinlanmaYili = scan.nextLine();
        KitapTuru kitapTuru = null;
        while (kitapTuru == null) {

            try {
                System.out.print("Kitap türü = ");
                String tur = scan.nextLine();
                kitapTuru = KitapTuru.valueOf(tur);
            } catch (Exception e) {
                System.out.println("Geçersiz bir kitap türü girdiniz. Lütfen kitap türünü \u001B[35mTARIH, POLISIYE, KURGU, ROMAN, DESTAN\u001B[0m olarak giriniz.");
            }
        }

        String KitapBilgileri = YazarAdi + "," + kitapTuru + "," + YayinlanmaYili;
        kitaplarMap.put(KitapAdi, KitapBilgileri);
        System.out.println("\u001B[32m" + KitapAdi + "\u001B[0m" + " isimli kitap kütüphaneye eklendi.");
        Helper.anaMenu();
    }

    public static void turVeyaYayinTarihiIleKitapBulma() throws InterruptedException {

        System.out.print("Aramak istediğiniz kitabın türünü veya yayınlanma yılını giriniz = ");
        String arananBilgi = scan.nextLine().toUpperCase();
        boolean Varmi = false;

        for (Map.Entry<String, String> bul : kitaplarMap.entrySet()) {
            String kitapBilgileri = bul.getValue();
            String[] bilgiler = kitapBilgileri.split(", ");
            String tur = bilgiler[1].toUpperCase();
            String yayinTarihi = bilgiler[2];

            if (tur.contains(arananBilgi) || yayinTarihi.contains(arananBilgi)) {
                System.out.println("Aradığınız kitap = " + bul.getValue());
                Varmi = true;
            }
        }

        if (!Varmi) {
            System.out.println("\u001B[31mAradığınız kriterlere uygun kitap bulunamadı.\u001B[0m");
        }
        Helper.anaMenu();

        System.out.println(
                "\n============ TECHNO STUDY CONFLUENCE ==========\n" +
                        "================= KITAP LISTESI ===============\n" +
                        "Kitap Ismi    :   Yazar Ismi , Kitap Turu , Yayin Yili");

    }


    public static void yazardanKitapBulma() throws InterruptedException {

        System.out.print("Aramak istediğiniz yazarın adını giriniz = ");
        String arananYazar = scan.nextLine().toLowerCase();
        boolean Varmi = false;

        for (Map.Entry<String, String> bul : kitaplarMap.entrySet()) {
            String yazarBilgisi = bul.getValue();
            String[] bilgiler = yazarBilgisi.split(", ");
            String yazar = bilgiler[0].toLowerCase();

            if (yazar.contains(arananYazar)) {
                System.out.println("Aradığınız yazarın kitapları = " + bul.getValue());
                Varmi = true;
            }
        }
        if (!Varmi) {
            System.out.println("\u001B[31mAradığınız yazar bulunamadı.\u001B[0m");
        }
        Helper.anaMenu();
    }

    public static void kitapListesiYazdir() throws InterruptedException { //Üye Listesi Yazdır Metodundan Faydalanabilirsiniz...
        Set<Map.Entry<String, String>> Kitaplar = kitaplarMap.entrySet();

        System.out.println("Kitap Listesi yazdiriliyor...");

        for (Map.Entry<String, String> each : Kitaplar) {
            String eachKey = each.getKey();
            String eachValue = each.getValue();

            System.out.println(eachKey + " : " + eachValue + " | ");
        }
        Helper.anaMenu();
    }

}
