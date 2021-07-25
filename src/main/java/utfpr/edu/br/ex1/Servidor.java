/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.edu.br.ex1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author User
 */
public class Servidor {

//    private static DatagramSocket socket;
//    private static Socket conexao;
//    private static DataInputStream entrada;
//    private static DataOutputStream saida;
    private static ExecutorService es = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        try (var socket = new DatagramSocket(2000)) {
//            Servidor.socket = socket;

            System.out.println("SERVIDOR: aceitando conexões UDP na porta 2000");

            while (true) {
                var DpReceive = Util.receiveUdpPacket(socket, 0);

                // regra de negocio
                es.submit(new ThreadServidor(socket, DpReceive));
//                new Thread(new ThreadServidor(socket, DpReceive)).start();
            }
        } catch(IOException ex){
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }



}
