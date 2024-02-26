package com.example.xls2sql;

import com.example.xls2sql.coletor.ColetorUsuario;
import com.example.xls2sql.coletor.domain.InfoUsuario;
import com.example.xls2sql.sql.DadosSql;
import com.example.xls2sql.sql.escritor.EscritorSql;
import com.example.xls2sql.xls.LeitorXls;

import java.io.IOException;


public class ConversorXls2sql {
    public static void start() throws IOException {

        ColetorUsuario coletorUsuario = ConversorXls2sql.coletaDadosUsuario();

        DadosSql dadosSql = ConversorXls2sql.fazerLeituraXls(coletorUsuario.getInfoUsuario().getEnderecoXls());

        ConversorXls2sql.escreverTextoSql(dadosSql,coletorUsuario.getInfoUsuario());

        ConversorXls2sql.abrirPastaLocalArquivoSql(coletorUsuario.getInfoUsuario().getEnderecoSql(),
                coletorUsuario.getInfoUsuario().getNomeTabela());
    }

    private static ColetorUsuario coletaDadosUsuario() {
        ColetorUsuario coletorUsuario = new ColetorUsuario();
        coletorUsuario.coletar();
        return coletorUsuario;
    }

    private static void abrirPastaLocalArquivoSql(String enderecoGuardarSql, String nomeTabela) throws IOException {
        Runtime.getRuntime().exec("explorer /select, " +
                enderecoGuardarSql + nomeTabela + ".sql");
    }

    private static DadosSql fazerLeituraXls(String enderecoXls) throws IOException {
        System.out.println("Fazendo a leitura do arquivo xls");
        LeitorXls leitorXls = new LeitorXls();
        DadosSql dadosSql = leitorXls.ler(enderecoXls);
        System.out.println("Arquivo lido com sucesso");

        return dadosSql;
    }

    private static void escreverTextoSql(DadosSql dadosSql, InfoUsuario infoUsuario) throws IOException {
        System.out.println("Escrevendo Texto Sql");
        EscritorSql escritorSql = new EscritorSql(dadosSql, infoUsuario);
        escritorSql.escreverBancoDados();
        System.out.println("Texto escrito com sucesso");
    }
}
