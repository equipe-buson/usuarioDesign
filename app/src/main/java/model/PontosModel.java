package model;

import java.util.ArrayList;

public class PontosModel {
    String endereco;
    Double latitude;
    Double logitude;
    ArrayList<PontosModel> pontosBlumenauIlhota = new ArrayList<>();
    ArrayList<PontosModel> pontosBlumenauGaspar = new ArrayList<>();
    ArrayList<PontosModel> pontosIlhotaBlumenau = new ArrayList<>();
    ArrayList<PontosModel> pontosGasparBlumenau = new ArrayList<>();

    public PontosModel(){

    }

    public PontosModel(String endereco, Double latitude, Double logitude){
        this.endereco = endereco;
        this.latitude = latitude;
        this.logitude = logitude;
    }

    public ArrayList<PontosModel> getPontosBlumenauIlhota() {
        return pontosBlumenauIlhota;
    }

    public void setPontosBlumenauIlhota(ArrayList<PontosModel> pontosBlumenauIlhota) {
        this.pontosBlumenauIlhota = pontosBlumenauIlhota;
    }

    public ArrayList<PontosModel> getPontosBlumenauGaspar() {
        return pontosBlumenauGaspar;
    }

    public void setPontosBlumenauGaspar(ArrayList<PontosModel> pontosBlumenauGaspar) {
        this.pontosBlumenauGaspar = pontosBlumenauGaspar;
    }

    public ArrayList<PontosModel> getPontosIlhotaBlumenau() {
        return pontosIlhotaBlumenau;
    }

    public void setPontosIlhotaBlumenau(ArrayList<PontosModel> pontosIlhotaBlumenau) {
        this.pontosIlhotaBlumenau = pontosIlhotaBlumenau;
    }

    public ArrayList<PontosModel> getPontosGasparBlumenau() {
        return pontosGasparBlumenau;
    }

    public void setPontosGasparBlumenau(ArrayList<PontosModel> pontosGasparBlumenau) {
        this.pontosGasparBlumenau = pontosGasparBlumenau;
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
