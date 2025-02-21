package p03_CadastroCarro;

import javax.swing.*;

import java.util.ArrayList;

public class Menu {
    public static void exibirMenu(Cadastro cadastro, ArrayList<Carro> carros) {
        int op;
        do {
            try {
                op = Integer.parseInt(JOptionPane.showInputDialog("MENU - Cadastro de Carros\n\n" + "1 - Cadastrar Carro\n" + "2 - Excluir Cadastro\n" + "3 - Listar Carros\n" + "4 - Buscar Carro por Modelo\n" + "5 - Listar Carro mais novo e mais velho\n" + "6 -  Carro mais barato e mais caro\n" + "0 - Sair\n\n" + "Escolha uma opção:"));

                switch (op) {
                    case 1 -> cadastro.cadastrarCarro(carros);
                    case 2 -> cadastro.excluirCarro(carros);
                    case 3 -> cadastro.listarCarros(carros);
                    case 4 -> cadastro.localizarModelo(carros);
                    case 5 -> cadastro.carroNovoAntigo(carros);
                    case 6 -> cadastro.carroBaratoCaro(carros);
                    case 0 -> JOptionPane.showMessageDialog(null, "Encerrando...");
                    default -> JOptionPane.showMessageDialog(null, "Opção inválida! Tente novamente.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro: Insira um número válido.");
                op = -1; // Força a repetição
            }
        } while (op != 0);
    }
}

//Cadastro (modelo, marca, valor,cor,ano, placa)
//localizar por modelo
//listar por ano (Mais velho e mais novo)
//listar por valor ((mais barato e mais caro) fazer um JOpane com seleção)



