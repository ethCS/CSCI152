public class App {
    public void main(String[] args) throws Exception {

        RubiksCube cube = new RubiksCube();
        //cube.print();

        char[][] test = new char[3][3];
        test[0][0] = 'b';
        test[0][1] = 'b';
        test[0][2] = 'w';
        test[1][0] = 'b';
        test[1][1] = 'b';
        test[1][2] = 'w';
        test[2][0] = 'b';
        test[2][1] = 'b';
        test[2][2]= 'w';

        cube.print();

        // cube.move("z");
    }
}

