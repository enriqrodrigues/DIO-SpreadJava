package dio;

public class ArvoreBinaria<T extends Comparable<T>> {

    private BinNo<T> raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public void inserir(T conteudo){
        BinNo<T> novoNo = new BinNo<>(conteudo);
        raiz = inserir(raiz, novoNo);          //Esse método vai chamar o inserir privado.
    }

    private BinNo<T> inserir(BinNo<T> atual, BinNo<T> novoNo){
        if(atual == null){      //Se o nó atual for nulo, significa que estamos na raiz,
            return novoNo;      //então vamos retornar o novo nó.
        }else if(novoNo.getConteudo().compareTo(atual.getConteudo()) < 0) {
                                //A partir desse if, vamos correr a árvore
            atual.setNoEsq(inserir(atual.getNoEsq(), novoNo));
        }else{
            atual.setNoDir(inserir(atual.getNoDir(), novoNo));
        }
        return atual;
    }

    public void exibirInOrdem(){
        System.out.println("\n Exibindo InOrdem");
        exibirInOrdem(this.raiz);
    }           //Aqui estamos exibindo InOrdem a partir da raiz.

    private void exibirInOrdem(BinNo<T> atual){
        if(atual != null){
            exibirInOrdem(atual.getNoEsq());
            System.out.print(atual.getConteudo() + ", ");
            exibirInOrdem(atual.getNoDir());
        }
    }

    public void exibirPosOrdem(){
        System.out.println("\n Exibindo PosOrdem");
        exibirPosOrdem(this.raiz);
    }           //Aqui estamos exibindo InOrdem a partir da raiz.

    private void exibirPosOrdem(BinNo<T> atual){
        if(atual != null){
            exibirPosOrdem(atual.getNoEsq());
            exibirPosOrdem(atual.getNoDir());
            System.out.print(atual.getConteudo() + ", ");
        }
    }

    public void exibirPreOrdem(){
        System.out.println("\n Exibindo PreOrdem");
        exibirPreOrdem(this.raiz);
    }           //Aqui estamos exibindo InOrdem a partir da raiz.

    private void exibirPreOrdem(BinNo<T> atual){
        if(atual != null){
            System.out.print(atual.getConteudo() + ", ");
            exibirPreOrdem(atual.getNoEsq());
            exibirPreOrdem(atual.getNoDir());
        }
    }

    public void remove(T conteudo){
        try{
            BinNo<T> atual = this.raiz;
            BinNo<T> pai = null;
            BinNo<T> filho = null;
            BinNo<T> temp = null;

            while(atual != null && !atual.getConteudo().equals(conteudo)){
                //Se estiver numa árvore vazio, o atual será nulo e não fará nada.
                //Enquanto o conteudo não for igual ao conteúdo de atual, ele continua rodando.
                pai = atual;
                if(conteudo.compareTo(atual.getConteudo()) < 0){
                    atual = atual.getNoEsq();
                }else{
                    atual = atual.getNoDir();
                }
            }
            if(atual == null){
                System.out.println("Conteudo não encontrado. Bloco Try.");
            }
            if(pai == null){
                if(atual.getNoDir() == null){
                    this.raiz = atual.getNoEsq();
                }else if(atual.getNoEsq() == null){
                    this.raiz = atual.getNoDir();
                }else{
                    for(temp = atual, filho = atual.getNoEsq();
                    filho.getNoDir() != null;
                    temp = filho, filho = filho.getNoEsq()
                    ){
                        if(filho != atual.getNoEsq()){
                            temp.setNoDir(filho.getNoEsq());
                            filho.setNoEsq(raiz.getNoEsq());
                        }
                    }
                    filho.setNoDir(raiz.getNoDir());
                    raiz = filho;
                }
            }else if(atual.getNoDir() == null){
                if(pai.getNoEsq() == atual){
                    pai.setNoEsq(atual.getNoEsq());
                }else{
                    pai.setNoDir(atual.getNoEsq());
                }
            }else if(atual.getNoEsq() == null){
                if(pai.getNoEsq() == atual){
                    pai.setNoEsq(atual.getNoDir());
                }else{
                    pai.setNoDir(atual.getNoDir());
                }
            }else{
                for(
                        temp = atual, filho = atual.getNoEsq();
                        filho.getNoDir() != null;
                        temp = filho, filho = filho.getNoDir()
                ){
                    if(filho != atual.getNoEsq()){
                        temp.setNoDir(filho.getNoEsq());
                        filho.setNoEsq(atual.getNoEsq());
                    }
                    filho.setNoDir(atual.getNoDir());
                    if(pai.getNoEsq() == atual){
                        pai.setNoEsq(filho);
                    }else{
                        pai.setNoDir(filho);
                    }
                }
            }
        }catch (NullPointerException erro){
            System.out.println("Conteudo não encontrado. Bloco Catch.");
        }
    }
}
