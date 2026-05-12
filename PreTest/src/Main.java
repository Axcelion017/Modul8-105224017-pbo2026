public class Main {
    public static void main(String[] args) throws Exception {
        Programmer k1 = new Programmer("Michael", 5000000);
        System.out.println("Nama: " + k1.nama);
        System.out.println("Gaji: " + k1.hitungGaji());
    }
}
