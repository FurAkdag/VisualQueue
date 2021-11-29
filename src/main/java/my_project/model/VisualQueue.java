package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.abitur.datenstrukturen.Queue;

public class VisualQueue<T extends GraphicalObject & Animatible> {

    private final Queue<T> queue;
    private ViewController viewController;

    public VisualQueue(ViewController viewController){
        queue = new Queue<>();
        this.viewController = viewController;
    }

    public void enqueue(T content){
        queue.enqueue(content);
        if(content != null) viewController.draw(content);
    }

    public void dequeue(){
        if(!queue.isEmpty()){
            viewController.removeDrawable(queue.front());
            queue.dequeue();
        }
    }

    public void front(){
        queue.front();
    }

}
