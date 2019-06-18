package example.emre.com.emineproject;

public class Sinav {
    String dersAdi;
    String gün;
    String saat;

    public Sinav(String dersAdi, String gün, String saat) {
        this.dersAdi = dersAdi;
        this.gün = gün;
        this.saat = saat;
    }

    public String getDersAdi() {
        return dersAdi;
    }

    public void setDersAdi(String dersAdi) {
        this.dersAdi = dersAdi;
    }

    public String getGün() {
        return gün;
    }

    public void setGün(String gün) {
        this.gün = gün;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }
}
