package p03_CadastroCarro;

public class Carro {
    private String modelo;
    private String marca;
    private String placa;
    private String cor;
    private double valor;
    private int anoDeFabricacao;

    public Carro(String modelo, String marca, String placa, String cor, double valor, int anoDeFabricacao) {
        this.modelo = modelo;
        this.marca = marca;
        this.placa = placa;
        this.cor = cor;
        this.valor = valor;
        this.anoDeFabricacao = anoDeFabricacao;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public String getPlaca() {
        return placa;
    }

    public String getCor() {
        return cor;
    }

    public double getValor() {
        return valor;
    }


    public int getAnoDeFabricacao() {
        return anoDeFabricacao;
    }

    @Override
    public String toString() {
        return "Modelo: " + modelo + "\nMarca: " + marca + "\nPlaca: " + placa +
                "\nCor: " + cor + "\nValor: R$ " + valor + "\nAno: " + anoDeFabricacao;
    }
}
