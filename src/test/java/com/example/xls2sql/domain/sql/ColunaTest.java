package com.example.xls2sql.domain.sql;

import com.example.xls2sql.sql.domain.Coluna;
import com.example.xls2sql.sql.exceptions.TipoColunaSqlNaoEncontradoException;
import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSQLString;
import com.example.xls2sql.sql.exceptions.TextoColunaVaziaException;
import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSqlNumeric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**Classe que faz testes automatizados da classe Coluna.
 * @author Halan Silva
 * @see Coluna*/
public class ColunaTest {


    /**Variável utilizada para testar a classe Coluna.*/
    private Coluna coluna;

    /**Método utilizado antes de cada teste para iniciar a variável.*/
    @BeforeEach
    public void criarColuna(){
        this.coluna = new  Coluna();
    }

    /**Método utilizado para verificar se o método adicionar guarda a variável nome corretamente.*/
    @Test
    public void adicionarNomeNaColuna(){

        coluna.adicionar("Pratos[int]",1);
        Assertions.assertEquals("Pratos", coluna.getNome());
    }

    /**Método utilizado para verificar se o método adicionar está criando o tipoDados e guardando o tipoColunaSql
     * e guardando 0 quando não vem o numeroElementos na String.*/
    @Test
    public void adicionarTipoColunaSqlSemTamanhoDeElementosNaColuna(){

        coluna.adicionar("Pratos[int]",1);
        Assertions.assertEquals(TipoColunaSqlNumeric.INT, coluna.getTipoDados().tipoColunaSql());
        Assertions.assertEquals(0,coluna.getTipoDados().numeroElementos());
    }

    /**Método utilizado para verificar se o método adicionar está criando o tipoDados e guardando o tipoColunaSql
     * mesmo com a string toda em maiúsculo.*/
    @Test
    public void adicionarTipoColunaSqlComTipoTodoEmMaiusculoNaString(){

        coluna.adicionar("Pratos[INT]",1);
        Assertions.assertEquals(TipoColunaSqlNumeric.INT, coluna.getTipoDados().tipoColunaSql());
    }

    /**Método utilizado para verificar se o método adicionar está criando o tipoDados e guardando o tipoColunaSql
     * e o numeroElementos quando os dois vem na String.*/
    @Test
    public void adicionarTipoColunaSqlENumeroElementosNaColuna(){

        coluna.adicionar("Pratos[varchar(10)]",1);
        Assertions.assertEquals(TipoColunaSQLString.VARCHAR, coluna.getTipoDados().tipoColunaSql());
        Assertions.assertEquals(10, coluna.getTipoDados().numeroElementos());
    }

    /**Método utilizado para verificar se o método adicionar dá a exceção TextoColunaVaziaException quando a
     * string vier vazia ou somente com espaçamento.
     * @see TextoColunaVaziaException*/
    @Test
    public void darTextoColunaVaziaExceptionQuandoAStringVierVazia(){

        Assertions.assertThrows(TextoColunaVaziaException.class,() -> coluna.adicionar("",1));

        Assertions.assertThrows(TextoColunaVaziaException.class,() -> coluna.adicionar("   ",1));
    }

    /**Método utilizado para verificar se o método adicionar dá a exceção TipoColunaSqlNaoEncontradoException
     *  quando a string vier vazia.
     * @see TipoColunaSqlNaoEncontradoException*/
    @Test
    public void darTipoDadoSqlNaoEncontradoException(){
        Assertions.assertThrows(TipoColunaSqlNaoEncontradoException.class,() -> coluna.adicionar("casa",1));
    }
}
