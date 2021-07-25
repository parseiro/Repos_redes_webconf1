/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.edu.br.ex1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author User
 */
public class Cliente {

//    private static DatagramSocket conexao;
//    private static DataInputStream entrada;
//    private static DataOutputStream saida;

    public static void main(String[] args) throws UnknownHostException {
        // cria o objeto socket
        var ip = InetAddress.getLocalHost();
        try (var conexao = new DatagramSocket();
             var sc = new Scanner(System.in)) {
//            Cliente.conexao = conexao;

            while (true) {

                System.out.printf("Por favor digite uma string para enviar ou \"tchau\" para terminar:%n> ");

                String dados = sc.nextLine();

                System.out.printf("CLIENTE: enviando para o servidor '%s'%n", dados);
                Util.sendUdpPacket(dados, ip, 2000, conexao);

                try {
                    var DpReceive = Util.receiveUdpPacket(conexao, 1000);
                    var receivedFromServer = Util.data(DpReceive.getData());

                    System.out.printf("CLIENTE: recebi do servidor: '%s'%n", receivedFromServer);

                } catch (SocketTimeoutException e) {
                    System.out.printf("Demorou demais para receber resposta do servidor.%n");
                }

                if ("tchau".equalsIgnoreCase(dados)) {
                    break;
                }


                // enviar e receber dados
/*                entrada = new DataInputStream(conexao.getInputStream());
                String respostaServidor = entrada.readUTF();
                System.out.println("Resposta do Servidor:" + respostaServidor);*/

            }
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
