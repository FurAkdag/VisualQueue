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
    private T previous;

    public VisualQueue(ViewController viewController, double posX, double posY){
        this.posX = posX;
        this.posY = posY;
        startX = posX;
        startY = posY;
        previous = null;
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
            if(queue.front().isArrived()){
                viewController.removeDrawable(queue.front());
                double radius = queue.front().getRadius();
                double width = queue.front().getWidth();
                queue.dequeue();
                //Rest bewegen
                Queue<T> newQueue = new Queue<>();
                while(!queue.isEmpty()){
                    newQueue.enqueue(queue.front());
                    if(radius == 0){
                        queue.front().setTx(queue.front().getX() - width);
                    }else {
                        queue.front().setTx(queue.front().getX() - radius * 2);
                    }
                    queue.dequeue();
                }
                if(radius == 0){
                    posX -= width;
                }else {
                    posX -= radius*2;
                }
                queue = newQueue;
            }
        }
    }
}
