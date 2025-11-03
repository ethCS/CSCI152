<p align="center"><em>Simulate and solve a Rubik's Cube from your terminal!</em></p>

<p align="center">
  <img src="https://preview.free3d.com/img/2018/04/2269232928544261296/4cnyxa60.jpg" alt="Rubik's Cube" width="400"/>
</p>

# Rubik's Cube Simulator | Java CLI

A console-based **Rubik's Cube** simulator implemented in Java. This project allows users to manipulate a 3x3 cube via command-line input, with full support for standard moves, prime moves, and random scrambles.  

---

## Features

- **Full Move Support:** Implements all standard moves (`u`, `d`, `r`, `l`, `f`, `b`) and their prime counterparts (`u'`, `d'`, etc.).
- **Random Scramble:** Use the `rng` switch to generate a random scramble sequence.
- **Undo Solution:** Automatically prints the reverse move sequence for any manually entered move series.
- **Error Handling:** Robust CLI input validation, with guidance for prime move syntax.
- **Clear Cube Visualization:** Prints each face of the cube in a readable format for the console.
- **Algorithmic Focus:** Perfect for studying arrays, rotation logic, and cube-solving mechanics.

---

## Getting Started

### Compilation

Compile both files:

```bash
javac RubiksCube.java App.java



Run the program via App.java:
    java App <move1> <move2> ... <moveN>

    Example:
        java App u r f d l

    Prime moves (') must be entered with backslash in CLI or as string literals:
    java App u\' r\' f\'

    Random Scramble:
        java App rng

    Example Usage:
        java App u r f d l

        Output will display the cube after each move and print the undo sequence: "Undo solution: l' d' f' r' u'"

        java App rng
        Output: Your cube has been scrambled!!!!!

