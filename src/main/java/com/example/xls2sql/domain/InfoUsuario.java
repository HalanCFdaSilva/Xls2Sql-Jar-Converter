package com.example.xls2sql.domain;

import com.example.xls2sql.coletor.ColetorUsuarioService;

public class InfoUsuario {


    private String enderecoSql;
    private String enderecoXls;
    private String nomeBancoDados;
    private String nomeTabela;

    public String getEnderecoXls() {
        return enderecoXls;
    }

    public void setEnderecoXls(String enderecoXls) {
        this.enderecoXls = enderecoXls;
    }

    public String getNomeBancoDados() {
        return nomeBancoDados;
    }

    public void setNomeBancoDados(String nomeBancoDados) {
        this.nomeBancoDados = nomeBancoDados;
    }

    public String getNomeTabela() {
        return nomeTabela;
    }

    public void setNomeTabela(String nomeTabela) {
        this.nomeTabela = nomeTabela;
    }

    public String getEnderecoSql() {
        return enderecoSql ;
    }

    public void setEnderecoSql(String enderecoSql) {
        this.enderecoSql = enderecoSql + "\\";
    }
}
