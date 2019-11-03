package model;

/**
 * Created by wolfsoft4 on 15/9/18.
 */

public class RidehistoryModel {

    Integer i1,i2,i3;
    String txtlinha,txtvalor;

    public Integer getI1() {
        return i1;
    }

    public void setI1(Integer i1) {
        this.i1 = i1;
    }

    public Integer getI2() {
        return i2;
    }

    public void setI2(Integer i2) {
        this.i2 = i2;
    }

    public Integer getI3() {
        return i3;
    }

    public void setI3(Integer i3) {
        this.i3 = i3;
    }

    public void setTxlinha(String txtlinha) {
        this.txtlinha = txtlinha;
    }

    public String getTxtlinha() {
        return txtlinha;
    }


    public String getTxtvalor() {
        return txtvalor;
    }

    public void setTxtvalor(String txtvalor) {
        this.txtvalor = txtvalor;
    }

    public RidehistoryModel(Integer i1, Integer i2, Integer i3, String txtlinha, String txtvalor) {
        this.i1 = i1;
        this.i2 = i2;
        this.i3 = i3;
        this.txtlinha = txtlinha;
        this.txtvalor = txtvalor;
    }
}
