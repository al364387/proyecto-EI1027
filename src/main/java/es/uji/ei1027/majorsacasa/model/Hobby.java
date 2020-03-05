package es.uji.ei1027.majorsacasa.model;

public class Hobby {
    private String type;
    private int idVolunteer;

   public Hobby() {
   }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIdVolunteer() {
        return idVolunteer;
    }

    public void setIdVolunteer(int idVolunteer) {
        this.idVolunteer = idVolunteer;
    }

    @Override
    public String toString() {
        return "Hobby{" +
                "type='" + type + '\'' +
                ", idVolunteer=" + idVolunteer +
                '}';
    }
}

