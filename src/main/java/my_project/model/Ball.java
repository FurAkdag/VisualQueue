package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;

/**
 * Repräsentiert eine Kugel (einen Kreis), der in eine Schlange eingefügt werden soll. Dabei muss jeder QueueBall immer
 * seinen Vorgänger kennen, damit er zu ihm Abstand halten kann.
 */
public class Ball extends GraphicalObject implements VisualQueue.Animatible {

    /**
     * Erzeugt einen neuen QueueBall
     * @param x Startposition x
     * @param y Startposition y
     */
    private boolean fadingIn;
    private boolean isArrived;
    private double tX;
    private double tY;

    public Ball(double radius){
        this.radius = radius;
    }

    /**
     * Selbsterklärend: zeichnet den QueueBall. Wird vom Framework automatisch aufgerufen (jede Frame 1x).
     */
    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(Color.RED);
        drawTool.drawFilledCircle(x,y,radius);
    }

    /**
     * Wird mit jeder Frame vom Framework aufgerufen und dient zur Manipulation des Objekts im Verlauf
     * der Zeit.
     * @param dt die Sekunden, die seit dem letzten Aufruf von update vergangen sind
     */
    @Override
    public void update(double dt){
        x = tX;
        y = tY;
        isArrived = true;
    }

    @Override
    public void fadeIn(){

    }

    @Override
    public void fadeOut(boolean fadeOut) {

    }


    @Override
    public void setTx(double tx) {
        this.tX = tx;
    }

    @Override
    public void setTy(double ty) {
        tY = ty;
    }
    @Override
    public double getTx() {
        return tX;
    }

    @Override
    public double getTy() {
        return tY;
    }


    @Override
    public boolean isArrived() {
        return isArrived;
    }

}
