package my_project.control;

import KAGO_framework.control.ViewController;
import my_project.model.Ball;
import my_project.model.Rectangel;
import my_project.model.VisualQueue;
import my_project.view.InputManager;

/**
 * Ein Objekt der Klasse ProgramController dient dazu das Programm zu steuern. Die updateProgram - Methode wird
 * mit jeder Frame im laufenden Programm aufgerufen.
 */
public class ProgramController {

    //Attribute


    // Referenzen
    private ViewController viewController;  // diese Referenz soll auf ein Objekt der Klasse viewController zeigen. Über dieses Objekt wird das Fenster gesteuert.
    private VisualQueue<Ball> ballVisualQueue;
    private VisualQueue<Rectangel> rectangelVisualQueue;


    /**
     * Konstruktor
     * Dieser legt das Objekt der Klasse ProgramController an, das den Programmfluss steuert.
     * Damit der ProgramController auf das Fenster zugreifen kann, benötigt er eine Referenz auf das Objekt
     * der Klasse viewController. Diese wird als Parameter übergeben.
     * @param viewController das viewController-Objekt des Programms
     */
    public ProgramController(ViewController viewController){
        this.viewController = viewController;
        ballVisualQueue = new VisualQueue<>(viewController, 500,600, "up");
        rectangelVisualQueue = new VisualQueue<>(viewController, 50,200, "right");
        new InputManager(this,viewController);
    }

    /**
     * Diese Methode wird genau ein mal nach Programmstart aufgerufen.
     * Sie erstellt die leeren Datenstrukturen, zu Beginn nur eine Queue
     */
    public void startProgram() {
    }

    public void addBall(){
        Ball newBall = new Ball(20);
        ballVisualQueue.enqueue(newBall);
    }

    public void deleteBall(){
        ballVisualQueue.dequeue();
    }

    public void addRectangel(){
        Rectangel newRectangel = new Rectangel(20, 20);
        rectangelVisualQueue.enqueue(newRectangel);
    }

    public void deleteRectangel(){
        rectangelVisualQueue.dequeue();
    }


    /**
     * Aufruf mit jeder Frame
     * @param dt Zeit seit letzter Frame
     */
    public void updateProgram(double dt){

    }
}
