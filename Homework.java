package example.emre.com.emineproject;

public class Homework {
    String dersAdi;
    String konu;
    String bitisTarihi;
    String aciklama;

    public Homework(String dersAdi, String konu, String bitisTarihi, String aciklama) {
        this.dersAdi = dersAdi;
        this.konu = konu;
        this.bitisTarihi = bitisTarihi;
        this.aciklama = aciklama;
    }

    public String getDersAdi() {
        return dersAdi;
    }

    public void setDersAdi(String dersAdi) {
        this.dersAdi = dersAdi;
    }

    public String getKonu() {
        return konu;
    }

    public void setKonu(String konu) {
        this.konu = konu;
    }

    public String getBitisTarihi() {
        return bitisTarihi;
    }

    public void setBitisTarihi(String bitisTarihi) {
        this.bitisTarihi = bitisTarihi;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }
}
