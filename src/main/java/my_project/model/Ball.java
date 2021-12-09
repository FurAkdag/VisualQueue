package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;

/**
 * Repräsentiert eine Kugel (einen Kreis), der in eine Schlange eingefügt werden soll. Dabei muss jeder QueueBall immer
 * seinen Vorgänger kennen, damit er zu ihm Abstand halten kann.
 */
public class Ball extends GraphicalObject implements Animatible {

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
        if(fadingIn){
            if (x < tX) {
                x += 50 * dt;
            }
            if (x > tX) {
                x -= 50 * dt;
            }
            if (y < tY) {
                y += 50 * dt;
            }else{
                y = tY;
            }
            if(x > tX - 5) isArrived = true;
        }

    }

    @Override
    public void fadeIn(){
        fadingIn = true;
        tX = x;
        tY = y;
        x = tX-100;
        y = tY - 50;
    }


    @Override
    public void setTx(double tx) {
        this.tX = tx;
    }



    @Override
    public boolean isArrived() {
        return isArrived;
    }

}
