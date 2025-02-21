package p03_CadastroCarro;

import javax.swing.*;
import java.util.ArrayList;

import static p03_CadastroCarro.ExibirMensagens.*;

public class Cadastro {

    public void cadastrarCarro(ArrayList<Carro> carros) {
        String modelo, marca, placa, cor;

// modelo
        do {
            modelo = JOptionPane.showInputDialog("Modelo do carro");
            if (modelo == null) {
                mensagem("Cadastro cancelado");
                return; // sai se o usuario cancelar
            } else if (modelo.trim().isEmpty()) {
                mensagem("Erro: Modelo é obrigatório");
            }
        } while (modelo.trim().isEmpty());

// marca
        do {
            marca = JOptionPane.showInputDialog("Marca do carro");
            if (marca == null) {
                mensagem("Cadastro cancelado");
                return;
            } else if (marca.trim().isEmpty()) {
                mensagem("Erro: Marca é obrigatório");
            }
        } while (marca.trim().isEmpty());

// placa
        do {
            placa = JOptionPane.showInputDialog("Placa do carro");
            if (placa == null) {
                mensagem("Cadastro cancelado");
                return;
            } else if (placa.length() != 7) {
                mensagem("Erro: Quantidade de caracteres na placa tem que ser igual a 7\nExemplo: ABC1D34");
            } else if (placa.trim().isEmpty()) {
                mensagem("Erro: Placa é obrigatório");
            }
        } while (placa.trim().isEmpty() || placa.length() != 7);

// cor
        do {
            cor = JOptionPane.showInputDialog("Cor do carro");
            if (cor == null) {
                mensagem("Cadastro cancelado");
                return;
            } else if (cor.trim().isEmpty()) {
                mensagem("Erro: Cor é obrigatório");
            }
        } while (cor.trim().isEmpty());

// valor
        boolean validValue = false;
        double valor = 0;

        do {
            try {
                String inputValor = JOptionPane.showInputDialog("Valor do carro");
                if (inputValor == null) {
                    mensagem("Cadastro cancelado");
                }

                assert inputValor != null; // garantir que o valor não seja nulo
                valor = Double.parseDouble(inputValor);

                if (valor > 0) {
                    validValue = true;
                } else {
                    mensagem("Erro: O valor deve ser maior que R$ 0.00");
                }
            } catch (NumberFormatException e) {
                mensagem("Erro: Insira um valor");
            }
        } while (!validValue);

// ano de fabricação
        boolean validoAno = false;
        int anoDeFabricacao = 0;

        do {
            try {
                String inputAno = JOptionPane.showInputDialog("Ano de fabricação do carro");
                if (inputAno == null) {
                    mensagem("Cadastro cancelado");
                    return;
                }

                anoDeFabricacao = Integer.parseInt(inputAno); // <- Agora a variável recebe o valor corretamente

                if (anoDeFabricacao >= 1886 && anoDeFabricacao <= 2025) {
                    validoAno = true;
                } else {
                    mensagem("Erro: O ano deve estar entre 1886 e 2025.");
                }
            } catch (NumberFormatException e) {
                mensagem("Erro: Insira um ano válido");
            }
        } while (!validoAno);

        Carro novoCarro = new Carro(modelo, marca, placa, cor, valor, anoDeFabricacao);
        carros.add(novoCarro);
        mensagem(novoCarro.getModelo() + " - Cadastrado com sucesso");
    }

    public void listarCarros(ArrayList<Carro> carros) {
        if (carros.isEmpty()) {
            mensagem("Nenhum carro cadastrado!");
            return;
        }

        StringBuilder listaCarros = new StringBuilder("Carros cadastrados:\n\n");
        for (Carro carro : carros) {
            listaCarros.append("Modelo: ").append(carro.getModelo()).append("\n");
            listaCarros.append("Marca: ").append(carro.getMarca()).append("\n");
            listaCarros.append("Placa: ").append(carro.getPlaca()).append("\n");
            listaCarros.append("Cor: ").append(carro.getCor()).append("\n");
            listaCarros.append("Ano de Fabricação: ").append(carro.getAnoDeFabricacao()).append("\n");
            listaCarros.append("Valor R$: ").append(String.format("%.2f", carro.getValor())).append("\n");
            listaCarros.append("--------------------------------------------------------\n");
        }

        mensagem(listaCarros.toString());
    }

