# Tic Tac Toe Game

## **Overview**
A console-based Tic Tac Toe game featuring three modes:  
- **Player vs Easy AI**  
- **Player vs Hard AI**  
- **Player vs Player**  

Players can make moves on a 3x3 grid, aiming to align three symbols (horizontal, vertical, or diagonal) to win.

---

## **Features**
- **Dynamic Board Display**: Updates after each move.  
- **Input Validation**: Ensures valid cell entries (1-9).  
- **Replay Option**: Restart after game conclusion.  
- **Smart AI**: In Hard Mode, the AI prioritizes winning and blocking moves.  

---

## **How to Play**
1. **Start the Game**  
   - Choose `1` for AI or `2` for Player vs Player mode.  

2. **Make a Move**  
   - Enter a cell number (1-9) corresponding to this layout:  
     ```
     7 | 8 | 9
     --+---+--
     4 | 5 | 6
     --+---+--
     1 | 2 | 3
     ```

3. **Win Conditions**  
   - Align three symbols to win.  

4. **Replay or Quit**  
   - After a game ends, choose to replay or exit.

---

## **Technical Details**
- **Game Logic**:  
  - Winning conditions are predefined as 2D arrays.  
  - AI in Hard Mode evaluates moves to win or block.

- **Key Methods**:  
  - `playSinglePlayerEasy()`  
  - `playSinglePlayerHard()`  
  - `playerTurn()`, `computerTurnEasyMode()`, `computerTurnHardMode()`

- **Project Class**:  
  - `gr.georgedurieux.TicTacToe`

---

## **Technologies**
- **Language**: Java  
- **Development Tools**:  
  - Java Development Kit (JDK)  
  - Command-line Interface (CLI)  
- **Core Concepts**:  
  - Object-Oriented Programming (OOP)  
  - Console-based Input/Output  
  - AI Implementation with Randomization and Strategy

## License

This project is licensed under the MIT License.

