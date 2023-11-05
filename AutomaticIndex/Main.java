import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Classe que inicializa a execução da aplicação.
 */
public class Main {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String nomeArquivo = "";
    String s = "";
    int escolhaMenu = -1;
    int countStop = 0;
    ListaDeOcorrencias listaOcorrencias = new ListaDeOcorrencias();
    ListaOrdenadaDePalavras listaPalavras = new ListaOrdenadaDePalavras();
    ListaOrdenadaDePalavras stopWords = new ListaOrdenadaDePalavras();

    
    // Inicializa o programa com o texto escolhido pelo usuário
    System.out.println("\nSeja bem-vindo ao nosso sistema automático de índices remissivos =)");
    System.out.println("\nTemos os seguintes livros disponíveis: ");
    System.out.println("- Alice");
    System.out.println("- Cocoa and Chocolate");
    System.out.println("- Five Weeks in a Balloon");
    System.out.println("- Java");
    System.out.print("Informe o título do livro desejado (Ex: Java): ");
    nomeArquivo = scanner.nextLine();

    // Troca a String informada pelo nome do arquivo .txt
    while (!nomeArquivo.toLowerCase().equals("alice") &&
    !nomeArquivo.toLowerCase().equals("cocoa and chocolate") &&
    !nomeArquivo.toLowerCase().equals("five weeks in a balloon") &&
    !nomeArquivo.toLowerCase().equals("java")) {
    System.out.println("\nLivro não encontrado. Por favor, escolha um dos livros disponíveis: ");
        nomeArquivo = scanner.nextLine();
    }
    if (nomeArquivo.toLowerCase().equals("alice")) {
        nomeArquivo = "alice.txt";
    }
    if (nomeArquivo.toLowerCase().equals("cocoa and chocolate")) {
        nomeArquivo = "cocoaandchocolate.txt";
    }
    if (nomeArquivo.toLowerCase().equals("five weeks in a balloon")) {
        nomeArquivo = "fiveweeksinaballoon.txt";
    }
    if (nomeArquivo.toLowerCase().equals("java")) {
        nomeArquivo = "java.txt";
    }
    System.out.println("\nVocê selecionou o arquivo: " + nomeArquivo);
     
    // Faz a leitura do livro e do arquivo das stopwords
    int nLinha = 0; 
    int nPagina = 1; //começa na primeira página
    
    ArquivoTexto arquivo = new ArquivoTexto(); //objeto que gerencia o arquivo
    LinhaTexto linha = new LinhaTexto(); //objeto que gerencia uma linha
    ArquivoTexto arquivoStop = new ArquivoTexto(); 
    LinhaTexto linhaStop = new LinhaTexto(); 
    String l;

    arquivoStop.open("StopWords-EN.txt");
    arquivo.open(nomeArquivo);

    do {
        l = arquivoStop.getNextLine(); 
        if (l == null) //acabou o arquivo?
           break;
        linhaStop.setLine(l); //define o texto da linha
        do {
            String palavra = linhaStop.getNextWord(); //obtém a próxima palavra da linha
            if (palavra == null)//acabou a linha
            {
                break;
            } 
            stopWords.addPalavra(palavra.toLowerCase()); 
        } while (true);
    } while (true);
    
    do {
        l = arquivo.getNextLine();
        if (l == null) 
           break;
        nLinha++; 
        if (nLinha == 40) 
        {
            nLinha = 1;
            nPagina++;
        }
        listaOcorrencias.add(nPagina);
        linha.setLine(l);
        do {
            String palavra = linha.getNextWord(); 
            if (palavra == null)
            {
                break;
            } 
            if (stopWords.contains(palavra) == null) {
                listaPalavras.addPalavra(palavra.toLowerCase(), nPagina); 
            } else {
                countStop++;
            }
         } while (true);

    } while (true);
    
    arquivo.close();
    arquivoStop.close();
    
    // Abre o menu de opções para usar o programa.
    do {
        System.out.println("\n--------------- MENU ---------------");
        System.out.println("1. Exibir o índice remissivo");
        System.out.println("2. Percentual de stopwords do texto");
        System.out.println("3. Palavra mais frequente");
        System.out.println("4. Encontrar palavra no texto");
        System.out.println("5. Sair do programa");
        System.out.println("------------------------------------");
        System.out.println("Digite o número da opção desejada: ");
        escolhaMenu = scanner.nextInt();
        while (escolhaMenu < 1 || escolhaMenu > 5) {
            System.out.println("Opção inválida. Digite novamente: ");
        }

        if (escolhaMenu == 1) {
            System.out.println(listaPalavras.toString());
        }

        if (escolhaMenu == 2) {
            DecimalFormat df = new DecimalFormat("0.0");

            double tam = countStop + listaPalavras.size();
            double tamStop = countStop;
            double conta = (tamStop/tam)*100.0;

            System.out.println("O percentual de stopwords é de " + df.format(conta) + "%.");

            System.out.println("\nTotal de palavras no texto: " + (countStop + listaPalavras.size()));
            System.out.println("Total de stopwords no texto: " + countStop);
        }

        if (escolhaMenu == 3) {
            System.out.println(listaPalavras.countOccurrenceWord());
        }

        if (escolhaMenu == 4) {
            System.out.println("Qual palavra você gostaria de encontrar? ");
            s = scanner.next();
            s = s.toLowerCase();
            System.out.println(listaPalavras.get(s));

        }
    } while (escolhaMenu != 5); 
    System.out.println("Até a próxima =)");
    scanner.close();
    }
}
