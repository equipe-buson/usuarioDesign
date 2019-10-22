package model;

public class PontosModel {
    String endereco;
    Double latitude;
    Double logitude;

    public PontosModel(String endereco, Double latitude, Double logitude){
        this.endereco = endereco;
        this.latitude = latitude;
        this.logitude = logitude;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLogitude() {
        return logitude;
    }

    public void setLogitude(Double logitude) {
        this.logitude = logitude;
    }


}
