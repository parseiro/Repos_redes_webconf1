/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.edu.br.ex1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author User
 */
public class ThreadServidor implements Runnable {

    private DatagramSocket serverSocket;
    private DatagramPacket pacoteRecebido;

    public ThreadServidor(DatagramSocket socket, DatagramPacket pacote) {
        this.serverSocket = socket;
        this.pacoteRecebido = pacote;
    }

    @Override
    public void run() {
        try {
            // executar o servico
            var entrada = Util.data(pacoteRecebido.getData());
            String pedidoCliente = entrada.toString();
            System.out.println("SERVIDOR: recebi '" + pedidoCliente + "'");

            String enviarAoCliente = "";
            if ("tchau".equalsIgnoreCase(pedidoCliente)) {
                System.out.println("SERVIDOR: cliente saindo!");
                enviarAoCliente = "Boa sorte na sua nova jornada!";
            } else {
                enviarAoCliente = verificarOcorrencia(pedidoCliente);
            }
            System.out.printf("SERVIDOR: vou enviar '%s'%n", enviarAoCliente);
            Util.sendUdpPacket(enviarAoCliente, pacoteRecebido.getAddress(), pacoteRecebido.getPort(), serverSocket);

        } catch (IOException ex) {
            Logger.getLogger(ThreadServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public static String verificarOcorrencia(String chave) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("dados.txt")));
        String linha;
        while ((linha = reader.readLine()) != null) {
            if (linha.contains(chave)) {
                return "tem relação";
            }
        }
        return "não tem relação";
    }


}