    public void excluirCarro(ArrayList<Carro> carros) {
        if (carros.isEmpty()) {
            mensagem("Nenhum carro cadastrado!");
            return;
        }

        StringBuilder listaCarros = new StringBuilder("Selecione o carro que deseja excluir:\n");
        for (int i = 0; i < carros.size(); i++) {
            Carro carro = carros.get(i);
            listaCarros.append(i + 1).append(" - ") // transforma a lista em 1 2 3
                    .append("Modelo: ").append(carro.getModelo()).append(" | ").append("Ano: ").append(carro.getAnoDeFabricacao()).append(" | ").append("Cor: ").append(carro.getCor()).append("\n");
        }

        String escolha = JOptionPane.showInputDialog(listaCarros + "Digite o número do carro para excluir");
        // validação da entrada
        if (escolha == null) {
            mensagem("Exclusão cancelda");
            return;
        }

        try {
            int indice = Integer.parseInt(escolha) - 1; // ajusta para o indice da lista em 0 1 2 originais

            if (indice >= 0 && indice < carros.size()) {
                Carro removido = carros.remove(indice);
                mensagem("Carro removido com sucesso: " + removido.getModelo() + " " + removido.getAnoDeFabricacao());

            } else {
                mensagem("Erro: Número inválido!");
            }
        } catch (NumberFormatException e) {
            mensagem("Erro: Insira um número válido");
        }
    }

    public void localizarModelo(ArrayList<Carro> carros) {
        if (carros.isEmpty()) {
            mensagem("Nenhum carro cadastrado!");
            return;
        }

        String modeloCarro = lerString("Digite o modelo do carro para ver os dados:");
        for (Carro c : carros) {
            if (modeloCarro.equalsIgnoreCase(c.getModelo())) {
                mensagem(c.toString());
                return;
            }
        }
        mensagem("Modelo não encontrado na base de dados!");
    }

    public void carroNovoAntigo(ArrayList<Carro> carros) {
        if (carros.isEmpty()) {
            mensagem("Nenhum carro cadastrado!");
            return;
        }

        int decidir = lerInt("Selecione a opção para localizar o carro:\n\n1 - Mostra os carros mais novos\n2 - Mostra os carros mais velhos");

        int anoMaisNovo = Integer.MIN_VALUE;
        int anoMaisVelho = Integer.MAX_VALUE;

        for (Carro carro : carros) {
            if (carro.getAnoDeFabricacao() > anoMaisNovo) {
                anoMaisNovo = carro.getAnoDeFabricacao();
            }
            if (carro.getAnoDeFabricacao() < anoMaisVelho) {
                anoMaisVelho = carro.getAnoDeFabricacao();
            }
        }

        StringBuilder lista = new StringBuilder();
        if (decidir == 1) {
            lista.append("Carro mais novo em estoque:\n\n");
            for (Carro carro : carros) {
                if (carro.getAnoDeFabricacao() == anoMaisNovo) {
                    lista.append(mostrarLista(carro));
                }
            }
        } else if (decidir == 2) {
            lista.append("Carros mais velho em estoque:\n\n");
            for (Carro carro : carros) {
                if (carro.getAnoDeFabricacao() == anoMaisVelho) {
                    lista.append(mostrarLista(carro));
                }
            }
        } else {
            ExibirMensagens.mensagem("Opção inválida! Escolha 1 ou 2");
            return;
        }

        ExibirMensagens.mensagem(lista.toString());

    }

    private String mostrarLista(Carro carro) {
        return "Modelo: " + carro.getModelo() + "\n" + "Marca: " + carro.getMarca() + "\n" + "Placa: " + carro.getPlaca() + "\n" + "Cor: " + carro.getCor() + "\n" + "Ano: " + carro.getAnoDeFabricacao() + "\n" + "Valor: R$ " + String.format("%.2f", carro.getValor()) + "\n\n";
    }

    public void carroBaratoCaro(ArrayList<Carro> carros) {
        if (carros.isEmpty()) {
            mensagem("Nenhum carro cadastrado!");
            return;
        }

        int decidir = lerInt("Selecione a opção para localizar o carro:\n\n1 - Mostrar o carro mais barato\n2 - Mostrar o carro mais caro");

        double carroMaisBarato = Double.MAX_VALUE;
        double carroMaisCaro = Double.MIN_VALUE;

        for (Carro carro : carros) {
            if (carro.getValor() < carroMaisBarato) {
                carroMaisBarato = carro.getValor();
            }
            if (carro.getValor() > carroMaisCaro) {
                carroMaisCaro = carro.getValor();
            }
        }

        StringBuilder lista = new StringBuilder();
        if (decidir == 1) {
            lista.append("Carro mais barato em estoque:\n\n");
            for (Carro carro : carros) {
                if (carro.getValor() == carroMaisBarato) {
                    lista.append(mostrarLista(carro));
                }
            }
        } else if (decidir == 2) {
            lista.append("Carro mais caro em estoque:\n\n");
            for (Carro carro : carros) {
                if (carro.getValor() == carroMaisCaro) {
                    lista.append(mostrarLista(carro));
                }
            }
        } else {
            ExibirMensagens.mensagem("Opção inválida! Escolha 1 ou 2");
            return;
        }

        ExibirMensagens.mensagem(lista.toString());
    }
}
