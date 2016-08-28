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
    ArrayList<Integer> valores;
    String s = "";

    public Algoritmos() {
        disco = new Disco();
        valores = new ArrayList<>();
    }

    public String lerValores() {
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

            for (int valor : valores) {
                s += String.valueOf(valor) + "\n";
            }

        } catch (IOException ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return s;
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
        //Collections.sort(valores);
        int setoresPercorridos = 0;
        int posicaoAtual = 0;
        int n = disco.getPosicaoBraco();
        int setorMaisProximo;
        int s = valores.get(0);

        if (n > s) {
            setorMaisProximo = n - s;
        } else {
            setorMaisProximo = s - n;
        }

        while (valores.size() > 0) {
            for (int i = 0; i < valores.size(); i++) {
                if (n > valores.get(i)) {
                    if ((n - valores.get(i)) < setorMaisProximo) {
                        setorMaisProximo = n - valores.get(i);
                        posicaoAtual = valores.get(i);
                    }
                } else if (valores.get(i) > n) {
                    if ((valores.get(i) - n) < setorMaisProximo) {
                        setorMaisProximo = valores.get(i) - n;
                        posicaoAtual = valores.get(i);
                    }
                }
            }
            setoresPercorridos = setoresPercorridos + setorMaisProximo;
            n = posicaoAtual;
            int x = valores.indexOf(posicaoAtual);
            valores.remove(x);
            Collections.sort(valores);
            if (valores.size() > 1) {
                setorMaisProximo = valores.get(valores.size() - 1);
            } else if (valores.size() == 1) {
                setorMaisProximo = valores.get(0);
            }
        }
        JOptionPane.showMessageDialog(null, "Quantidade de setores percorridos com SSF: " + setoresPercorridos);
        /*for (int i = 0; i < valores.size(); i++) {
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
        JOptionPane.showMessageDialog(null, "Quantidade de setores percorridos com SSF: " + setoresPercorridos);*/
    }

    public void executarElevator() {
        int setoresPercorridos = 0;
        ArrayList<Integer> menores = new ArrayList<>();
        ArrayList<Integer> maiores = new ArrayList<>();
        
        Collections.sort(menores);
        Collections.sort(maiores);
        
        int posicaoInicial = valores.get(0);
        
        for(int i = 0; i < valores.size(); i++){
            if(valores.get(i) > posicaoInicial){
                maiores.add(valores.get(i));
            }else{
                menores.add(valores.get(i));
            }
        }
        
        if(posicaoInicial > disco.getPosicaoBraco()){
            for(int i = 0; i < maiores.size(); i++){
                for(int j = disco.getPosicaoBraco(); j < maiores.get(i); j++){
                    setoresPercorridos++;
                }
                disco.setPosicaoBraco(maiores.get(i));
            }
            for(int i = 0; i < menores.size(); i++){
                for(int j = disco.getPosicaoBraco(); j >= menores.get(i); j--){
                    setoresPercorridos++;
                }
                disco.setPosicaoBraco(menores.get(i));
            }
        }else{
            Collections.reverse(menores);
            for(int i = 0; i < menores.size(); i++){
                for(int j = disco.getPosicaoBraco(); j >= menores.get(i); j--){
                    setoresPercorridos++;
                }
                disco.setPosicaoBraco(menores.get(i));
            }
            for(int i = 0; i < maiores.size(); i++){
                for(int j = disco.getPosicaoBraco(); j < maiores.get(i); j++){
                    setoresPercorridos++;
                }
                disco.setPosicaoBraco(maiores.get(i));
            }
        }
        JOptionPane.showMessageDialog(null,"Quantidade de setores percorridos com Elevator: " + setoresPercorridos);
    }

    /*public static void main(String[] args) {
        Algoritmos algoritmos = new Algoritmos();
        algoritmos.lerValores();
        algoritmos.executarElevator(false);
    }*/
}
