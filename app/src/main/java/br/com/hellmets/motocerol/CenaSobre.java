package br.com.hellmets.motocerol;

import br.com.hellmets.motocerol.AndGraph.AGGameManager;
import br.com.hellmets.motocerol.AndGraph.AGInputManager;
import br.com.hellmets.motocerol.AndGraph.AGScene;
import br.com.hellmets.motocerol.AndGraph.AGScreenManager;
import br.com.hellmets.motocerol.AndGraph.AGSprite;

/**
 * Created by Ascom_Unitins on 09/12/2017.
 */

public class CenaSobre extends AGScene  {

    AGSprite sobre = null;

    /*******************************************
     * Name: CAGScene()
     * Description: Scene construtor
     * Parameters: CAGameManager
     * Returns: none
     *****************************************
     * @param pManager*/
    public CenaSobre(AGGameManager pManager) {
        super(pManager);
    }


    @Override
    public void init() {

        sobre = createSprite(R.mipmap.sobre, 1,1);
        sobre.setScreenPercent(100,100);
        sobre.vrPosition.setXY(AGScreenManager.iScreenWidth/2, AGScreenManager.iScreenHeight/2);

    }

    @Override
    public void restart() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {

        //Evento que ativa o botão retorna, fazendo que retorne para sa scena de menu
        if (AGInputManager.vrTouchEvents.backButtonClicked()){
            this.vrGameManager.setCurrentScene(0);
            return;
        }

    }
}
