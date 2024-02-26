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

    /**<p>Retorna a variável enderecoXls</p>*/
    public String getEnderecoXls() {
        return enderecoXls;
    }

    /**<p> Modifica a variável Endereçoxls</p>*/
    public void setEnderecoXls(String enderecoXls) {
        this.enderecoXls = enderecoXls;
    }

    /**<p>Retorna a variável nomeBancoDados</p>*/
    public String getNomeBancoDados() {
        return nomeBancoDados;
    }

    /**<p> Modifica a variável nomeBancoDados</p>*/
    public void setNomeBancoDados(String nomeBancoDados) {
        this.nomeBancoDados = nomeBancoDados;
    }
    /**<p>Retorna a variável nomeTabela</p>*/
    public String getNomeTabela() {
        return nomeTabela;
    }

    /**<p> Modifica a variável nomeTabela</p>*/
    public void setNomeTabela(String nomeTabela) {
        this.nomeTabela = nomeTabela;
    }

    /**<p>Retorna a variável enderecoSql</p>*/
    public String getEnderecoSql() {
        return enderecoSql ;
    }

    /**<p> Modifica a variável EnderecoSql</p>*/
    public void setEnderecoSql(String enderecoSql) {
        this.enderecoSql = enderecoSql + "\\";
    }
}
