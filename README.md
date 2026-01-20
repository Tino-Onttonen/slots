# 🎰 3×3 Slot Machine created with Java

This project is a simple 3×3 slotmachine written in Java.  
It simulates a classic slot machine grid with three rows and three columns and randomly generates symbols.  
This game includes win detection and a high-scores table.

This game cannot be beat as the casino always wins, but you can give it a try!

## How It Works

- The slot machine consists of a 3×3 grid.
- Each cell contains a randomly selected symbol.
- After each spin, the grid is evaluated for winning patterns.
- Common winning conditions include:
    - Matching symbols in a horizontal row.
    - Matching symbols in a vertical column.
    - Matching symbols on diagonals.
<br>
Symbols are represented as strings.

### Features

- Randomized slot spins.
- Configurable symbol set.
- Win detection logic.
- Console-based output (or GUI, once i get there).
- Clean, easily readable code.

## How to Run

1.  Clone or download the repository.
2.  Open the project in your preferred IDE / Console.
3.  Compile, and run the project with the following command:
```` java
javac *.java && java Slots
```

Example Output:  
[ 🍒 | 🍋 | 🔔 ]  
[ 🍓 | 🍓 | 🍓 ]  
[ 🍋 | 🍒 | 🍋 ]  
<br>
🍓: Pays out: 12.5

### Concepts demonstrated:
-   2D arrays
-   Random number generation
-   Conditional logic
-   Loops
-   Basic game state evaluation
-   Swing library for GUI (Eventually)

### Possible Improvements

-   Add custom betting system
-   Add sound effects or animations
-   Create a GUI using JavaFX or Swing
-   Allow configurable grid size (beyond 3×3)

### License  

MIT License

Copyright (c) 2026 Tino-Onttonen

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.