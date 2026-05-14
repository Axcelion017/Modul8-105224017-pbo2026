abstract class LayananPengiriman{
    protected String noResi;
    protected double beratBarang, jarakTempuhdalamKm;

    LayananPengiriman(String noResi, double beratBarang, double jarakTempuhdalamKm){
        this.noResi = noResi;
        this.beratBarang = beratBarang;
        this.jarakTempuhdalamKm = jarakTempuhdalamKm;
    }

    void cetakResi(){
        System.out.println("No Resi: " + noResi);
        System.out.println("Berat Barang: " + beratBarang + " kg");
        System.out.println("Jarak Tempuh: " + jarakTempuhdalamKm + " km");
    }

    abstract double hitungOngkosKirim();
}

interface LacakKargo{
    void updateStatus(String status);
    String cekLokasiTerakhir();
}

interface Asuransi{
    double hitungPremi(double nilaiBarang);
    default void cetakPolis(){
        System.out.println("Polis Asuransi aktif: Menanggung kehilangan dan kerusakan fisik sebesar 100% dari nilai barang.");
    }
}

class PengirimanDarat extends LayananPengiriman implements LacakKargo{
    private String jenisTruk, statusSaatIni;

    PengirimanDarat(String noResi, double beratBarang, double jarakTempuhdalamKm, String jenisTruk){
        super(noResi, beratBarang, jarakTempuhdalamKm);
        this.jenisTruk = jenisTruk;
        this.statusSaatIni = "Menunggu Kurir";
    }

    @Override
    double hitungOngkosKirim() {
        double biayaDasar = (beratBarang * 5000) + (jarakTempuhdalamKm * 2000);
        if(jenisTruk.equalsIgnoreCase("Tronton")){
            return biayaDasar + 150000;
        }
        return biayaDasar;
    }

    public void updateStatus(String status){
        this.statusSaatIni = status;
    }

    public String cekLokasiTerakhir() {
        return "Lokasi Terakhir: " + this.statusSaatIni;
    }
}

class PengirimanUdara extends LayananPengiriman implements LacakKargo, Asuransi{
    private String nomorPenerbangan, statusSaatIni;
    private double nilaiBarang;

    PengirimanUdara(String noResi, double beratBarang, double jarakTempuhdalamKm, String nomorPenerbangan, double nilaiBarang){
        super(noResi, beratBarang, jarakTempuhdalamKm);
        this.nomorPenerbangan = nomorPenerbangan;
        this.nilaiBarang = nilaiBarang;
        this.statusSaatIni = "Menunggu Jadwal Penerbangan";
    }

    @Override
    public double hitungOngkosKirim() {
        return (beratBarang * 25000) + (jarakTempuhdalamKm * 5000);
    }
    

    @Override
    public double hitungPremi(double nilaiBarang) {
        return nilaiBarang * 0.03; 
    }


    @Override
    public void updateStatus(String status) {
        this.statusSaatIni = status;
    }

    @Override
    public String cekLokasiTerakhir() {
        return "Lokasi Terakhir: " + this.statusSaatIni;
    }

    double getNilaiBarang(){
        return this.nilaiBarang;
    }
}

public class App {
    public static void main(String[] args) throws Exception {

        LayananPengiriman[] pengirimanArray = new LayananPengiriman[2];
        pengirimanArray[0] = new PengirimanDarat("DRT-001", 50, 100, "Tronton");;
        pengirimanArray[1] = new PengirimanUdara("UDR-999", 10, 800, "GA-123", 5000000);

        ((PengirimanDarat)pengirimanArray[0]).updateStatus("Sedang di jalan tol Cipali");
        ((PengirimanUdara)pengirimanArray[1]).updateStatus("Transit di Bandara Soekarno-Hatta");

        for(LayananPengiriman p : pengirimanArray){
            p.cetakResi();
            double ongkosKirim = p.hitungOngkosKirim();
            double totalTagihan = ongkosKirim;
            System.out.println("Ongkos Kirim: Rp " + ongkosKirim);
            if(p instanceof LacakKargo){
                System.out.println(((LacakKargo)p).cekLokasiTerakhir());
            }
            if(p instanceof Asuransi){
                Asuransi asuransi = (Asuransi) p;
                asuransi.cetakPolis();
                double premi = asuransi.hitungPremi(((PengirimanUdara)p).getNilaiBarang());
                System.out.println("Premi Asuransi: Rp " + premi);
                totalTagihan += premi;
            }
            System.out.println("Total Tagihan: Rp " + totalTagihan);
            System.out.println("-----------------------------------");
        }
    }
}
