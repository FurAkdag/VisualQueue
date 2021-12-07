package my_project.model;

public interface Animatible {

    void fadeIn();

    void setNewPosition(int x, int y);

    void setTx(double tx);
    void setTy(double ty);
    double getTx();
    double getTy();

    boolean isArrived();

}
