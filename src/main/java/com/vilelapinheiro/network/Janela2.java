/*
 * Created by JFormDesigner on Sun Jul 18 00:24:43 BRT 2021
 */

package com.vilelapinheiro.network;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


/**
 * @author unknown
 */
public class Janela2 extends JFrame {
    public Janela2() {
        initComponents();
    }

    private void button1ActionPerformed(ActionEvent e) {
        try {
            var pessoa = new Pessoa(this.nome.getText(), Integer.parseInt(this.idade.getText()));
            //this.resposta.setText("Oi, o nome é: " + pessoa.getNome() + " e a idade é: " + pessoa.getIdade());

            Runnable client = () -> {
                try (SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8189));
                     var entrada = new Scanner(channel, StandardCharsets.UTF_8);
                     var saida = new ObjectOutputStream(Channels.newOutputStream(channel))) {

//                    var pessoa = new Pessoa("John Lennon", 72);

                    System.out.println("CLIENTE: Enviando Nome: " + pessoa.getNome() + ", idade: " + pessoa.getIdade());
                    saida.writeObject(pessoa);
                    saida.flush();

                    String leitura = entrada.nextLine();
                    System.out.println("CLIENT: recebi do servidor: " + leitura);
                    this.resposta.setText("Recebeu do servidor:\n" + leitura);

                } catch (IOException error) {
                    error.printStackTrace();
                }
            };

            new Thread(client).start();

        } catch (NumberFormatException error) {

        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        label1 = new JLabel();
        nome = new JTextField();
        label2 = new JLabel();
        idade = new JTextField();
        label3 = new JLabel();
        scrollPane1 = new JScrollPane();
        resposta = new JTextArea();
        textField1 = new JTextField();
        button1 = new JButton();
        slider1 = new JSlider();

        //======== this ========
        setType(Window.Type.UTILITY);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- label1 ----
        label1.setText("Nome");
        contentPane.add(label1, new GridBagConstraints(0, 0, 6, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(nome, new GridBagConstraints(0, 1, 14, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- label2 ----
        label2.setText("Idade");
        contentPane.add(label2, new GridBagConstraints(0, 2, 5, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- idade ----
        idade.setColumns(2);
        contentPane.add(idade, new GridBagConstraints(0, 3, 14, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- label3 ----
        label3.setText("Retorno do servidor");
        contentPane.add(label3, new GridBagConstraints(0, 4, 7, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //======== scrollPane1 ========
        {

            //---- resposta ----
            resposta.setEditable(false);
            resposta.setRows(13);
            resposta.setBackground(new Color(255, 255, 204));
            scrollPane1.setViewportView(resposta);
        }
        contentPane.add(scrollPane1, new GridBagConstraints(0, 5, 14, 8, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));
        contentPane.add(textField1, new GridBagConstraints(1, 13, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- button1 ----
        button1.setText("Enviar");
        button1.addActionListener(e -> button1ActionPerformed(e));
        contentPane.add(button1, new GridBagConstraints(9, 13, 5, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));
        contentPane.add(slider1, new GridBagConstraints(1, 14, 9, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel label1;
    private JTextField nome;
    private JLabel label2;
    private JTextField idade;
    private JLabel label3;
    private JScrollPane scrollPane1;
    private JTextArea resposta;
    private JTextField textField1;
    private JButton button1;
    private JSlider slider1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
