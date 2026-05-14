import java.util.ArrayList;
abstract class Pembayaran{
    protected String namaPembayar;
    protected double nominal;

    Pembayaran(String namaPembayar, double nominal){
        this.namaPembayar = namaPembayar;
        this.nominal = nominal;
    }

    void tampilkanDetail(){
        System.out.println("Nama Pembayar: " + this.namaPembayar);
        System.out.println("Nominal: Rp" + this.nominal);
    }

    abstract void prosesPembayaran();
}

interface Keamanan{
    boolean autentikasi();
}

class KartuKredit extends Pembayaran implements Keamanan{
    private String nomorKartu;

    KartuKredit(String namaPembayar, double nominal, String nomorKartu){
        super(namaPembayar, nominal);
        this.nomorKartu = nomorKartu;
    }

    @Override
    void prosesPembayaran() {
        double biayaAdmin = this.nominal * 0.02; // Biaya tambahan 2%
        this.nominal += biayaAdmin; // Menambahkan biaya tambahan 2%
        System.out.println("\nBiaya tambahan 2%: Rp" + biayaAdmin);
        System.out.println("Total Tagihan yang harus dibayar: Rp" + this.nominal);
    }

    @Override
    public boolean autentikasi(){
        System.out.print("Autentikasi PIN berhasil.");
        return true; // Contoh sederhana, seharusnya ada logika autentikasi yang lebih kompleks
    }
}

class Ewallet extends Pembayaran implements Keamanan{
    private String nomorHp;

    Ewallet(String namaPembayar, double nominal, String nomorHp){
        super(namaPembayar, nominal);
        this.nomorHp = nomorHp;
    }

    @Override
    void prosesPembayaran() {
        System.out.println("\nTotal Tagihan yang harus dibayar: Rp" + this.nominal);
    }

    @Override
    public boolean autentikasi(){
        System.out.print("Autentikasi berhasil.");
        return true; // Contoh sederhana, seharusnya ada logika autentikasi yang lebih kompleks
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        ArrayList<Pembayaran> daftar = new ArrayList<>();

        daftar.add(new KartuKredit("Michael", 1000000, "1234-5678-9012-3456"));
        daftar.add(new Ewallet("Alex", 500000, "081234567890"));

        for (Pembayaran p : daftar) {
            p.tampilkanDetail();
            if (p instanceof Keamanan) {
                if(((Keamanan) p).autentikasi()){
                    p.prosesPembayaran();
                }else{
                    System.out.println("\nAutentikasi gagal. Pembayaran tidak diproses.");
                }
            }
            System.out.println();
        }
    }
}
