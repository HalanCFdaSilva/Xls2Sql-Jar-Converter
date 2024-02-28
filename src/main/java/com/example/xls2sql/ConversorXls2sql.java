package com.example.xls2sql;

import com.example.xls2sql.coletor.ColetorUsuario;
import com.example.xls2sql.coletor.domain.InfoUsuario;
import com.example.xls2sql.sql.DadosSql;
import com.example.xls2sql.sql.escritor.EscritorSql;
import com.example.xls2sql.xls.LeitorXls;

import java.io.IOException;

/**Classe que faz a interface de usuário juntando as classes para criar o programa.*/
public class ConversorXls2sql {

    /**Método principal que agrega os diversos métodos internos e gera o programa.
     * @throws IOException retorna uma IOException se não conseguir achar o endereço aonde deve salvar o arquivo sql.*/
    public static void start() throws IOException {

        InfoUsuario infoUsuario = ConversorXls2sql.coletaDadosUsuario();

        DadosSql dadosSql = ConversorXls2sql.fazerLeituraXls(infoUsuario.getEnderecoXls());

        ConversorXls2sql.escreverTextoSql(dadosSql,infoUsuario);

        ConversorXls2sql.abrirPastaLocalArquivoSql(infoUsuario.getEnderecoSql(),
                infoUsuario.getNomeTabela());
    }

    /**Método interno que inicia o programa chamando a classe ColetorUsuário que pega os dados do usuário.
     * @see ColetorUsuario
     * @see InfoUsuario
     * @return Retorna a classe infoUsuário, a qual retêm todas as informações ditas pelo usuário.*/
    private static InfoUsuario coletaDadosUsuario() {
        ColetorUsuario coletorUsuario = new ColetorUsuario();
        coletorUsuario.coletar();
        return coletorUsuario.getInfoUsuario();
    }

    /**Método que finaliza o programa abrindo o explorador de arquivos na pasta em que foi salvo o arquivo sql,
     * além de já deixar o mesmo selecionado.
     * @see Runtime*/
    private static void abrirPastaLocalArquivoSql(String enderecoGuardarSql, String nomeTabela) throws IOException {
        Runtime.getRuntime().exec("explorer /select, " +
                enderecoGuardarSql + nomeTabela + ".sql");
    }

    /**Método que chama a classe LeitorXls, faz a leitura do arquivo xls/xlsx e passa os dados a serem inseridos
     * no arquivo sql para uma instância da classe DadosSql.
     * @param enderecoXls string com o endereço do arquivo xls/xlsx.
     * @return retorna a instância da classe DadosSql criada na LeitorXls.
     * @see LeitorXls*/
    private static DadosSql fazerLeituraXls(String enderecoXls) throws IOException {
        System.out.println("Fazendo a leitura do arquivo xls");
        LeitorXls leitorXls = new LeitorXls();
        DadosSql dadosSql = leitorXls.ler(enderecoXls);
        System.out.println("Arquivo lido com sucesso");

        return dadosSql;
    }

    /**Método que chama a classe EscritorSql e faz a escrita do arquivo sql.
     * @param dadosSql classe que guarda todos os dados que estavam no arquivo xls/xlsx e que devem ser postos no sql.
     * @param infoUsuario classe que guarda todos os dados que o usúario passou.
     * @see EscritorSql*/
    private static void escreverTextoSql(DadosSql dadosSql, InfoUsuario infoUsuario) throws IOException {
        System.out.println("Escrevendo Texto Sql");
        EscritorSql escritorSql = new EscritorSql(dadosSql, infoUsuario);
        escritorSql.escreverBancoDados();
        System.out.println("Texto escrito com sucesso");
    }
}
