package com.example.xls2sql;

import com.example.xls2sql.coletor.ColetorUsuario;
import com.example.xls2sql.domain.sql.DadosSql;
import com.example.xls2sql.sql.escritor.EscritorSql;
import com.example.xls2sql.sql.exceptions.CelulaExcelComTamanhoMaiorQueOPermitidoColuna;
import com.example.xls2sql.xls.LeitorXls;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, CelulaExcelComTamanhoMaiorQueOPermitidoColuna {

        ColetorUsuario coletorUsuario = new ColetorUsuario();
        coletorUsuario.coletar();
        LeitorXls leitorXls = new LeitorXls();
        DadosSql dadosSql = leitorXls.ler(coletorUsuario.getInfoUsuario().getEnderecoXls());

        EscritorSql escritorSql = new EscritorSql(dadosSql, coletorUsuario.getInfoUsuario());
        escritorSql.escreverBancoDados();


        Runtime.getRuntime().exec("explorer /select, " +
           coletorUsuario.getInfoUsuario().getEnderecoSql() +
           coletorUsuario.getInfoUsuario().getNomeTabela() + ".sql");
    }
}
