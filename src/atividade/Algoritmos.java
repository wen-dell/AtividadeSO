package atividade;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

public class Algoritmos {

    private Disco disco;
    private ArrayList<Integer> valores;

    public Algoritmos() {
        disco = new Disco();
        valores = new ArrayList<>();
    }

    private void lerValores() {
        try {
            String diretorioCorrente = new File(".").getCanonicalPath() + "//src//atividade//arquivo.txt";
            File arquivo = new File(diretorioCorrente);
            FileReader leitor = new FileReader(arquivo);
            BufferedReader buffer = new BufferedReader(leitor);

            String linha;

            while ((linha = buffer.readLine()) != null) {
                valores.add(Integer.parseInt(linha));
            }

            disco.setQuantidadeSetores(valores.remove(0));
            disco.setPosicaoBraco(valores.remove(0));

        } catch (IOException ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
    }

    public void executarFCFS() {
        int setoresPercorridos = 0;
        for (int i = 0; i < valores.size(); i++) {
            if (valores.get(i) <= disco.getPosicaoBraco()) {
                for (int j = disco.getPosicaoBraco(); j > valores.get(i); j--) {
                    setoresPercorridos++;
                }
                disco.setPosicaoBraco(valores.get(i));
            } else {
                for (int j = disco.getPosicaoBraco(); j < valores.get(i); j++) {
                    setoresPercorridos++;
                }
                disco.setPosicaoBraco(valores.get(i));
            }
        }
        JOptionPane.showMessageDialog(null, "Quantidade de setores percorridos com FIFO: " + setoresPercorridos);
    }

    public void executarSSF() {
        Collections.sort(valores);
        int setoresPercorridos = 0;
        for (int i = 0; i < valores.size(); i++) {
            if (valores.get(i) <= disco.getPosicaoBraco()) {
                for (int j = disco.getPosicaoBraco(); j > valores.get(i); j--) {
                    setoresPercorridos++;
                }
                disco.setPosicaoBraco(valores.get(i));
            } else {
                for (int j = disco.getPosicaoBraco(); j < valores.get(i); j++) {
                    setoresPercorridos++;
                }
                disco.setPosicaoBraco(valores.get(i));
            }
        }
        JOptionPane.showMessageDialog(null, "Quantidade de setores percorridos com SSF: " + setoresPercorridos);
    }

    public void executarElevator(boolean sobe) {
        if (sobe) {
            Collections.reverse(valores);
            int setoresPercorridos = 0;
            for (int i = 0; i < valores.size(); i++) {
                if (valores.get(i) <= disco.getPosicaoBraco()) {
                    for (int j = disco.getPosicaoBraco(); j > valores.get(i); j--) {
                        setoresPercorridos++;
                    }
                    disco.setPosicaoBraco(valores.get(i));
                } else {
                    for (int j = disco.getPosicaoBraco(); j < valores.get(i); j++) {
                        setoresPercorridos++;
                    }
                    disco.setPosicaoBraco(valores.get(i));
                }
            }
            JOptionPane.showMessageDialog(null, "Quantidade de setores percorridos com Elevator: " + setoresPercorridos);
        } else {
            executarFCFS();
        }
    }

    public static void main(String[] args) {
        Algoritmos algoritmos = new Algoritmos();
        algoritmos.lerValores();
        algoritmos.executarElevator(false);
    }

}
