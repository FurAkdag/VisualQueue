package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.abitur.datenstrukturen.Queue;

public class VisualQueue<T extends GraphicalObject & Animatible> {

    private final Queue<T> queue;
    private ViewController viewController;
    private double posX;
    private double posY;
    private T previouse;

    public VisualQueue(ViewController viewController, double posX, double posY){
        this.posX = posX;
        this.posY = posY;
        previouse = null;
        queue = new Queue<>();
        this.viewController = viewController;
    }

    public void enqueue(T content){
        if(!queue.isEmpty()){
           queue.enqueue(content);
           if(content != null) {
               if(content.getRadius() == 0){
                   content.setY(posY);
                   content.setX(posX);
                   posX += content.getWidth();
               }else{
                   posX += content.getRadius();
                   content.setY(posY);
                   content.setX(posX);
                   posX += content.getRadius();
               }
           }
        }else{
            queue.enqueue(content);
            content.setY(posY);
            content.setX(posX);
            if(content.getRadius() == 0){
                posX += content.getWidth();
            }else{
                posX += content.getRadius();
            }
        }

        if(content != null){
            viewController.draw(content);
            content.fadeIn();
        }
    }

    public void dequeue(){
        if(!queue.isEmpty()){
            viewController.removeDrawable(queue.front());
            queue.dequeue();
            if(queue.isEmpty()){
                posX = 200;
                posY = 200;
            }
        }

    }

    public void front(){
        queue.front();
    }

    //Extra Methoden

    public void fadeIn(){
        if(queue.isEmpty()){
            queue.front().fadeIn();
        }
    }

    public void fadeOut(){
        if(queue.isEmpty()){
            queue.front().fadeOut();
        }
    }

    public void changeSize(double size){
        if(queue.isEmpty()){
            queue.front().changeSize(size);
        }
    }



}
