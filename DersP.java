package example.emre.com.emineproject;

public class DersP {
    String dersAdi;
    String ogretmenAdi;
    String baslangicSaati;
    String bitisSaati;

    public DersP(String dersAdi, String ogretmenAdi, String baslangicSaati, String bitisSaati) {
        this.dersAdi = dersAdi;
        this.ogretmenAdi = ogretmenAdi;
        this.baslangicSaati = baslangicSaati;
        this.bitisSaati = bitisSaati;
    }

    public String getDersAdi() {
        return dersAdi;
    }

    public void setDersAdi(String dersAdi) {
        this.dersAdi = dersAdi;
    }

    public String getOgretmenAdi() {
        return ogretmenAdi;
    }

    public void setOgretmenAdi(String ogretmenAdi) {
        this.ogretmenAdi = ogretmenAdi;
    }

    public String getBaslangicSaati() {
        return baslangicSaati;
    }

    public void setBaslangicSaati(String baslangicSaati) {
        this.baslangicSaati = baslangicSaati;
    }

    public String getBitisSaati() {
        return bitisSaati;
    }

    public void setBitisSaati(String bitisSaati) {
        this.bitisSaati = bitisSaati;
    }
}
