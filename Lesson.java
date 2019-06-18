package example.emre.com.emineproject;

public class Lesson {
    String dersAdi;
    String ogretmenAdi;
    String devamDurumu;

    public Lesson(String dersAdi, String ogretmenAdi, String devamDurumu) {
        this.dersAdi = dersAdi;
        this.ogretmenAdi = ogretmenAdi;
        this.devamDurumu = devamDurumu;
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

    public String getDevamDurumu() {
        return devamDurumu;
    }

    public void setDevamDurumu(String devamDurumu) {
        this.devamDurumu = devamDurumu;
    }
}
