package com.example.xls2sql.coletor;

import java.util.Scanner;

public class ColetorUsuarioService {

    private Scanner scanner;

    public ColetorUsuarioService() {
        this.scanner = new Scanner(System.in);
    }

    public String pegarEscrita(){
        String dadoColetado = scanner.next();
        return dadoColetado;
    }

    public void fecharScanner(){
        scanner.close();
    }
}
