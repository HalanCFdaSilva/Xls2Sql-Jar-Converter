package com.example.xls2sql.coletor.domain;
/**<p>Classe utilizada para armazenar todos os dados enviados pelo usuário.</p>
 * @author Halan Silva*/
public class InfoUsuario {


    /**<p>Variável utilizada para armazenar o endereço da pasta em que será salvo o arquivo sql na classe
     * {@link com.example.xls2sql.sql.escritor.EscritorSql EscritorSql}.</p>*/
    private String enderecoSql;
    /**<p>Variável utilizada para armazenar o endereço do arquivo xls/xlsx que será utilizado pela classe
     * {@link com.example.xls2sql.xls.LeitorXls LeitorXls}.</p>*/
    private String enderecoXls;

    /**<p> Variável utilizada para armazenar o nome do banco de dados em que será colocado a tabela.</p>
     * @see com.example.xls2sql.sql.escritor.EscritorSql */
    private String nomeBancoDados;

    /**<p> Variável utilizada para armazenar o nome da tabela que será utilizada na hora de escrever os dados a armazenar no sql.</p>
     * @see com.example.xls2sql.sql.escritor.EscritorSql*/
    private String nomeTabela;

    /** Retorna a variável enderecoXls.
     * @return Retorna o endereço do arquivo xls/xlsx onde estão os dados a ser salvo na tabela sql.*/
    public String getEnderecoXls() {
        return enderecoXls;
    }

    /** Modifica a variável Endereçoxls.
     * @param enderecoXls String com o endereço do arquivo xls/xlsx onde estão os dados a ser salvo na tabela sql.*/
    public void setEnderecoXls(String enderecoXls) {
        this.enderecoXls = enderecoXls;
    }

    /** Retorna a variável nomeBancoDados.
     * @return Retorna o nome do banco de dados do sql onde deve ser salvo os dados.*/
    public String getNomeBancoDados() {
        return nomeBancoDados;
    }

    /** Modifica a variável nomeBancoDados.
     * @param nomeBancoDados String com o nome do banco de dados do sql onde deve ser salvo os dados.*/
    public void setNomeBancoDados(String nomeBancoDados) {
        this.nomeBancoDados = nomeBancoDados;
    }

    /** Retorna a variável nomeTabela.
     * @return Retorna o nome da tabela do sql onde deve ser salvo os dados.*/
    public String getNomeTabela() {
        return nomeTabela;
    }

    /** Modifica a variável nomeTabela.
     * @param nomeTabela string com o nome da tabela sql onde será inserido os dados.*/
    public void setNomeTabela(String nomeTabela) {
        this.nomeTabela = nomeTabela;
    }

    /**Retorna a variável enderecoSql.
     * @return retorna o endereço da pasta onde deve ser salvo o arquivoSql.*/
    public String getEnderecoSql() {
        return enderecoSql ;
    }

    /**<p> Modifica a variável EnderecoSql</p>
     * @param enderecoSql String com o endereço da dasta onde deve ser salvo o arquivoSql.*/
    public void setEnderecoSql(String enderecoSql) {
        this.enderecoSql = enderecoSql + "\\";
    }
}
