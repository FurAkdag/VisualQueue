package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

public class VisualQueue<T extends GraphicalObject & Animatible> {


    /* --------- Anfang der privaten inneren Klasse -------------- */

    private class QueueNode extends GraphicalObject{

        private T content = null;
        private QueueNode nextNode = null;

        /**
         * Ein neues Objekt vom Typ QueueNode<ContentType> wird erschaffen.
         * Der Inhalt wird per Parameter gesetzt. Der Verweis ist leer.
         *
         * @param pContent das Inhaltselement des Knotens vom Typ ContentType
         */
        public QueueNode(T pContent) {
            content = pContent;
            nextNode = null;
        }

        /**
         * Der Verweis wird auf das Objekt, das als Parameter uebergeben wird,
         * gesetzt.
         *
         * @param pNext der Nachfolger des Knotens
         */
        public void setNext(QueueNode pNext) {
            nextNode = pNext;
        }

        /**
         * Liefert das naechste Element des aktuellen Knotens.
         *
         * @return das Objekt vom Typ QueueNode, auf das der aktuelle Verweis zeigt
         */
        public QueueNode getNext() {
            return nextNode;
        }

        /**
         * Liefert das Inhaltsobjekt des Knotens vom Typ ContentType.
         *
         * @return das Inhaltsobjekt des Knotens
         */
        public T getContent() {
            return content;
        }

        @Override
        public void draw(DrawTool drawTool) {
            if(content != null) {
                content.draw(drawTool);
            }
        }
    }

    /* ----------- Ende der privaten inneren Klasse -------------- */

    private QueueNode head;
    private QueueNode tail;
    private ViewController viewController;

    /**
     * Eine leere Schlange wird erzeugt.
     * Objekte, die in dieser Schlange verwaltet werden, muessen vom Typ
     * ContentType sein.
     */
    public VisualQueue(ViewController viewController) {
        head = null;
        tail = null;
        this.viewController = viewController;
    }

    /**
     * Die Anfrage liefert den Wert true, wenn die Schlange keine Objekte enthaelt,
     * sonst liefert sie den Wert false.
     *
     * @return true, falls die Schlange leer ist, sonst false
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Das Objekt pContentType wird an die Schlange angehaengt.
     * Falls pContentType gleich null ist, bleibt die Schlange unveraendert.
     *
     * @param pContent
     *            das anzuhaengende Objekt vom Typ ContentType
     */
    public void enqueue(T pContent) {
        if (pContent != null) {
            QueueNode newNode = new QueueNode(pContent);
            viewController.draw(newNode);
            if (this.isEmpty()) {
                head = newNode;
                tail = newNode;
            } else {
                tail.setNext(newNode);
                tail = newNode;
            }
        }
    }

    /**
     * Das erste Objekt wird aus der Schlange entfernt.
     * Falls die Schlange leer ist, wird sie nicht veraendert.
     */
    public void dequeue() {
        if (!this.isEmpty()) {
            viewController.removeDrawable(head);
            head = head.getNext();
            if (this.isEmpty()) {
                head = null;
                tail = null;
            }
        }
    }

    /**
     * Die Anfrage liefert das erste Objekt der Schlange.
     * Die Schlange bleibt unveraendert.
     * Falls die Schlange leer ist, wird null zurueckgegeben.
     *
     * @return das erste Objekt der Schlange vom Typ ContentType oder null,
     *         falls die Schlange leer ist
     */
    public T front() {
        if (this.isEmpty()) {
            return null;
        } else {
            return head.getContent();
        }
    }


}

