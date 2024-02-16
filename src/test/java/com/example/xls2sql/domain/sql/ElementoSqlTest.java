package com.example.xls2sql.domain.sql;

import com.example.xls2sql.sql.domain.Coluna;
import com.example.xls2sql.sql.domain.ElementoSql;
import com.example.xls2sql.sql.exceptions.CelulaComElementosNaoConversiveisException;
import com.example.xls2sql.sql.exceptions.TipoDadoSqlNaoEncontradoException;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class ElementoSqlTest {
    Coluna coluna;
    ElementoSql elementoSql;
    @BeforeEach
    public void antesCadaTeste(){
        this.coluna = new Coluna();
        this.coluna.adicionar("ingrediente[varchar(50)]",2);
        this.elementoSql = new ElementoSql(1,1);
        ArrayList<String> celula = new ArrayList();
        celula.add("arroz");
        elementoSql.adicionarCelula(celula,coluna);

    }

    @Test
    public  void guardaPosicaoLihaAoCriarElementoSql(){
        ElementoSql elementoSql = new ElementoSql(1,1);
        Assertions.assertEquals(1, elementoSql.getLinha());
    }


    @Test
    public void guardaCelulaAoUsarAdicionarCelula(){

        Assertions.assertEquals("[arroz]",elementoSql.getCelula().toString());
    }


    @Test
    public void modificaCelulaAoUsarSetCelula(){
        Assertions.assertEquals("[arroz]",elementoSql.getCelula().toString());
        ArrayList<String> celula = new ArrayList();
        celula.add("feijão");
        elementoSql.setCelula(celula);
        Assertions.assertEquals(celula.toString(),elementoSql.getCelula().toString());
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
        ArrayList<String> celula = new ArrayList();
        celula.add(textoMaiorQuePostoNoTipoDados);

        Assertions.assertThrows(CelulaComElementosNaoConversiveisException.class,() ->elementoSql.adicionarCelula(celula,coluna));
        Assertions.assertThrows(CelulaComElementosNaoConversiveisException.class,() ->elementoSql.setCelula(celula));
    }

    @Test
    public void daCelulaComElementosNaoConversiveisExceptionQuandoTextoAAdicionarMaiorQueGuardadoNoTipoDados(){

        coluna.adicionar("numero[tinyint]",2);
        ArrayList<String> celula = new ArrayList();
        celula.add("128");
        Assertions.assertThrows(CelulaComElementosNaoConversiveisException.class,() ->elementoSql.adicionarCelula(celula,coluna));
        Assertions.assertThrows(CelulaComElementosNaoConversiveisException.class,() ->elementoSql.setCelula(celula));
    }


}
