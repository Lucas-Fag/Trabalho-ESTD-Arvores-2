package arvores;
import java.util.Vector;
import java.util.function.Consumer;

public class ArvoreB<T extends Comparable<T>> {
    class Elemento {
        Elemento pai; // Referência para o elemento pai
        Vector<Elemento> filhos; // Vetor de elementos filho
        Vector<T> chaves; // Vetor de chaves (valores)

        public Elemento() {
            filhos = new Vector<>();
            chaves = new Vector<>();
        }
    }

    private Elemento raiz;
    private int ordem;
    private int quantidadeOperacoes;

    public int getQuantidadeOperacoes() {
        return quantidadeOperacoes;
    }

    public ArvoreB(int ordem) {
        this.ordem = ordem;
        raiz = new Elemento();
    }

    public void percorre(Elemento e, Consumer<T> callback) {
        if (e != null) {
            int total = e.chaves.size();

            for (int i = 0; i < total; i++) {
                // Visita o filho da esquerda/direita
                percorre(e.filhos.get(i), callback);

                callback.accept(e.chaves.get(i));
            }

            // Visita ultimo filho (direita)
            percorre(e.filhos.get(total), callback);
        }
    }

    private boolean localizaChave(T chave) {
        Elemento e = raiz;

        while (e != null) {
            int i = pesquisaBinaria(e, chave);

            if (i < e.chaves.size() && e.chaves.get(i).compareTo(chave) == 0) {
                return true; // Encontrou

            } else {
                e = i < e.filhos.size() ? e.filhos.get(i) : null;
                
            }
        }

        return false; // Não encontrou
    }

    private int pesquisaBinaria(Elemento e, T chave) {
        int inicio = 0, fim = e.chaves.size() - 1, meio;

        while (inicio <= fim) {
            quantidadeOperacoes++;
            meio = (inicio + fim) / 2;

            if (e.chaves.get(meio).compareTo(chave) == 0) {
                quantidadeOperacoes++;
                return meio; // Encontrou

            } else if (e.chaves.get(meio).compareTo(chave) > 0) {
                quantidadeOperacoes += 2;
                fim = meio - 1;

            } else {
                quantidadeOperacoes += 2;
                inicio = meio + 1;
            }
        }

        return inicio; // Não encontrou
    }

    private Elemento localizaNo(T chave) {
        Elemento e = raiz;

        while (e != null) {
            quantidadeOperacoes++;
            int i = pesquisaBinaria(e, chave);
            Elemento filho = i < e.filhos.size() ? e.filhos.get(i) : null;

            quantidadeOperacoes++;
            if (filho != null) {
                e = filho;
            } else {
                return e; // Encontrou nó
            }
        }

        return null; // Não encontrou nenhum nó
    }

    private void adicionaChaveNo(Elemento e, Elemento novo, T chave) {
        int i = pesquisaBinaria(e, chave);

        e.chaves.insertElementAt(chave, i);

        quantidadeOperacoes++;
        if (e.filhos.size() == 0)
            e.filhos.insertElementAt(null, i);

        e.filhos.insertElementAt(novo, i + 1);
    }

    private boolean transbordo(Elemento e) {
        return e.chaves.size() > ordem * 2;
    }

    private Elemento divideNo(Elemento e) {
        int meio = e.chaves.size() / 2;
        Elemento novo = new Elemento();
        novo.pai = e.pai;

        for (int i = meio + 1; i <= e.chaves.size(); i++) {
            quantidadeOperacoes += 2;
            if (i < e.chaves.size()) {
                T v = e.chaves.get(i);
                novo.chaves.add(v);
            }
            
            Elemento filho = e.filhos.get(i);
            novo.filhos.add(filho);
            
            quantidadeOperacoes++;
            if (filho != null)
                filho.pai = novo;
        }

        e.chaves.subList(meio, e.chaves.size()).clear();
        e.filhos.subList(meio + 1, e.filhos.size()).clear();
        return novo;
    }

    public void adicionaChave(T chave) {
        Elemento e = localizaNo(chave);

        adicionaChaveRecursivo(e, null, chave);
    }

    private void adicionaChaveRecursivo(Elemento e, Elemento novo, T chave) {
        adicionaChaveNo(e, novo, chave);

        quantidadeOperacoes++;
        if (transbordo(e)) {
            T promovido = e.chaves.get(ordem);
            novo = divideNo(e);

            quantidadeOperacoes++;
            if (e.pai == null) {
                Elemento pai = new Elemento();
                pai.filhos.add(e);
                adicionaChaveNo(pai, novo, promovido);
                e.pai = pai;
                novo.pai = pai;
                raiz = pai;
            } else {
                adicionaChaveRecursivo(e.pai, novo, promovido);
            }
        }
    }
}
