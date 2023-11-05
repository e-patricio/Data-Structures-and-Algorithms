/**
 * Esta classe guarda os números das páginas em que uma palavra ocorre.
 */
public class ListaDeOcorrencias {
        
    // Classe interna Node
    private class Node {
        public int numPagina;
        public Node next;    
        public Node(int n) {
            numPagina = n;
            next = null;
        }
    }
    
    // Atributos
    private Node head;
    private Node tail;
    private int count;

    // Metodos 
    public ListaDeOcorrencias() {
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
     * Adiciona um número de página ao final da lista, caso ele ainda
     * não tenha sido adicionado.
     * @param numPagina número de página a ser adicionado ao final da lista
     * @return true se adicionou no final da lista o número de página  
     * recebido por parâmetro, e false caso contrário.
     */
    public boolean add(int numPagina)  {
        if (!contains(numPagina)) {
            Node n = new Node(numPagina);
            if (head == null) {
                head = n;
            } else {
                tail.next = n;
            }
            tail = n;
            count++;
            return true;
        }
        return false;
    }  
    
    /**
     * Retorna o elemento de uma determinada posição da lista.
     * @param index a posição da lista
     * @return o elemento da posição especificada
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */    
    public Integer get(int index) {
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException();
        }
		
        if (index == count-1) // se for para retornar o ultimo
            return tail.numPagina;
        
        Node aux = head;
        for (int i=0; i<index; i++) {
            aux = aux.next;
        }
        return aux.numPagina;
    }
 
    /**
     * Retorna true se a lista contêm o número de página passado
     * por parâmetro.
     * @param numPagina o elemento a ser procurado
     * @return true se a lista contêm o elemento especificado
     */
    public boolean contains(int numPagina) {
        Node aux = head;
        while(aux != null) {
            if (aux.numPagina == numPagina) {
                return true;
            }
            aux = aux.next;
        }
        return false;
    }    
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        Node aux = head;

        if(aux != null){
            s.append(aux.numPagina);
            aux = aux.next;
        }
        
        while (aux != null) {
            s.append(", ");
            s.append(aux.numPagina);
            aux = aux.next;
        }
        s.append(". \n");
        return s.toString();
    }

}
