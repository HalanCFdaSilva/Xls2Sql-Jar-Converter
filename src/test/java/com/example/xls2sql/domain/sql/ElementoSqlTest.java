package com.example.xls2sql.domain.sql;

import com.example.xls2sql.sql.exceptions.CelulaComElementosNaoConversiveisException;
import com.example.xls2sql.sql.exceptions.CelulaExcelComTamanhoMaiorQueOPermitidoColunaException;
import org.junit.jupiter.api.*;

public class ElementoSqlTest {
    Coluna coluna;
    ElementoSql elementoSql;
    @BeforeEach
    public void antesCadaTeste(){
        this.coluna = new Coluna();
        this.coluna.adicionar("ingrediente[varchar(50)]",2);
        this.elementoSql = new ElementoSql(1);
        elementoSql.adicionarCelula("arroz",coluna);

    }

    @Test
    public  void guardaPosicaoLihaAoCriarElementoSql(){
        ElementoSql elementoSql = new ElementoSql(1);
        Assertions.assertEquals(1, elementoSql.getLinha());
    }


    @Test
    public void guardaCelulaAoUsarAdicionarCelula(){

        Assertions.assertEquals("arroz",elementoSql.getCelula());
    }
    @Test
    public void naoGuardaCelulaAoUsarAdicionarCelulaQuandoTipoDadosNãoDefinido(){

        ElementoSql elementoSql1 = new ElementoSql(2);
        elementoSql1.setCelula("arroz");
        Assertions.assertEquals(null,elementoSql1.getCelula());
    }

    @Test
    public void modificaCelulaAoUsarSetCelula(){
        Assertions.assertEquals("arroz",elementoSql.getCelula());
        elementoSql.setCelula("feijão");
        Assertions.assertEquals("feijão",elementoSql.getCelula());
    }

    @Test
    public void elementoSqlGuardaTipoDadosDaColuna(){
        Assertions.assertEquals(coluna.getTipo(), elementoSql.getTipoDados());
    }

    @Test
    public void daCelulaExcelComTamanhoMaiorQueOPermitidoColunaExceptionQuandoTextoAAdicionarMaiorQueGuardadoNoTipoDados(){
        String textoMaiorQuePostoNoTipoDados = "M";
        for (int i = 0; i <= coluna.getTipo().getNumeroElementos() + 1; i++){
            textoMaiorQuePostoNoTipoDados += "M";
        }

        String finalTextoMaiorQuePostoNoTipoDados = textoMaiorQuePostoNoTipoDados;
        Assertions.assertThrows(CelulaExcelComTamanhoMaiorQueOPermitidoColunaException.class,() ->elementoSql.adicionarCelula(finalTextoMaiorQuePostoNoTipoDados,coluna));
        Assertions.assertThrows(CelulaExcelComTamanhoMaiorQueOPermitidoColunaException.class,() ->elementoSql.setCelula(finalTextoMaiorQuePostoNoTipoDados));
    }

    @Test
    public void daCelulaComElementosNaoConversiveisExceptionQuandoTextoAAdicionarMaiorQueGuardadoNoTipoDados(){

        coluna.adicionar("numero[tinyint]",2);
        Assertions.assertThrows(CelulaComElementosNaoConversiveisException.class,() ->elementoSql.adicionarCelula("128",coluna));
        Assertions.assertThrows(CelulaComElementosNaoConversiveisException.class,() ->elementoSql.setCelula("128"));
    }


}
