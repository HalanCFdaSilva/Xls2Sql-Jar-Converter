package com.example.xls2sql.sql.domain;

import com.example.xls2sql.sql.domain.CelulaLinhaSql;
import com.example.xls2sql.sql.domain.Coluna;
import com.example.xls2sql.sql.exceptions.CelulaComElementosNaoConversiveisException;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

/**Classe que faz testes automatizados da classe CelulaLinhaSql
 * @author Halan Silva
 * @see CelulaLinhaSql*/
public class CelulaLinhaSqlTest {

    /**Variável utilizada em alguns testes para verificar inserções na variável celulaLinhaSql. */
    Coluna coluna;
    /**Variável alvo dos testes e que serve par ver se a classe está funcionando corretamente*/
    CelulaLinhaSql celulaLinhaSql;

    /**Método chamado antes de cada teste o qual faz inserções nas variáveis para facilitar os testes.*/
    @BeforeEach
    public void antesCadaTeste(){
        this.coluna = new Coluna();
        this.coluna.adicionar("ingrediente[varchar(50)]",2);
        this.celulaLinhaSql = new CelulaLinhaSql(1,1);
        ArrayList<String> celula = new ArrayList<>();
        celula.add("arroz");
        celulaLinhaSql.adicionarCelula(celula,coluna.getTipoDados());

    }

    /**Método utilizado para verificar se a classe está guardando os parâmetros de inicialização corretamente.*/
    @Test
    public  void guardaPosicaoLinhaColunaAoCriarElementoSql(){
        Assertions.assertEquals(1, celulaLinhaSql.getLinha());
        Assertions.assertEquals(1,celulaLinhaSql.getColuna());
    }

    /**Método que verifica se a variável celulaLinhaSql está guardando a string na variável celula corretamente ao utilizar
     * o método adicionar.*/
    @Test
    public void guardaCelulaAoUsarAdicionar(){

        Assertions.assertEquals("[arroz]", celulaLinhaSql.getCelula().toString());

    }

    /**Método que verifica se a Classe CelulaLinhaSql modifica a variável celula corretamente ao utilizar
     *  o método setCelula. */
    @Test
    public void modificaCelulaAoUsarSetCelula(){
        Assertions.assertEquals("[arroz]", celulaLinhaSql.getCelula().toString());
        ArrayList<String> celula = new ArrayList<>();
        celula.add("feijão");
        celulaLinhaSql.setCelula(celula);
        Assertions.assertEquals(celula.toString(), celulaLinhaSql.getCelula().toString());
    }

    /**Método que verifica se a classe CelulaLinhaSql está guardando a variável TipoDados corretamente ao utilizar
     *  o método adicionar.*/
    @Test
    public void elementoSqlGuardaTipoDadosDaColuna(){
        Assertions.assertEquals(coluna.getTipoDados(), celulaLinhaSql.getTipoDados());
    }

    /** Método que verifica se a classe CelulaLinhaSql está dando a exception
     * {@link CelulaComElementosNaoConversiveisException CelulaComElementosNaoConversiveisException}
     * quando o testo a inserir fora das regras do TipoColunaSqlString.
     * @see com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSQLString*/
    @Test
    public void daCelulaComElementosNaoConversiveisExceptionQuandoTextoDoTipoStringAAdicionarForaDasRegrasDoTipoDados(){
        String textoMaiorQuePostoNoTipoDados = "M";
        for (int i = 0; i <= coluna.getTipoDados().numeroElementos() + 1; i++){
            textoMaiorQuePostoNoTipoDados += "M";
        }
        ArrayList<String> celula = new ArrayList<>();
        celula.add(textoMaiorQuePostoNoTipoDados);

        Assertions.assertThrows(CelulaComElementosNaoConversiveisException.class,() -> celulaLinhaSql.adicionarCelula(celula,coluna.getTipoDados()));
        Assertions.assertThrows(CelulaComElementosNaoConversiveisException.class,() -> celulaLinhaSql.setCelula(celula));
    }

    /**Método que verifica se a classe CelulaLinhaSql guarda arraylist com os dados escrito null ou um espaço simples
     * para TipoColunaSqlString.
     * @see com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSQLString*/
    @Test
    public void quardaCelulacomTextoNullOuEspacoSimplesIgnorandoAsRegrasDoTipoDadosString() {

        coluna.adicionar("numero[char]", 2);
        ArrayList<String> celula = new ArrayList<>();
        celula.add("null");
        celulaLinhaSql.adicionarCelula(celula, coluna.getTipoDados());
        Assertions.assertEquals(celula.toString(), celulaLinhaSql.getCelula().toString());

        celula.clear();
        celula.add(" ");
        celulaLinhaSql.setCelula(celula);
        Assertions.assertEquals(celula.toString(), celulaLinhaSql.getCelula().toString());
    }

