package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.abitur.datenstrukturen.Queue;

public class VisualQueue<T extends GraphicalObject & VisualQueue.Animatible> {

    //Interface
    interface Animatible {

        void fadeIn();

        void fadeOut(boolean fadeOut);

        void setTx(double tx);

        void setTy(double ty);

        double getTx();

        double getTy();

        boolean isArrived();

    }

    //Ende Interface

    private Queue<T> queue;
    private ViewController viewController;
    private double posX;
    private double posY;
    private String direction;

    public VisualQueue(ViewController viewController, double posX, double posY, String direction){
        this.posX = posX;
        this.posY = posY;
        this.direction = direction;
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
                    if(direction.equals("right")) {
                        posX += content.getWidth();
                    }else if(direction.equals("up")){
                        posY -= content.getHeight();
                    }
                } else {
                    if(direction.equals("right")) {
                        posY += content.getRadius();
                    }else if(direction.equals("up")){
                        posY -= content.getRadius();
                    }
                    content.setY(posY);
                    content.setX(posX);
                    if(direction.equals("right")) {
                        posY += content.getRadius();
                    }else if(direction.equals("up")){
                        posY -= content.getRadius();
                    }
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
                queue.front().fadeOut(true);
                double radius = queue.front().getRadius();
                double width = queue.front().getWidth();
                double height = queue.front().getHeight();
                queue.dequeue();
                //Rest bewegen
                Queue<T> newQueue = new Queue<>();
                //Verzweigung, jenachdem ob deine Queue nach oben hin oder nach rechts hin vergrößert werden soll
                if(direction.equals("right")) {
                    while (!queue.isEmpty()) {
                        newQueue.enqueue(queue.front());
                        if (radius == 0) {
                            queue.front().setTx(queue.front().getTx() - width);
                        } else {
                            queue.front().setTx(queue.front().getTx() - radius * 2);
                        }
                        queue.dequeue();
                    }
                    if(radius == 0){
                        posX -= width;
                    }else {
                        posX -= radius*2;
                    }
                }else if(direction.equals("up")){
                    while (!queue.isEmpty()) {
                        newQueue.enqueue(queue.front());
                        if (radius == 0) {
                            queue.front().setTy(queue.front().getTy() + height);
                        } else {
                            queue.front().setTy(queue.front().getTy() + radius * 2);
                        }
                        queue.dequeue();
                    }
                    if(radius == 0){
                        posY += height;
                    }else {
                        posY += radius*2;
                    }
                }

                queue = newQueue;
            }
        }
    }

    public T getFront(){
        return queue.front();
    }
}
