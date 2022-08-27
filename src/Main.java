public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");

        do {
            Game g = new Game();
            g.Play();
        } while (Game.RestartQuestion());

    }
}