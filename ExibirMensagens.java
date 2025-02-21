package p03_CadastroCarro;

import javax.swing.*;

public class ExibirMensagens {

    public static void mensagem(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public static String lerString(String msg) {
        String str = JOptionPane.showInputDialog(msg);
        if (str == null || str.trim().isEmpty() || str.length() < 2) {
            mensagem("Dados inválidos, verifique e tente novamente");
            return lerString(msg);
        } else {
            return str;
        }
    }

    public static int lerInt(String msg) {
        try {
            return Integer.parseInt(JOptionPane.showInputDialog(msg));
        } catch (NumberFormatException e) {
            mensagem("Entrada inválida, verifique e tente novamente");
        }
        return lerInt(msg);
    }
}

//"Cadastrar Carro", "Localizar por Modelo", "Listar por Ano", "Listar por Valor", "Sair"

