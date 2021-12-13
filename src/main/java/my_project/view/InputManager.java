package my_project.view;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.InteractiveGraphicalObject;
import my_project.control.ProgramController;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Realisiert ein Objekt, dass alle Eingaben empfängt und dann danach passende Methoden
 * im ProgramController aufruft
 */
public class InputManager extends InteractiveGraphicalObject {

    private ProgramController programController;
    private ViewController viewController;

    /**
     * Objekterzeugung
     * @param programController Nötig als Objekt vom Controllerbereich, das informiert wird
     * @param viewController Nötig, um den Aufruf der Interface-Methoden sicherzustellen
     */
    public InputManager(ProgramController programController, ViewController viewController){
        this.programController = programController;
        this.viewController = viewController;
        viewController.register(this);

    }

    @Override
    public void keyReleased(int key){
        if (key == KeyEvent.VK_SPACE) programController.addBall();
        if (key == KeyEvent.VK_BACK_SPACE) programController.deleteBall();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            programController.addRectangel();
        }
        if(e.getButton() == MouseEvent.BUTTON3){
            programController.deleteRectangel();
        }
    }

    @Override
    public void keyPressed(int key) {
        if(key == KeyEvent.VK_UP){
            programController.moveBall("up");
        }else if(key == KeyEvent.VK_DOWN){
            programController.moveBall("down");
        }else if(key == KeyEvent.VK_RIGHT){
            programController.moveBall("right");
        } else if (key == KeyEvent.VK_LEFT) {
            programController.moveBall("left");
        }
    }
}
