# Xls2Sql-Jar-Converter

Conversor de tabela no formato (.xls/.xlsx) para um arquivo em (.sql) todo feito em java 19.
A qual pode ser aplicado no mysql assim pondo os elementos em uma tabela do sql.

## Como Usar


### Tabela Xls

1. Quando for montar a tabela xls tudo oque precisa se atentar é em colocar o nome das colunas do sql nas células 
da primeira linha do arquivo xls. Com os seus respectivos tipos de dados entre colchetes ao lado, e caso haja 
alguma limitação de tamanho na tabela sql você deve por entre aspas ao lado do tipo de dado e ainda dentro dos 
colchetes.

    Ex:

    | EXEMPLO1[varchar(10)] | EXEMPLO2[int] |
    |-----------------------|---------------|

    1. Os tipos de Dados compativeis são:

         | Numerico  | Texto      | Data      | 
         |-----------|------------|-----------|
         | DECIMAL   | CHAR       | DATE      |
         | BIT       | VARCHAR    | YEAR      |
         | TINYINT   | TINYTEXT   | DATETIME  |
         | SMALLINT  | TEXT       | TIMESTAMP |
         | MEDIUMINT | MEDIUMTEXT | TIME      |
         | INT       | LONGTEXT   |
         | BIGINT    | BLOB       |
         |           | MEDIUMBLOB |
         |           | LONGBLOB   |
    2. Caso for utilizar deve escrever o tipo de dados igual está escrito acima, com exceção que pode estar maiúsculo, minusculo e com espaçamento, isso o código consegue resolver.     
   

2. Nas linhas seguintes você vai colocar os dados respectivos para uma determinada tupla do sql.
   Obs: Não se esqueça de colocar os dados ordenados pela ordem das colunas postas previamente e sem ultrapassar o limite de células de coluna escritas.

    Ex2:

   | EXEMPLO1[varchar(10)] |   EXEMPLO2[int]  |
   |-----------------------|------------------|
   | tupla1.elemento1      | tupla1.elemento2 |
   | tupla2.elemento1      | tupla2.elemento2 |



### Utilizando o programa

1. Primeiro verifique se você tem o java 19 ou maior instalado.
   * Abra o terminal e digite o comando abaixo:
  
     `java -version`
     
     * Após isso o terminal deve escrever qual a versão do seu java se ele for 19 ou maior o programa vai rodar, se for inferior será necessário atualizar o java para no minimo o 19. Ainda se o terminal der um erro pode ser que você não tenha o java instalado ou o caminho dele não está nas variaveis de ambiente. De qualquer forma recomendo que instale ou reinstale o java 19 seguindo todos os passos recomendados que ele já vai aparecer nas variaveis de ambiente.

  
2. Basta baixar o arquivo Xls2Sql.jar na aba releases e inicialo apartir da linha de comando digitando o comando:

    `>java -jar Xls2Sql.jar`

   * Caso o conversor não esteja na mesma pasta onde está a linha de comando será necessário digitar o caminho até a pasta quando for inicializar o conversor.
     
     `>java -jar  caminho_ate_a_pasta/Xls2Sql.jar`  


3. Com isso o programa do conversor vai inicializar e basta digitar as informações que ele pede para a conversão ocorrer corretamente.

   




