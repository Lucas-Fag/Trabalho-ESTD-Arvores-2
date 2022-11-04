package arvores;
import java.util.function.Consumer;

import estruturasLineares.Fila;
import estruturasLineares.Pilha;

public class ArvoreAvl<T extends Comparable<T>> {
    class Elemento {
        Elemento pai;
        Elemento esquerda;
        Elemento direita;
        T valor;
    
        public Elemento(T valor) {
            this.valor = valor;
        }
    }
  
    private Elemento raiz;
    private int quantidadeOperacoes;

    public int getQuantidadeOperacoes() {
        return quantidadeOperacoes;
    }

    public boolean isVazia() {
        return raiz == null;
    }

    public Elemento adicionar(T valor) {
        Elemento e = new Elemento(valor);
        Elemento pai = this.raiz;

        while (pai != null) {
            // Conta uma verificação do while, uma verificação para ver o tamanho do valor e outra para ver se posiciona na esquerda ou direita
            quantidadeOperacoes += 3;
            if (valor.compareTo(pai.valor) < 0) {
                if (pai.esquerda == null) {
                    e.pai = pai;
                    pai.esquerda = e;
                    balanceamento(pai);
                    
                    return e;
                } else {
                    pai = pai.esquerda;
                }
            } else {
                if (pai.direita == null) {
                    e.pai = pai;
                    pai.direita = e;
                    balanceamento(pai);

                    return e;
                } else {
                    pai = pai.direita;
                }
            }
        }

        this.raiz = e;
        return e;
    }

    public void balanceamento(Elemento elemento) {
        while (elemento != null) {
            int fator = fb(elemento);

            quantidadeOperacoes++;
            if (fator > 1) {
                quantidadeOperacoes++;
                //Arvore mais profunda para esquerda, rotação para a direita
                if (fb(elemento.esquerda) > 0) {
                    rsd(elemento);
                } else {
                    rdd(elemento);
                }
            } else if (fator < -1) {
                quantidadeOperacoes++;
                //Arvore mais profunda para direita, rotação para a esquerda
                if (fb(elemento.direita) < 0) {
                    rse(elemento);
                } else {
                    rde(elemento);
                }
            }

            elemento = elemento.pai;
        }
    }
    
    public Elemento adicionar(Elemento pai, T valor) {
        Elemento e = new Elemento(valor);
        
        e.pai = pai;
         
        if (pai == null) {
            raiz = e;
        }
        
        return e;
    }

    public void remover(Elemento e) {
        if (e.esquerda != null)
            remover(e.esquerda);  
        
        if (e.direita != null)
            remover(e.direita);
        
        if (e.pai == null) {
            raiz = null;
        } else {
            if (e.pai.esquerda == e) {
                e.pai.esquerda = null;
            } else {
                e.pai.direita = null;
            }
        }
    }

    public void percorrer(Elemento e, Consumer<T> callback) {
        if (e != null) {
            if (e.valor == (Integer) 5) {
                System.out.println("");
            }
            
            callback.accept(e.valor);
            percorrer(e.esquerda, callback);
            percorrer(e.direita, callback);
        }
    }

    public Elemento pesquisar(Elemento e, T valor) {
        while (e != null) {
            if (e.valor.equals(valor)) {
                return e;
            } else if (valor.compareTo(e.valor) > 0) {
                e = e.direita;
            } else {
                e = e.esquerda;
            }
        }
        
        return null;
    }

    public int caminho(Elemento e) {
        int contador = 1;

        while (e.pai != null) { //Enquanto não alcançamos a raiz
            contador++;
            e = e.pai;
        }

        return contador;
    }

    public void percorrerInOrder(Elemento e, Consumer<T> callback) {
        if (e != null) {
            percorrerInOrder(e.esquerda, callback);
            callback.accept(e.valor);
            percorrerInOrder(e.direita, callback);
        }
    }

