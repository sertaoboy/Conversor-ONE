# Challenge Conversor de Moedas
- Desafio proposto pelo programa ONE para a entrega de um conversor de moedas desenvolvido em Java

### Classes
- ConversionRates: classe criada para trabalhar com a API ExchangeRate
```java
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
```

- Cotacao: classe criada para ter uma relacao de composicao com a classe `ConversionRates`
```java 
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
```
- Menu: classe criada para realizar as conversoes a medida que o usuario interage com o programa
```java
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public void iniciar() throws IOException, InterruptedException {
        String apikey = "845a86143fe902671dfe741c";
        String url_str = "https://v6.exchangerate-api.com/v6/"+apikey+"/latest/USD";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url_str)).build();
        HttpResponse<String>response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Cotacao cotacao = gson.fromJson(json,Cotacao.class);




        Scanner leitura = new Scanner(System.in);
        int opcao;
        boolean booleano = true;
        while(booleano) {
            System.out.println("********************************************************");
            System.out.println("Bem vindo ao conversor de moedas.");
            System.out.println("1 - Dolar ==> Peso Argentino");
            System.out.println("2 - Peso Argentino ==> Dolar");
            System.out.println("3 - Dolar ==> Real Brasileiro");
            System.out.println("4 - Real Brasileiro ==> Dolar");
            System.out.println("5 - Peso Colombiano ==> Dolar");
            System.out.println("6 - Dolar ==> Peso Colombiano");
            System.out.println("7 - Sair");
            System.out.println("Escolha uma opcao valida.");

            opcao = leitura.nextInt();
            boolean entradaValida = false;
            switch (opcao) {
                case 1:
                    System.out.println("Quantos Dolares deseja converter para Peso Argentino?");
                    double dolaresInseridos = 0;
                    while (!entradaValida) {
                        try {
                            dolaresInseridos = leitura.nextDouble();
                            entradaValida = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Digite um número válido."+e.getMessage());
                            leitura.nextLine(); // Limpa o buffer de entrada
                        }
                    }
                    double dolaresConvertidosEmPesosArgentinos = dolaresInseridos * cotacao.getConversionRates().getArs();
                    System.out.println(dolaresInseridos + " Dolares em Pesos Argentinos: " + dolaresConvertidosEmPesosArgentinos);
                    break;
                case 2:
                    System.out.println("Quantos Pesos Argentinos deseja converter para Dolares?");
                    double pesosArgentinosInseridos = 0;
                    while(!entradaValida) {
                        try {
                            pesosArgentinosInseridos = leitura.nextDouble();
                            entradaValida = true;
                        }catch(InputMismatchException e) {
                            System.out.println("Entrada invalida. Digite um numero valido"+e.getMessage());
                            leitura.nextLine();
                        }
                    double pesosArgentinosConvertidosEmDolar = pesosArgentinosInseridos /cotacao.getConversionRates().getArs();
                    System.out.println(pesosArgentinosInseridos + " Pesos Argentinos em Dolar: "+pesosArgentinosConvertidosEmDolar);
                    }
                    break;
                case 3:
                    System.out.println("Quantos Dolares deseja converter para Real Brasileiro?");
                    double dolarInserido =0;
                    while(!entradaValida) {
                        try {
                            dolarInserido = leitura.nextDouble();
                            entradaValida = true;
                        }catch(InputMismatchException e) {
                            System.out.println("Entrada invalida. Digite um numero valido" + e.getMessage());
                            leitura.nextLine();
                        }
                    double dolaresConvertidosEmReal = dolarInserido * cotacao.getConversionRates().getBrl();
                    System.out.println(dolarInserido + " Dolares em Real: "+dolaresConvertidosEmReal);
                    }
                    break;
                case 4:
                    System.out.println("Quantos Reais Brasileiros deseja converter para Dolares?");
                    double realInserido = 0;
                    while(!entradaValida) {
                        try {
                            realInserido = leitura.nextDouble();
                            entradaValida = true;
                        }catch(InputMismatchException e) {
                            System.out.println("Entrada invalida. Digite um numero valido"+e.getMessage());
                            leitura.nextLine();
                        }
                    double realConvertidoEmDolar = realInserido / cotacao.getConversionRates().getBrl();
                        System.out.println(realInserido + " Reais em Dolar:"+realConvertidoEmDolar);
                    }
                    break;
                case 5:
                    System.out.println("Quantos Pesos Colombianos deseja converter para Dolares?");
                    double pesoColombianoInserido = 0;
                    while(!entradaValida) {
                        try {
                            pesoColombianoInserido = leitura.nextDouble();
                            entradaValida = true;
                        }catch(InputMismatchException e) {
                            System.out.println("Entrada invalida. Digite um numero valido"+e.getMessage());
                            leitura.nextLine();
                        }
                    double pesoColombianoConvertidoEmDolar = pesoColombianoInserido /cotacao.getConversionRates().getCop();
                        System.out.println(pesoColombianoInserido + " Pesos colombianos em Dolares:"+pesoColombianoConvertidoEmDolar);
                    }
                    break;
                case 6:
                    System.out.println("Quantos Dolares deseja converter para Pesos Colombianos?");
                    double dolaresInserido = 0;
                    while(!entradaValida) {
                        try {
                            dolaresInserido = leitura.nextDouble();
                            entradaValida = true;
                        }catch(InputMismatchException e) {
                            System.out.println("Entrada invalida. Digite um numero valido"+e.getMessage());
                            leitura.nextLine();
                        }
                        double dolarConvertidoEmPesoColombiano = dolaresInserido * cotacao.getConversionRates().getCop();
                        System.out.println(dolaresInserido+" Dolares convertidos em Pesos Colombianos:"+dolarConvertidoEmPesoColombiano);
                    }
                    break;
                case 7:
                    System.out.println("Saindo...");
                    booleano = false;
                    break;
            }


        }

    }
}
```