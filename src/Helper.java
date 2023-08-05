import java.util.Scanner;

public class Helper {

    static Scanner scan = new Scanner(System.in);

    private static String loggedInUsername;
    private static UserRole loggedInUserRole;

    private static void login() {

        while (loggedInUsername == null) {
            System.out.print("Kullanıcı adınızı giriniz = ");
            String username = scan.nextLine();
            System.out.print("Şifrenizi giriniz = ");
            String password = scan.nextLine();

            if (username.equals("admin") && password.equals("Admin123")) {
                loggedInUsername = username;
                loggedInUserRole = UserRole.ADMIN;
                System.out.println("\u001B[32mAdmin\u001B[0m olarak giriş yaptınız");

            } else if (username.equals("user") && password.equals("User123")) {
                loggedInUsername = username;
                loggedInUserRole = UserRole.USER;
                System.out.println("\u001B[32mUser\u001B[0m olarak giriş yaptınız");

            } else {
                System.out.println();
                System.out.println("\u001B[31mGeçersiz kullanıcı adı ve şifre girdiniz.\u001B[0m"); // Bilal hocaya teşekkürler:)
                System.out.println();
            }
        }
    }

    public static void anaMenu() throws InterruptedException {

        login();

        if (loggedInUserRole == UserRole.ADMIN) {
            showAdminMenu();

        } else if (loggedInUserRole == UserRole.USER) {
            showUserMenu();
        }
        String tercih = "";
        {
            tercih = scan.nextLine().toLowerCase();

            {
                kutuphaneBilgileriniYazdir();
                loginAndShowUserMenu(UserRole.ADMIN);
                loginAndShowUserMenu(UserRole.ADMIN);
            }
        }
        projeDurdur();
    }

    public static void kutuphaneBilgileriniYazdir() throws InterruptedException {

        System.out.print("Kütüphane bilgileri yazdırılıyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }
        System.out.println("\n" +
                "\n============= TECHNO STUDY CONFLUENCE =============\n" +
                "\t\t Kutuphane : " + Kutuphane.kutuphaneIsim +
                "\n\t\t Adres : " + Kutuphane.adres +
                "\n\t\t Telefon : " + Kutuphane.telefon);
    }

    private static void loginAndShowUserMenu(UserRole requiredRole) throws InterruptedException {

        System.out.print("Kullanici Adi: ");
        String username = scan.nextLine();
        System.out.print("Sifre: ");
        String password = scan.nextLine();

        UserRole role = authenticateUser(username, password);

        if (role == null) {
            System.out.println("Geçersiz kullanıcı adı veya şifre girdiniz");
        } else if (role == UserRole.USER) {
            showUserMenu();
        } else if (role == UserRole.ADMIN) {
            showAdminMenu();
        } else {
            System.out.println("Bu sayfaya erişim izniniz yok");
        }

        //NOT : Doğrulamadan geçerse role ADMIN veya USER olur... Geçmezse "null" olur

        //TODO null ise "Gecersiz kullanici adi veya sifre." mesajı verin
        //TODO ADMIN ise showAdminMenu() metoduyla admin menüyü gösterin
        //TODO USER ise showUserMenu() metoduyla user menüyü gösterin...
        //TODO Aksi durumlarda "Bu sayfaya erisim izniniz yok." mesajını verin...
        //if-else bloklarıyla şartlar gerçeklenebilir...
    }

    private static UserRole authenticateUser(String username, String password) {

        if (username.equals("admin") && password.equals("Admin123")) {
            return UserRole.ADMIN;

        } else if (username.equals("user") && password.equals("User123")) {
            return UserRole.USER;

        } else {
            return null;
        }
    }

    private static void showAdminMenu() throws InterruptedException {

        String tercih = null;
        while (true) {
            System.out.println(
                    "\n=========== TECHNO STUDY CONFLUENCE ==========\n" +
                            "================== ADMIN MENU ================\n" +
                            "\n" +
                            "\t   1- Uyeler Menu\n" +
                            "\t   2- Kitaplar Menu\n" +
                            "\t   A- ANAMENU\n" +
                            "\t   Q- CIKIS");
            System.out.print("\t   Tercihinizi yapınız = ");

            tercih = scan.nextLine();

            switch (tercih) {

                case "1":
                    UyeManager.uyeMenu();
                    break;
                case "2":
                    KitapManager.kitapMenu();
                    break;
                case "A":
                    anaMenu();
                    break;
                case "Q":
                    projeDurdur();
                    break;
                default:
                    System.out.println();
                    System.out.println("\u001B[31mLütfen geçerli bir tercih yapınız.\u001B[0m");
                    continue;
            }
            break;
        }
    }

    private static void showUserMenu() throws InterruptedException {
        String tercih = null;

        while (tercih == null) {
            {
                System.out.println(
                        "\n========== TECHNO STUDY CONFLUENCE ===========\n" +
                                "================== USER MENU =================\n" +
                                "\n" +
                                "\t   1- Uyeleri Listele\n" +
                                "\t   2- Soyisimden Uye Bulma\n" +
                                "\t   3- Sehire Gore Uye Bulma\n" +
                                "\t   4- Kitaplari Listele\n" +
                                "\t   5- Yazardan Kitap Bulma\n" +
                                "\t   6- Kitap Turu veya Yayin Tarihi Ile Kitap Bulma\n" +
                                "\t   7- Kitap Odunc Al\n" +
                                "\t   8- Kitap Iade Et\n" +
                                "\t   A- ANAMENU\n" +
                                "\t   Q- CIKIS");
                System.out.print("\t   Tercihinizi yapınız = ");

                tercih = scan.nextLine();

                switch (tercih) {
                    case "1":
                        UyeManager.uyeListesiYazdir();
                        break;
                    case "2":
                        UyeManager.soyisimdenUyeBulma();
                        break;
                    case "3":
                        UyeManager.sehreGoreUyeBulma();
                        break;
                    case "4":
                        KitapManager.kitapListesiYazdir();
                        break;
                    case "5":
                        KitapManager.yazardanKitapBulma();
                        break;
                    case "6":
                        KitapManager.turVeyaYayinTarihiIleKitapBulma();
                        break;
                    case "7":
                        KitapManager.kitapOduncAl();
                        break;
                    case "8":
                        KitapManager.kitapIadeEt();
                        break;
                    case "A":
                        anaMenu();
                        break;
                    case "Q":
                        projeDurdur();
                        break;
                    default:
                        System.out.println();
                        System.out.println("\u001B[31mLütfen geçerli bir tercih yapınız.\u001B[0m");
                        tercih = null;
                }
            }
        }
    }

    public static void projeDurdur() {
        System.out.println("Kütüphane projesinden çıktınız. Haftaya görüşmek üzere.");
        System.exit(0);
    }
}
