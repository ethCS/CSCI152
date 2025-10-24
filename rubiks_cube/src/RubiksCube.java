public class RubiksCube {
    // Each face has its own array
    private char[][] red_face = this.create_rubiks_cube_face('r');
    private char[][] blue_face = this.create_rubiks_cube_face('b');
    private char[][] orange_face = this.create_rubiks_cube_face('o');
    private char[][] green_face = this.create_rubiks_cube_face('g');
    private char[][] yellow_face = this.create_rubiks_cube_face('y');
    private char[][] white_face = this.create_rubiks_cube_face('w');

    public void print() {
        this.print_rubiks_cube_face(this.red_face);
        this.print_rubiks_cube_face(this.blue_face);
        this.print_rubiks_cube_face(this.orange_face);
        this.print_rubiks_cube_face(this.green_face);
        this.print_rubiks_cube_face(this.yellow_face);
        this.print_rubiks_cube_face(this.white_face);
    }

    public void move(String arg) {
        char[] temp_b;
        char[] temp_r;
        char[] temp_g;
        char[] temp_o;
        char[] temp_y;
        char[] temp_w;

        switch (arg) {
            case "u":
                this.right_turn(this.red_face);
                temp_y = this.get_row(this.yellow_face, 0);
                temp_g = this.get_row(this.green_face, 0);
                temp_w = this.get_row(this.white_face, 0);
                temp_b = this.get_row(this.blue_face, 0);

                this.set_row(this.yellow_face, 0, temp_b);
                this.set_row(this.green_face, 0, this.reverse(temp_y));
                this.set_row(this.white_face, 0, this.reverse(temp_g));
                this.set_row(this.blue_face, 0, temp_w);
                break;

            case "d":
                this.right_turn(this.orange_face);
                temp_y = this.get_row(this.yellow_face, 2);
                temp_g = this.get_row(this.green_face,2);
                temp_w = this.get_row(this.white_face,2);
                temp_b = this.get_row(this.blue_face, 2);

                this.set_row(this.yellow_face, 2, this.reverse(temp_g));
                this.set_row(this.green_face, 2, this.reverse(temp_w));
                this.set_row(this.white_face, 2, temp_b);
                this.set_row(this.blue_face, 2, temp_y);
                break;

            case "r":
                this.right_turn(this.white_face);
                temp_b = this.get_column(this.blue_face, 2);
                temp_r = this.get_column(this.red_face, 2);
                temp_g = this.get_column(this.green_face, 0); //THIS IS THE REAR FROM LUTHER'S PERSPECTIVE SO ITS GONNA BE BACKWARDS!!
                temp_o = this.get_column(this.orange_face, 2);

                // from red perspective, as luther has it, green is flipped (col index of 0)
                // and green 'slice' is reversed from normal perspective
                // we need to re-reverse the green slice when assigning it to orange slice

                this.set_column(this.blue_face,2, temp_o);
                this.set_column(this.red_face,2, temp_b);
                this.set_column(this.green_face, 0, this.reverse(temp_r)); //backwards
                this.set_column(this.orange_face,2, this.reverse(temp_g));
                break;
            case "l":
                this.right_turn(this.yellow_face);
                temp_b = this.get_column(this.blue_face, 0);
                temp_r = this.get_column(this.red_face, 0);
                temp_g = this.get_column(this.green_face, 2);
                temp_o = this.get_column(this.orange_face, 0);

                this.set_column(this.blue_face,0, temp_r);
                this.set_column(this.red_face,0, this.reverse(temp_g)); // reverse perspective means that it'll be affecting red instead of orange
                this.set_column(this.green_face, 2, this.reverse(temp_o));
                this.set_column(this.orange_face,0, temp_b);
                break;
            case "f":
                this.right_turn(this.blue_face);
                
                break;

            case "b":
                this.right_turn(this.green_face);

                break;

            case "u'":
                this.left_turn(this.red_face);
                temp_y = this.get_row(this.yellow_face, 0);
                temp_g = this.get_row(this.green_face,0);
                temp_w = this.get_row(this.white_face,0);
                temp_b = this.get_row(this.blue_face, 0);

                this.set_row(this.yellow_face, 0, this.reverse(temp_g));
                this.set_row(this.green_face, 0, this.reverse(temp_w));
                this.set_row(this.white_face, 0, temp_b);
                this.set_row(this.blue_face, 0, temp_y);
                break;

            case "d'":
                this.left_turn(this.orange_face);
                temp_y = this.get_row(this.yellow_face, 2);
                temp_g = this.get_row(this.green_face,2);
                temp_w = this.get_row(this.white_face,2);
                temp_b = this.get_row(this.blue_face, 2);

                this.set_row(this.yellow_face, 2, temp_b);
                this.set_row(this.green_face, 2, this.reverse(temp_y));
                this.set_row(this.white_face, 2, this.reverse(temp_g));
                this.set_row(this.blue_face, 2, temp_w);
                break;

            case "r'":
                this.left_turn(this.white_face);
                temp_b = this.get_column(this.blue_face, 2);
                temp_r = this.get_column(this.red_face, 2);
                temp_g = this.get_column(this.green_face, 0);
                temp_o = this.get_column(this.orange_face, 2);

                this.set_column(this.blue_face,2, temp_r);
                this.set_column(this.red_face,2, this.reverse(temp_g)); // reverse perspective means that it'll be affecting red instead of orange
                this.set_column(this.green_face, 0, this.reverse(temp_o));
                this.set_column(this.orange_face,2, temp_b);
                break;

            case "l'":
                this.left_turn(this.yellow_face);
                temp_b = this.get_column(this.blue_face, 0);
                temp_r = this.get_column(this.red_face, 0);
                temp_g = this.get_column(this.green_face, 2); 
                temp_o = this.get_column(this.orange_face, 0);

                this.set_column(this.blue_face,0, temp_o);
                this.set_column(this.red_face,0, temp_b);
                this.set_column(this.green_face, 2, this.reverse(temp_r)); 
                this.set_column(this.orange_face,0, this.reverse(temp_g));
                break;

            case "f'":
                this.left_turn(this.blue_face);
                break;

            case "b'":
                this.left_turn(this.green_face);
                break;

            default:
                System.out.println("Invalid input/syntax for attempted cube move");
                System.out.println("Please use: (u, d, r, l, f, b) or (u', d', r', l', f', b') ");
                break;
        }
    }

    private char[] reverse(char[] temp_col) {
        return new char[] {temp_col[2], temp_col[1], temp_col[0]};
    }

    private void set_row(char[][] face_array, int row_index, char[] preferred_color) {
        face_array[row_index][0] = preferred_color[0];
        face_array[row_index][1] = preferred_color[1];
        face_array[row_index][2] = preferred_color[2];
    }

    private void set_column(char[][] face_array, int col_index, char[] preferred_color) {
        face_array[0][col_index] = preferred_color[0];
        face_array[1][col_index] = preferred_color[1];
        face_array[2][col_index] = preferred_color[2];
    }

    private char[] get_column(char[][] face_array, int index) {
        return new char[] {face_array[0][index], face_array[1][index], face_array[2][index]};
    }

    private char[] get_row(char[][] face_array, int index) {
        return new char[] {face_array[index][0], face_array[index][1], face_array[index][2]};
    }

    private char[][] create_rubiks_cube_face(char face_value) {
        char[][] face_to_return = new char[3][3];

        for(int i = 0; i < face_to_return.length; i++) {
            for(int j = 0; j < face_to_return.length; j++) {
                face_to_return[i][j] = face_value;
            }
        }
        return face_to_return;
    }

    private void print_rubiks_cube_face(char[][] return_face) {
        for(int i = 0; i < return_face.length; i++) {
            for(int j = 0; j < return_face.length; j++) {
                System.out.print(return_face[i][j]);
                if (j % 2 != 0 || j == 0) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();
    }

    private char[][] array_copier(char[][] original_array) {
        char[][] array_copy = new char[3][3];
        for(int i=0; i<original_array.length; i++) {
            for(int j=0; j<original_array[i].length; j++) {
                array_copy[i][j] = original_array[i][j];
            }
        }
        return array_copy;
    }

    private void right_turn(char[][] any_face) {
        char[][] copied_array = this.array_copier(any_face);

        any_face[0][2] = copied_array[0][0];
        any_face[1][2] = copied_array[0][1];
        any_face[2][2] = copied_array[0][2];
        any_face[2][1] = copied_array[1][2];
        any_face[2][0] = copied_array[2][2];
        any_face[0][0] = copied_array[2][0];
        any_face[0][1] = copied_array[1][0];
    }

    private void left_turn(char[][] any_face) {
        char[][] copied_array = this.array_copier(any_face);

        any_face[0][0] = copied_array[0][2];
        any_face[0][1] = copied_array[1][2];
        any_face[0][2] = copied_array[2][2];
        any_face[1][2] = copied_array[2][1];
        any_face[2][2] = copied_array[2][0];
        any_face[2][0] = copied_array[0][0];
        any_face[1][0] = copied_array[0][1];
    }
}