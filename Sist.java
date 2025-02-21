package p03_CadastroCarro;

import java.util.ArrayList;

public class Sist {
    public static void main(String[] args) {
        ArrayList<Carro> carros = new ArrayList<>();
        Cadastro cadastro = new Cadastro();
        Menu.exibirMenu(cadastro, carros);
    }
}
