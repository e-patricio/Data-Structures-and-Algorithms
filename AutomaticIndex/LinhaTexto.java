/**
 * @author Isabel H. Manssour
 */
public class LinhaTexto {
    private String linha;
    private String palavras[];
    private int contPalavras;
    
    /**
     * Recebe a string da linha que sera armazenada.
     * @param lin String com a linha de texto
     */
    public void setLine(String lin) {
        linha = lin;
        linha = linha.replaceAll("\\t"," "); // substitui tab por espaço em branco
        linha = linha.replaceAll("\\-"," "); // substituí hífen por espaço em branco
        linha = linha.replaceAll("\\/"," "); // substitui barra por espaço em branco
        linha = linha.replaceAll("\\'s",""); // para remover 's (is)
        linha = linha.replaceAll("\\'S",""); // para remover 's (is)
        linha = linha.replaceAll("\\'ll",""); // para remover 'll (will)
        linha = linha.replaceAll("\\'ve",""); // para remover 've (have)
        linha = linha.replaceAll("\\'re",""); // para remover 're (are) 
        linha = linha.replaceAll("\\'d",""); // para remover 'd (would)
        linha = linha.replaceAll(",",""); // para remover vírgulas
        linha = linha.replaceAll("\\.",""); // para remover ponto final
        linha = linha.replaceAll("\\?",""); // para remover ponto de interrogação
        linha = linha.replaceAll("\\?{2}",""); // para remover dois pontos de interrogação
        linha = linha.replaceAll("\\!",""); // para remover ponto exclamação
        linha = linha.replaceAll("\\(",""); // para remover abre parênteses
        linha = linha.replaceAll("\\)",""); // para remover fecha parênteses
        linha = linha.replaceAll("\\[",""); // para remover abre colchetes
        linha = linha.replaceAll("\\]",""); // para remover fecha colchetes
        linha = linha.replaceAll("\\{",""); // para remover abre chaves
        linha = linha.replaceAll("\\}",""); // para remover fecha chaves
        linha = linha.replaceAll("\\-{2}",""); // para remover dois hífen
        linha = linha.replaceAll("\\_",""); // para remover underline
        linha = linha.replaceAll("\\'",""); // para remover apóstofre
        linha = linha.replaceAll("\\;",""); // para remover ponto e vírgula
        linha = linha.replaceAll("\\:",""); // para remover dois pontos
        linha = linha.replaceAll("\"",""); // para remover aspas
        linha = linha.replaceAll("\\“",""); // para remover “
        linha = linha.replaceAll("\\”",""); // para remover ”
        linha = linha.replaceAll("\\‘",""); // para remover ‘
        linha = linha.replaceAll("\\’",""); // para remover ’
        linha = linha.replaceAll("\\°",""); // para remover °
        linha = linha.replaceAll("\\$",""); // para remover $
        linha = linha.replaceAll("\\£",""); // para remover £
        linha = linha.replaceAll("\\à","a"); // para remover acento
        linha = linha.replaceAll("\\é","e"); // para remover acento
        linha = linha.replaceAll("\\É","e"); // para remover acento
        linha = linha.replaceAll("\\ê","e"); // para remover acento
        linha = linha.replaceAll("\\æsthetic","aesthetic"); // para substituir por aesthetic
        
    
        palavras = linha.split(" "); // divide a string pelo espaco em branco 
        contPalavras = 0;
    }
    
    /**
     * Retorna uma palavra da linha.
     * @return a palavra, ou null caso não tenha mais palavras.
     */
    public String getNextWord() {
      String pal = null;
      if (contPalavras < palavras.length) {
          pal = palavras[contPalavras];
          contPalavras++;
      }
      return pal;
    }
}