    public void percorrerPosOrder(Elemento e, Consumer<T> callback) {
        if (e != null) {
            percorrerPosOrder(e.esquerda, callback);
            percorrerPosOrder(e.direita, callback);
            callback.accept(e.valor);
        }
    }

    public void percorrer(Consumer<T> callback) {
        this.percorrer(raiz, callback);
    }

    public void percorrerInOrder(Consumer<T> callback) {
        this.percorrerInOrder(raiz, callback);
    }

    public void percorrerPosOrder(Consumer<T> callback) {
        this.percorrerPosOrder(raiz, callback);
    }

    public void percorrerLargura(Consumer<T> callback) {
        Fila<ArvoreAvl<T>.Elemento> fila = new Fila<>();

        fila.adicionar(raiz);

        while (!fila.isVazia()) {
            ArvoreAvl<T>.Elemento e = fila.remover();

            //visitando o valor do elemento atual
            callback.accept(e.valor); 

            if (e.esquerda != null) {
                fila.adicionar(e.esquerda);
            }

            if (e.direita != null) {
                fila.adicionar(e.direita);
            }
        }
    }

    public void percorrerProfundidade(Consumer<T> callback) {
        Pilha<ArvoreAvl<T>.Elemento> pilha = new Pilha<>();

        pilha.adicionar(raiz);

        while (!pilha.isVazia()) {
            ArvoreAvl<T>.Elemento e = pilha.remover();

            //visitando o valor do elemento atual
            callback.accept(e.valor); 

            if (e.direita != null) {
                pilha.adicionar(e.direita);
            }

            if (e.esquerda != null) {
                pilha.adicionar(e.esquerda);
            }
        }
    }

    private int altura(Elemento e){
        int esquerda = 0,direita = 0;
    
        if (e.esquerda != null) {
            esquerda = altura(e.esquerda) + 1;
        }
    
        if (e.direita != null) {
            direita = altura(e.direita) + 1;
        }
      
        return esquerda > direita ? esquerda : direita;
    }

    private int fb(Elemento e) {
        int esquerda = 0,direita = 0;
      
        if (e.esquerda != null) {
            esquerda = altura(e.esquerda) + 1;
        }
    
        if (e.direita != null) {
            direita = altura(e.direita) + 1;
        }
      
        return esquerda - direita;
    }
    
    private Elemento rse(Elemento e) {
        Elemento pai = e.pai;
        Elemento direita = e.direita;
        
        quantidadeOperacoes++;
        if (direita.esquerda != null) {
            direita.esquerda.pai = e;
        }

        e.direita = direita.esquerda;
        e.pai = direita;
    
        direita.esquerda = e;
        direita.pai = pai;

        quantidadeOperacoes++;
        if (direita.pai == null) {
            this.raiz = direita;
        } else {
            quantidadeOperacoes++;
            if (pai.esquerda == e) {
                pai.esquerda = direita;
            } else {
                pai.direita = direita;
            }
        }
      
        return direita;
    }

    private Elemento rsd(Elemento e) {
        Elemento pai = e.pai;
        Elemento esquerda = e.esquerda;
        
        quantidadeOperacoes++;
        if (esquerda.direita != null) {
            esquerda.direita.pai = e;
        }

        e.esquerda = esquerda.direita;
        e.pai = esquerda;
      
        esquerda.direita = e;
        esquerda.pai = pai;

        quantidadeOperacoes++;
        if (esquerda.pai == null) {
            this.raiz = esquerda;
        } else {
            quantidadeOperacoes++;
            if (pai.esquerda == e) {
                pai.esquerda = esquerda;
            } else {
                pai.direita = esquerda;
            }
        }
      
        return esquerda;
    }
    
    private Elemento rde(Elemento e) {
        e.direita = rsd(e.direita);
        return rse(e);
    }
    
    private Elemento rdd(Elemento e) {
        e.esquerda = rse(e.esquerda);
        return rsd(e);
    }
    
}