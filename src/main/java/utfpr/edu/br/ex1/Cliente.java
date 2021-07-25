/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.edu.br.ex1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Cliente {

    private static Socket conexao;
    private static DataInputStream entrada;
    private static DataOutputStream saida;

    public static void main(String[] args) {
        try {
            while (true) {
                // conectar com servidor
                conexao = new Socket("localhost", 2000);

                // enviar e receber dados
                saida = new DataOutputStream(conexao.getOutputStream());
                String dados = "Cliente";
                saida.writeUTF(dados);
                entrada = new DataInputStream(conexao.getInputStream());
                String respostaServidor = entrada.readUTF();
                System.out.println("Resposta do Servidor:" + respostaServidor);

                // fechar conexao
                conexao.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
