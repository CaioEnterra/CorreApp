package br.com.hellmets.motocerol;

import android.icu.text.AlphabeticIndex;
import android.view.MotionEvent;

import br.com.hellmets.motocerol.AndGraph.AGAnimation;
import br.com.hellmets.motocerol.AndGraph.AGGameManager;
import br.com.hellmets.motocerol.AndGraph.AGInputManager;
import br.com.hellmets.motocerol.AndGraph.AGScene;
import br.com.hellmets.motocerol.AndGraph.AGScreenManager;
import br.com.hellmets.motocerol.AndGraph.AGSoundManager;
import br.com.hellmets.motocerol.AndGraph.AGSprite;
import br.com.hellmets.motocerol.AndGraph.AGTimer;


public class CenaPlay extends AGScene {

    AGSprite cenario = null;
    AGSprite boneco = null;
    AGSprite pedra1 = null;
    AGSprite gameOver = null;


    //AGSprite carta = null;
    //AGSprite gato = null;

    Boolean sobe, desce, fim = false;
    float alturaPulo, alturaPiso = 0;
    int i, sonPulo = 0;


    CenaPlay(AGGameManager vrManager){
        super(vrManager);
    }


    @Override
    public void init() {
        //Atribuindo a variavel o efeito de som a cada pulo/toque na tela valifdo para o pulo do boneco
        //Trocar o son do pulo
        sonPulo = AGSoundManager.vrSoundEffects.loadSoundEffect("clique.mp3");

        //Atribuindo as variavais o valor do pulo e da posição do piso paras as condições que seram utilizado nos if de condição do pulo
        //mais favil atribuir valor a variavel para não precisar sempre mudar a logica
        alturaPulo = AGScreenManager.iScreenHeight*0.62f;
        alturaPiso = AGScreenManager.iScreenHeight*0.22f;

        //inicio do cenario
        cenario = this.createSprite( R.mipmap.cenario2,2,1);
        cenario.setScreenPercent(300,100);
        //posicionando o inicio do cenario no fim da tela
        cenario.vrPosition.setXY(AGScreenManager.iScreenWidth*1.5f,AGScreenManager.iScreenHeight/2);

        //inicio do boneco
        boneco = this.createSprite(R.mipmap.boneco5,8,2);
        boneco.setScreenPercent(15,20);
        boneco.vrPosition.setXY(AGScreenManager.iScreenWidth /2, AGScreenManager.iScreenHeight*0.22f);

        //inicio da pedra
        pedra1 = this.createSprite(R.mipmap.pedra,1,1);
        pedra1.setScreenPercent(12,12);
        pedra1.vrPosition.setXY(AGScreenManager.iScreenWidth,alturaPiso -70);

        //Sprite do game over
        gameOver = this.createSprite(R.mipmap.gameover,1,1);
        gameOver.setScreenPercent(30,15);
        gameOver.vrPosition.setXY(AGScreenManager.iScreenWidth/2, AGScreenManager.iScreenHeight*2);


    }

    @Override
    public void restart() {
    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {

        //Evento que ativa o botão retorna, fazendo que retorne para sa cena de menu
        if (AGInputManager.vrTouchEvents.backButtonClicked()){
            this.vrGameManager.setCurrentScene(0);
            fim=false;
            return;
        }

        if(fim == false){
            boneco.addAnimation(10, true ,0,7);

            //movimento da pedra
            pedra1.vrPosition.setX(pedra1.vrPosition.getX()-10);

            //reciclagem da pedra
            //se a pedra passar 10pixel da linha de visualização da tela, faço a pedra voltar 10pixel antes da linha de visualização da tela
            if (pedra1.vrPosition.getX()== -10){
                pedra1.vrPosition.setX(AGScreenManager.iScreenWidth+10);
            }

            //movimento do cenario
            cenario.vrPosition.setX(cenario.vrPosition.getX()-10);
            //reciclagem do cenario
            if (cenario.vrPosition.getX()== - AGScreenManager.iScreenWidth/2){
                cenario.vrPosition.setX(AGScreenManager.iScreenWidth*1.5f);
            }


            //Função que realiza o pulo do boneco
            if(AGInputManager.vrTouchEvents.screenClicked()){

                boneco.setCurrentAnimation(0);
                //Posicionando o boneco na posição de subida, se ele estiver no meio da tela e se não estiver descendo
                if(boneco.vrPosition.getX()==AGScreenManager.iScreenWidth /2 && desce==false){
                    //retorna uma um pixel do meio da tela, para que o boneco suba
                    boneco.vrPosition.setX(boneco.vrPosition.getX()-1);
                    sobe = true;
                    desce = false;

                    AGSoundManager.vrSoundEffects.play(sonPulo);
                    i =0;
                }

                return;


            }

            //Verificando altura maxima do pulo, se boneco chegar na altura maxima, posiciona para descida
            if(boneco.vrPosition.getY() >= alturaPulo){

                i++;

                if (i==5) {
                    //Volrando o Boneco para o meio da tela, posição inical
                    boneco.vrPosition.setX(boneco.vrPosition.getX()+1);

                    desce =true;
                }
                sobe = false;

            }

            // Se o boneco tiver, no meio da tela - 1 pixel e entre o piso e a altura maxima ele sobe
            if(boneco.vrPosition.getX()==(AGScreenManager.iScreenWidth/2)-1 && boneco.vrPosition.getY() >= alturaPiso && boneco.vrPosition.getY() < alturaPulo){
                desce=false;
                sobe=true;
                //Se boneco estiver no meio da tela e acima do piso ele desce
            }else if(boneco.vrPosition.getX()==(AGScreenManager.iScreenWidth/2) && boneco.vrPosition.getY() > alturaPiso){
                desce=true;
                sobe =false;

                //Se o boneco estiver no meio da tela e posição de corrida, para de pular
            }else if(boneco.vrPosition.getX()==AGScreenManager.iScreenWidth/2 && boneco.vrPosition.getY() <= alturaPiso){
                desce=false;
                sobe=false;

            }
            //if s que fazem de velocidade que fazem subir e descer na posição Y incrementando e decrmentando
            if(sobe==true){
                boneco.vrPosition.setY(boneco.vrPosition.getY()+30);
            }
            if(desce==true){

                boneco.vrPosition.setY(boneco.vrPosition.getY()-27);
            }

        }

        //COLISÃO
        if(boneco.vrPosition.getY() <= alturaPiso+10 && boneco.vrPosition.getX() == pedra1.vrPosition.getX() ){

            gameOver.vrPosition.setXY(AGScreenManager.iScreenWidth/2,alturaPulo);
            fim=true;

        }


    }



}

