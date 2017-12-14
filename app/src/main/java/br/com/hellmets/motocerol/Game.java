//Aplication package
package br.com.hellmets.motocerol;

//Used Packages
import android.os.Bundle;
import br.com.hellmets.motocerol.AndGraph.AGActivityGame;
import br.com.hellmets.motocerol.AndGraph.AGGameManager;


public class Game extends AGActivityGame
{

    public void onCreate(Bundle saved)
    {

        super.onCreate(saved);

        //(this=esta tela, false= não utilizar acelelometro)
        this.vrManager = new AGGameManager(this, false);

        //referencia da scena
        CenaAbertura vrAbertura = new CenaAbertura(vrManager);
        //add scena de abertura no GameManager. a cena tem identificador de acordo com sua chammada.
        vrManager.addScene(vrAbertura);

        //referencia da scena
        CenaPlay vrPlay = new CenaPlay(vrManager);
        //add scena de abertura no GameManager. a cena tem identificador de acordo com sua chammada.
        vrManager.addScene(vrPlay);


        //referencia da scena
        CenaSobre vrSobre = new CenaSobre(vrManager);
        //add scena de sobre no GameManager. a cena tem identificador de acordo com sua chammada.
        vrManager.addScene(vrSobre);




    }
}
