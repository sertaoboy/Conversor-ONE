import com.google.gson.annotations.SerializedName;

public class ConversionRates {
    @SerializedName("USD")
    private double usd;
    @SerializedName("BRL")
    private double brl;
    @SerializedName("ARS")
    private double ars;
    @SerializedName("COP")
    private double cop;

    public double getCop() {
        return cop;
    }

    public double getBrl() {
        return brl;
    }

    public double getArs() {
        return ars;
    }

    public double getUsd() {
        return usd;
    }

    public void setUsd(double usd) {
        this.usd = usd;
    }

    public void setCop(double cop) {
        this.cop = cop;
    }

    public void setBrl(double brl) {
        this.brl = brl;
    }

    public void setArs(double ars) {
        this.ars = ars;
    }

}

