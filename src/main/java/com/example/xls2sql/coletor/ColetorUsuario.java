package com.example.xls2sql.coletor;

import com.example.xls2sql.xls.AnalisadorEndereco;
import com.example.xls2sql.domain.InfoUsuario;

public class ColetorUsuario {

    private InfoUsuario infoUsuario;

    private ColetorUsuarioService coletorService;
    public ColetorUsuario() {
        this.infoUsuario = new InfoUsuario();
        coletorService = new ColetorUsuarioService();
    }

    public void coletar(){
        System.out.println("Digite o nome do banco de dados");
        infoUsuario.setNomeBancoDados(coletorService.pegarEscrita());


        System.out.println("Digite o endereco do arquivo xls");
        infoUsuario.setEnderecoXls(coletorService.pegarEscrita());
        while (!AnalisadorEndereco.verificarArquivoXls(infoUsuario.getEnderecoXls())){
            System.out.println("não foi possivel encontrar o endereco digite de novo");
            infoUsuario.setEnderecoXls(coletorService.pegarEscrita());
        }

        String nomeTabela = infoUsuario.getEnderecoXls();
        int indexInicio = nomeTabela.lastIndexOf("\\");
        int indexFinal = nomeTabela.indexOf(".xls");
        nomeTabela = nomeTabela.substring(indexInicio + 1,indexFinal);
        infoUsuario.setNomeTabela(nomeTabela);


        System.out.println("Digite o endereco em que quer salvar o arquivo SQL");
        infoUsuario.setEnderecoSql(coletorService.pegarEscrita());
       // fazer um checador para o sql


        coletorService.fecharScanner();


    }

    public InfoUsuario getInfoUsuario() {
        return infoUsuario;
    }
}
