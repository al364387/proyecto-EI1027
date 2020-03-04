package es.uji.ei1027.majorsacasa.model;

public class Contract {
    private String number;
    private boolean catering;
    private boolean nursing;
    private boolean cleaning;

    public Contract() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isCatering() {
        return catering;
    }

    public void setCatering(boolean catering) {
        this.catering = catering;
    }

    public boolean isNursing() {
        return nursing;
    }

    public void setNursing(boolean nursing) {
        this.nursing = nursing;
    }

    public boolean isCleaning() {
        return cleaning;
    }

    public void setCleaning(boolean cleaning) {
        this.cleaning = cleaning;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "number='" + number + '\'' +
                ", catering=" + catering +
                ", nursing=" + nursing +
                ", cleaning=" + cleaning +
                '}';
    }
}
