import com.google.gson.annotations.SerializedName;

public class Cotacao {
    @SerializedName("conversion_rates")
    private ConversionRates conversionRates;

    public ConversionRates getConversionRates() {
        return conversionRates;
    }

    public void setConversionRates(ConversionRates conversionRates) {
        this.conversionRates = conversionRates;
    }
}

