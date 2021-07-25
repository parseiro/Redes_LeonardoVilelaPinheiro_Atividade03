package com.vilelapinheiro.network;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

class Pessoa implements Serializable {
    private String nome;
    private int idade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
}

public class PessoaSendReceive {
    private static Janela2 janela;
    public static AtomicBoolean done = new AtomicBoolean();


    public static void main(String... args) {
        EventQueue.invokeLater(() -> {
            janela = new Janela2();
            janela.setVisible(true);
        });

        Runnable server = () -> {
            try (var s = new ServerSocket(8189)) {
                System.out.println("SERVER: Escutando na porta 8189");
                while (!done.get()) {
                    try (var incoming = s.accept();
                         var entrada = new ObjectInputStream(incoming.getInputStream());
                         var saida = new PrintWriter(new OutputStreamWriter(incoming.getOutputStream(), StandardCharsets.UTF_8), true)) {

                        System.out.println("SERVER: aguardando input");

                        Pessoa p;

                        while (true) {
                            p = (Pessoa) entrada.readObject();

                            System.out.println("SERVER: Recebi Nome: " + p.getNome() + ", idade: " + p.getIdade());

                            saida.println("Dados recebidos corretamente!");
                        }

                    } catch (EOFException e) {

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        };

        new Thread(server).start();

    }
}