    /** Método que verifica se a classe CelulaLinhaSql está dando a exception
     * {@link CelulaComElementosNaoConversiveisException CelulaComElementosNaoConversiveisException}
     * quando o testo a inserir fora das regras do TipoColunaSqlNumeric.
     * @see com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSqlNumeric*/
    @Test
    public void daCelulaComElementosNaoConversiveisExceptionQuandoTextoDoTipoStringAAdicionarForaDasRegrasDoTipoDadosNumeric(){

        coluna.adicionar("numero[tinyint]",2);
        ArrayList<String> celula = new ArrayList<>();
        celula.add("128");
        Assertions.assertThrows(CelulaComElementosNaoConversiveisException.class,() -> celulaLinhaSql.adicionarCelula(celula,coluna.getTipoDados()));
        Assertions.assertThrows(CelulaComElementosNaoConversiveisException.class,() -> celulaLinhaSql.setCelula(celula));
    }

    /**Método que verifica se a classe CelulaLinhaSql guarda arraylist com os dados escrito null ou um espaço
     * simples para TipoColunaSqlNumeric.
     * @see com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSqlNumeric*/
    @Test
    public void quardaCelulacomTextoNullOuEspacoSimplesIgnorandoAsRegrasDoTipoDadosNumeric() {

        coluna.adicionar("numero[tinyInt]", 2);
        ArrayList<String> celula = new ArrayList<>();
        celula.add("null");
        celulaLinhaSql.adicionarCelula(celula, coluna.getTipoDados());
        Assertions.assertEquals(celula.toString(), celulaLinhaSql.getCelula().toString());

        celula.clear();
        celula.add(" ");
        celulaLinhaSql.setCelula(celula);
        Assertions.assertEquals(celula.toString(), celulaLinhaSql.getCelula().toString());
    }

    /** Método que verifica se a classe CelulaLinhaSql está dando a exception
     * {@link CelulaComElementosNaoConversiveisException CelulaComElementosNaoConversiveisException}
     * quando o testo a inserir fora das regras do TipoColunaSqlDateTime.
     * @see com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSqlDateTime*/
    @Test
    public void daCelulaComElementosNaoConversiveisExceptionQuandoTextoDoTipoStringAAdicionarForaDasRegrasDoTipoDadosDateTime(){

        coluna.adicionar("numero[Year]",2);
        ArrayList<String> celula = new ArrayList<>();
        celula.add("1000");
        Assertions.assertThrows(CelulaComElementosNaoConversiveisException.class,() -> celulaLinhaSql.adicionarCelula(celula,coluna.getTipoDados()));
        Assertions.assertThrows(CelulaComElementosNaoConversiveisException.class,() -> celulaLinhaSql.setCelula(celula));
    }

    /**Método que verifica se a classe CelulaLinhaSql guarda arraylist com os dados escrito null ou um espaço simples
     * para TipoColunaSqlDateTime.
     * @see com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSqlDateTime*/
    @Test
    public void quardaCelulacomTextoNullOuEspacoSimplesIgnorandoAsRegrasDoTipoDadosDateTime() {

        coluna.adicionar("numero[Year]", 2);
        ArrayList<String> celula = new ArrayList<>();
        celula.add("null");
        celulaLinhaSql.adicionarCelula(celula, coluna.getTipoDados());
        Assertions.assertEquals(celula.toString(), celulaLinhaSql.getCelula().toString());

        celula.clear();
        celula.add(" ");
        celulaLinhaSql.setCelula(celula);
        Assertions.assertEquals(celula.toString(), celulaLinhaSql.getCelula().toString());
    }

    /**Método que verifica se o método isNull retorna false quando a variável célula não está guardando uma
     * String com o texto null("null") ou um espaço simples(" "). */
    @Test
    public void retornaFalseQuandoVariavelCelulanaoENullOuEspacoSimples(){
        Assertions.assertFalse(celulaLinhaSql.isNull());
    }

    /**Método que verifica se o método isNull retorna true quando a variável célula não está guardando uma
     * String com o texto null("null") ou um espaço simples(" "). */
    @Test
    public void retornatrueQuandoVariavelCelulaENullOuEspacoSimples(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("null");
        celulaLinhaSql.setCelula(arrayList);
        Assertions.assertTrue(celulaLinhaSql.isNull());
        arrayList.clear();
        arrayList.add(" ");
        celulaLinhaSql.setCelula(arrayList);
        Assertions.assertTrue(celulaLinhaSql.isNull());
    }



}
