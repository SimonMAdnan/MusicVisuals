package ie.tudublin;

import C22440562.DriftVeilCity;

public class Main {

    public void startUI() {
        String[] a = { "MAIN" };
        processing.core.PApplet.runSketch(a, new DriftVeilCity());
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.startUI();
    }
}