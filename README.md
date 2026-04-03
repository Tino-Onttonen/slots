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
``` java
javac *.java && java Slots
```

Example Output:  
![Screenshot](/snippet.png)



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
-   Create a GUI using JavaFX or Swing - Done with Swing
-   Allow configurable grid size (beyond 3×3) 

[![wakatime](https://wakatime.com/badge/user/c2147304-0fdf-4d03-964c-b3803d6fedb5/project/215e6aee-cc27-4b7e-a6d8-832d0f306317.svg)](https://wakatime.com/badge/user/c2147304-0fdf-4d03-964c-b3803d6fedb5/project/215e6aee-cc27-4b7e-a6d8-832d0f306317)
