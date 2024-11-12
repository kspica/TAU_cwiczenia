package org.example;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @BeforeEach
    public void setUp() {
        Game.initializeBoard();
    }

    @Test
    public void testInitializeBoard_PlayerStartsAtTopLeft() {
        assertEquals(Game.PLAYER, Game.board[0][0], "Player should start at position (0, 0)");
    }

    @Test
    public void testInitializeBoard_AllOtherCellsAreEmpty() {
        for (int i = 0; i < Game.SIZE; i++) {
            for (int j = 0; j < Game.SIZE; j++) {
                if (i != 0 || j != 0) {
                    assertEquals(Game.EMPTY, Game.board[i][j], "All other cells should be empty");
                }
            }
        }
    }

    @Test
    public void testPlaceGoal_GoalIsAtBottomRight() {
        Game.placeGoal();

        assertEquals(Game.GOAL, Game.board[Game.SIZE - 1][Game.SIZE - 1],
                "Goal should be placed at the bottom-right corner of the board");
    }

    @Test
    public void testPlaceObstacles_NoObstaclesOnPlayerOrGoalPosition() {
        Game.placeGoal();
        Game.placeObstacles();

        assertNotEquals(Game.OBSTACLE, Game.board[0][0], "Player's starting position should not have an obstacle");
        assertNotEquals(Game.OBSTACLE, Game.board[Game.SIZE - 1][Game.SIZE - 1],
                "Goal position should not have an obstacle");
    }

    @Test
    public void testIsValidMove_BoundaryConditions() {
        assertFalse(Game.isValidMove(0, -1), "Move should be invalid (outside left boundary)");
        assertFalse(Game.isValidMove(-1, 0), "Move should be invalid (outside top boundary)");
        assertFalse(Game.isValidMove(0, Game.SIZE), "Move should be invalid (outside right boundary)");
        assertFalse(Game.isValidMove(Game.SIZE, 0), "Move should be invalid (outside bottom boundary)");
    }

    @Test
    public void testIsValidMove_ObstaclePosition() {
        Game.board[1][1] = Game.OBSTACLE;
        assertFalse(Game.isValidMove(1, 1), "Move should be invalid (obstacle at target position)");
    }

    @Test
    public void testIsValidMove_EmptyPosition() {
        assertTrue(Game.isValidMove(0, 1), "Move should be valid (empty position at target)");
    }

    @Test
    public void testIsValidMove_GoalPosition() {
        Game.board[2][2] = Game.GOAL;
        assertTrue(Game.isValidMove(2, 2), "Move should be valid (goal position)");
    }

}
