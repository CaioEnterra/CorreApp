package br.com.hellmets.motocerol;

import android.view.View;
import android.widget.Button;

import br.com.hellmets.motocerol.AndGraph.AGGameManager;
import br.com.hellmets.motocerol.AndGraph.AGInputManager;
import br.com.hellmets.motocerol.AndGraph.AGMusic;
import br.com.hellmets.motocerol.AndGraph.AGScene;
import br.com.hellmets.motocerol.AndGraph.AGScreenManager;
import br.com.hellmets.motocerol.AndGraph.AGSoundManager;
import br.com.hellmets.motocerol.AndGraph.AGSprite;
import br.com.hellmets.motocerol.AndGraph.AGTimer;

/**
 * Created by labmacmini18 on 16/11/17.
 */

public class CenaAbertura extends AGScene {


    //atributo de classe
    AGTimer tempo = null;
    AGSprite botao = null;
    AGSprite sobre = null;
    AGSprite fundo = null;
    AGSoundManager musica = null;


    CenaAbertura(AGGameManager vrManager){

        super(vrManager);
    }


    @Override
    public void init() {

        musica = new AGSoundManager();
        musica.vrMusic.loadMusic("pokemon.mp3", true);
        musica.vrMusic.play();
        setSceneBackgroundColor(1,0,0);
        tempo = new AGTimer(4000);


        fundo = this.createSprite( R.mipmap.fundomenu,1,1);
        fundo.setScreenPercent(100,100);
        fundo.vrPosition.setXY(AGScreenManager.iScreenWidth/2, AGScreenManager.iScreenHeight/2);




        botao = this.createSprite( R.mipmap.play,1,1);
        botao.setScreenPercent(40,20);
        botao.vrPosition.setX(AGScreenManager.iScreenWidth/2);
        botao.vrPosition.setY(AGScreenManager.iScreenHeight*0.33f);

        sobre = this.createSprite( R.mipmap.sobrebotao,1,1);
        sobre.setScreenPercent(40,20);
        sobre.vrPosition.setXY(AGScreenManager.iScreenWidth/2, AGScreenManager.iScreenHeight*0.66f);


    }

    @Override
    public void restart() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {

        if(AGInputManager.vrTouchEvents.screenClicked()){



            if(botao.collide(AGInputManager.vrTouchEvents.getLastPosition())){

                this.vrGameManager.setCurrentScene(1);
                return;
            }


            if(sobre.collide(AGInputManager.vrTouchEvents.getLastPosition())){

                this.vrGameManager.setCurrentScene(2);
                return;
            }
        }



    }

}
