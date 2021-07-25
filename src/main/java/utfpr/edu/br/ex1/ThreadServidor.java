/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.edu.br.ex1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class ThreadServidor implements Runnable{

    private Socket conexao;
    private DataInputStream entrada;
    private DataOutputStream saida;
    
    public ThreadServidor(Socket c){
        this.conexao = c;
    }
    
    @Override
    public void run() {
        try {
            // executar o servico
            entrada = new DataInputStream(conexao.getInputStream());
            String pedidoCliente = entrada.readUTF();
            String respostaCliente = verificarOcorrencia(pedidoCliente);
            saida = new DataOutputStream(conexao.getOutputStream());
            saida.writeUTF(respostaCliente);
            
            // finalizar conexao
            conexao.close();
        } catch (IOException ex) {
            Logger.getLogger(ThreadServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String verificarOcorrencia(String chave) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\Users\\User\\Documents\\dados.txt")));
        String linha;
        while ((linha = reader.readLine()) != null) {
            if (linha.contains(chave)) {
                return "tem relação";
            }
        }
        return "não tem relação";
    }
    
}
