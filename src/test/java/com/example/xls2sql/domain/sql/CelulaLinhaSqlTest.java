package com.example.xls2sql.domain.sql;

import com.example.xls2sql.sql.domain.CelulaLinhaSql;
import com.example.xls2sql.sql.domain.Coluna;
import com.example.xls2sql.sql.exceptions.CelulaComElementosNaoConversiveisException;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class CelulaLinhaSqlTest {
    Coluna coluna;
    CelulaLinhaSql celulaLinhaSql;
    @BeforeEach
    public void antesCadaTeste(){
        this.coluna = new Coluna();
        this.coluna.adicionar("ingrediente[varchar(50)]",2);
        this.celulaLinhaSql = new CelulaLinhaSql(1,1);
        ArrayList<String> celula = new ArrayList();
        celula.add("arroz");
        celulaLinhaSql.adicionarCelula(celula,coluna.getTipoDados());

    }

    @Test
    public  void guardaPosicaoLihaAoCriarElementoSql(){
        CelulaLinhaSql celulaLinhaSql = new CelulaLinhaSql(1,1);
        Assertions.assertEquals(1, celulaLinhaSql.getLinha());
    }


    @Test
    public void guardaCelulaAoUsarAdicionarCelula(){

        Assertions.assertEquals("[arroz]", celulaLinhaSql.getCelula().toString());
    }


    @Test
    public void modificaCelulaAoUsarSetCelula(){
        Assertions.assertEquals("[arroz]", celulaLinhaSql.getCelula().toString());
        ArrayList<String> celula = new ArrayList();
        celula.add("feijão");
        celulaLinhaSql.setCelula(celula);
        Assertions.assertEquals(celula.toString(), celulaLinhaSql.getCelula().toString());
    }

    @Test
    public void elementoSqlGuardaTipoDadosDaColuna(){
        Assertions.assertEquals(coluna.getTipoDados(), celulaLinhaSql.getTipoDados());
    }

    @Test
    public void daCelulaExcelComTamanhoMaiorQueOPermitidoColunaExceptionQuandoTextoAAdicionarMaiorQueGuardadoNoTipoDados(){
        String textoMaiorQuePostoNoTipoDados = "M";
        for (int i = 0; i <= coluna.getTipoDados().numeroElementos() + 1; i++){
            textoMaiorQuePostoNoTipoDados += "M";
        }
        ArrayList<String> celula = new ArrayList();
        celula.add(textoMaiorQuePostoNoTipoDados);

        Assertions.assertThrows(CelulaComElementosNaoConversiveisException.class,() -> celulaLinhaSql.adicionarCelula(celula,coluna.getTipoDados()));
        Assertions.assertThrows(CelulaComElementosNaoConversiveisException.class,() -> celulaLinhaSql.setCelula(celula));
    }

    @Test
    public void daCelulaComElementosNaoConversiveisExceptionQuandoTextoAAdicionarMaiorQueGuardadoNoTipoDados(){

        coluna.adicionar("numero[tinyint]",2);
        ArrayList<String> celula = new ArrayList();
        celula.add("128");
        Assertions.assertThrows(CelulaComElementosNaoConversiveisException.class,() -> celulaLinhaSql.adicionarCelula(celula,coluna.getTipoDados()));
        Assertions.assertThrows(CelulaComElementosNaoConversiveisException.class,() -> celulaLinhaSql.setCelula(celula));
    }


}
