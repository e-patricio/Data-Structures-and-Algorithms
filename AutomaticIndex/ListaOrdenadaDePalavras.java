/**
 * Esta classe guarda as palavras do índice remissivo em ordem alfabética.
 */

public class ListaOrdenadaDePalavras {

    // Classe interna 
    private class Palavra {
        public String s;
        public ListaDeOcorrencias listaOcorrencias;
        public int ocorrenciasNoTexto;
        public Palavra next; 
           
        public Palavra(String str) {
            s = str;
            next = null;
            listaOcorrencias = new ListaDeOcorrencias();
            ocorrenciasNoTexto = 1;
        }
    }
    
    // Atributos
    private Palavra head;
    private Palavra tail;
    private int count;
    
    // Metodos
    public ListaOrdenadaDePalavras() {
        head = null;
        tail = null;
        count = 0;

    }

     /**
     * Retorna true se a lista não contêm elementos.
     * @return true se a lista não contêm elementos
     */
    public boolean isEmpty() {
        return (head == null);
    }   
    
    /**
     * Retorna o número de elementos da lista.
     * @return o número de elementos da lista
     */
    public int size() {
        return count;
    }  
    
    /**
     * Esvazia a lista.
     */
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * Adiciona palavras na lista.
     * @param s palavra que será adicionada na lista
     * @return true se adicionar e false se não adicionar (se a lista já tiver a palavra)
     */
    public boolean addPalavra(String s)  {
        Palavra aux = contains(s);
        Palavra palavra = new Palavra(s);
        if (aux == null) {
            if (head == null) {
                head = palavra;
            } else {
                tail.next = palavra;
            }
            tail = palavra;
            count++;
            return true;
        }
        return false;
    }  

    /**
     * Adiciona palavras na lista em ordem alfabética e suas ocorrências.
     * @param s palavra que será adicionada na lista
     * @param ocorrencia número da página onde está a palavra
     */
    public void addPalavra(String s, int ocorrencia) {
        Palavra aux = contains(s);
        if (s != null && s != "" && s != " " && !s.isEmpty()) {
            if (aux == null) {
                Palavra palavra = new Palavra(s);
                palavra.listaOcorrencias.add(ocorrencia);
                    
                    if (head == null) {
                        head = palavra;
                        tail = palavra;
                    } else {
                        Palavra current = head;
                        Palavra ant = null;
                        while (current != null && s.compareTo(current.s) > 0) {
                            ant = current;
                            current = current.next;
                        }

                        if (ant == null) {
                            palavra.next = head;
                            head = palavra;
                        } 
                        else if (current == null) {
                            ant.next = palavra;
                            tail = palavra;
                        } 
                        else {
                            palavra.next = current;
                            ant.next = palavra;
                        }
                    }
            } else {
                aux.listaOcorrencias.add(ocorrencia);
                aux.ocorrenciasNoTexto++; //conta as ocorrências da palavra
            }
        }
        count++; //conta o tamanho total da lista, incluindo palavras repetidas!   
    } 

    /**
     * Retorna as ocorrências de uma determinada palavra da lista.
     * @param s palavra que devemos encontrar na lista
     * @return se encontrar a palavra retorna as ocorrências da mesma, 
     * caso contrário retorna "Palavra não encontrada."
     */
    public String get(String s) { 
        Palavra aux = contains(s);
        if (aux != null) {
            return "A palavra "+ s.toUpperCase() +" pode ser encontrada na(s) página(s): " + aux.listaOcorrencias.toString();
        }
        return "Palavra não encontrada.";
    }

    /**
     * Verifica se a lista contêm a palavra.
     * @param s palavra que será verificada
     * @return a palavra se estiver na lista e null se não estiver
     */
    public Palavra contains(String s) {
        Palavra aux = head;
        
        while (aux != null) {
            if (aux.s.equalsIgnoreCase(s)) {
                return aux;
            }
            aux = aux.next;
        }
        return null;
    }

    /**
     * Retorna a palavra com o maior número de ocorrências no texto.
     * @return a palavra com o maior número de ocorrências no texto
     */
    public String countOccurrenceWord() {
        int auxCount = 0;
        Palavra aux = head;
        String maisFrequente = "";
            
        while (aux != null) {
            String palavra = aux.s; 
            int count = aux.ocorrenciasNoTexto;
            if (count > auxCount) {
                auxCount = count;
                maisFrequente = palavra;
            }
            aux = aux.next;
        }
        
        if (!maisFrequente.isEmpty()) {
            return "A palavra mais frequente é: " + maisFrequente.toUpperCase() + " (" + auxCount + " ocorrências)";
        } else {
            return "Nenhuma palavra encontrada no texto.";
        }
    }

    @Override
    public String toString() {
    StringBuilder s = new StringBuilder();
    Palavra current = head.next;
    while (current != null) {
        s.append(current.s).append(": ").append(current.listaOcorrencias.toString()).append("\n");
        current = current.next;
    }
    return s.toString();
    } 
}
