package app.labEscape;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justin on 20/05/2017.
 */
public class LabEscape {

    private static final char WALL = 'O';
    private static final char FREE = ' ';
    private static final char PATH = '•';

    private static int ROW =0;
    private  static int COLOUMN =0;
    private static char[][] initalArray;

    /**
     * @param labyrinth A labyrinth drawn on a matrix of characters. It's at least 4x4, can be a rectangle or a square.
     *                  Walkable areas are represented with a space character, walls are represented with a big O character.
     *                  The escape point is always on the border (see README)
     * @param startX    Starting row number for the escape. 0 based.
     * @param startY    Starting column number for the escape. 0 based.
     * @return          A char matrix with the same labyrinth and a path drawn from the starting point to the escape
     * @throws          NoEscapeException when no path exists to the outside, from the selected starting point
     */
    public static char[][] drawPathForEscape(char[][] labyrinth, int startX, int startY) throws NoEscapeException {
        initalArray = labyrinth;
        char[][] finalMaze = labyrinth;
        ROW = labyrinth.length;
        COLOUMN = labyrinth[0].length;
        Point startingPoint = new Point(startX,startY);
        updatePointPaths(startingPoint);
        return finalMaze;
    }

    private static void updatePointPaths(Point startingPoint) {
        updateFreeVerticalPaths(startingPoint);
        updateHorizontalPaths(startingPoint);
    }

    /**
     * This method will create a list of points which has empty surrounding the point
     * @param startingPoint
     * @return
     */
    private static void updateHorizontalPaths(Point startingPoint) {
        List<Point> freePoints = new ArrayList<>();
        if(startingPoint.getY()>0){
            for(int i=startingPoint.getY()-1;i>=0;i--){
                if(initalArray[startingPoint.getX()][i] == FREE){
                    freePoints.add(new Point(startingPoint.getX(),i));
                }
                if(initalArray[startingPoint.getX()][i] == WALL){
                    break;
                }
            }
            for(int i=startingPoint.getY()+1;i<COLOUMN;i++){
                if(initalArray[startingPoint.getX()][i] == FREE){
                    freePoints.add(new Point(startingPoint.getX(),i));
                }
                if(initalArray[startingPoint.getX()][i] == WALL){
                    break;
                }
            }
        }
        startingPoint.setFreeHorizontalPaths(freePoints);

    }

    private static void  updateFreeVerticalPaths(Point startingPoint){
        List<Point> freePoints = new ArrayList<>();
        if(startingPoint.getX()>0){
            for(int i= startingPoint.getX()-1;i>=0;i--){
            if(initalArray[i][startingPoint.getY()]==FREE){
                freePoints.add(new Point(i,startingPoint.getY()));
            }
            if(initalArray[i][startingPoint.getY()]==WALL){
                break;
            }
            }
            for(int i=startingPoint.getX()+1;i<ROW;i++){
                if(initalArray[i][startingPoint.getY()]==FREE){
                    freePoints.add(new Point(i,startingPoint.getY()));
                }
                if(initalArray[i][startingPoint.getY()]==WALL){
                    break;
                }
            }
        }
        startingPoint.setFreeVerticalPaths(freePoints);

    }


}
