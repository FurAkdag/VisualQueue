package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.abitur.datenstrukturen.Queue;

public class VisualQueue<T extends GraphicalObject & Animatible> {

    private final Queue<T> queue;
    private ViewController viewController;
    private double posX;
    private double posY;
    private double startX;
    private double startY;
    private T previouse;

    public VisualQueue(ViewController viewController, double posX, double posY){
        this.posX = posX;
        this.posY = posY;
        startX = posX;
        startY = posY;
        previouse = null;
        queue = new Queue<>();
        this.viewController = viewController;
    }

    public void enqueue(T content){
        if(content != null) {
            queue.enqueue(content);
            if (!queue.isEmpty()) {
                if (content.getRadius() == 0) {
                    content.setY(posY);
                    content.setX(posX);
                    posX += content.getWidth();
                } else {
                    posX += content.getRadius();
                    content.setY(posY);
                    content.setX(posX);
                    posX += content.getRadius();
                }
            } else {
                content.setY(posY);
                content.setX(posX);
                if (content.getRadius() == 0) {
                    posX += content.getWidth();
                } else {
                    posX += content.getRadius();
                }
            }
            viewController.draw(content);
            content.fadeIn();
        }
    }

    public void dequeue(){
        if(!queue.isEmpty()){
            viewController.removeDrawable(queue.front());
            queue.dequeue();
            if(queue.isEmpty()){
                posX = startX;
                posY = startY;
            }
        }

    }

    public void front(){
        queue.front();
    }

    //Extra Methoden




}
