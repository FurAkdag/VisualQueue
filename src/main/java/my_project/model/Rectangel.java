package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;

public class Rectangel extends GraphicalObject implements VisualQueue.Animatible {

    private boolean isArrived;
    private boolean fadingIn;
    private double tX;
    private double tY;

    public Rectangel(double width, double height){
        this.width = width;
        this.height = height;
    }

    /**
     * Selbsterklärend: zeichnet den QueueBall. Wird vom Framework automatisch aufgerufen (jede Frame 1x).
     */
    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(Color.RED);
        drawTool.drawFilledRectangle(x,y,width,height);
    }

    /**
     * Wird mit jeder Frame vom Framework aufgerufen und dient zur Manipulation des Objekts im Verlauf
     * der Zeit.
     * @param dt die Sekunden, die seit dem letzten Aufruf von update vergangen sind
     */
    @Override
    public void update(double dt){
        if(fadingIn){
            if(x < tX) x += 50*dt;
            if(x > tX) x -= 50*dt;
            if(y < tY) y += 50*dt;
            if(x > tX - 5) isArrived = true;
        }
    }

    @Override
    public void fadeIn() {
        fadingIn = true;
        tX = x;
        tY = y;
        x = tX-100;
        y = tY - 50;
    }

    @Override
    public void fadeOut(boolean fadeOut) {

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
    public void setTx(double tx) {
        this.tX = tx;
    }


    @Override
    public boolean isArrived() {
        return isArrived;
    }

}

