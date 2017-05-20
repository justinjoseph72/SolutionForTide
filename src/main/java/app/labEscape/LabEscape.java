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
        traverse(startingPoint);
        return finalMaze;
    }

    /**
     * This method will have the logic to traverse the maze
     * @param startingPoint
     */
    private static void traverse(Point startingPoint) {
        initalArray[startingPoint.getX()][startingPoint.getY()] = PATH;
        List<Point> horizontalPaths = startingPoint.getFreeHorizontalPaths();
        //first traverse horizontally  then traverse vertically
        if(horizontalPaths!=null && !horizontalPaths.isEmpty()){
            //traverse Horizontaly
            for(Point freePoint: horizontalPaths){
                initalArray[freePoint.getX()][freePoint.getY()] = 'X';
                updatePointPaths(freePoint);
                if(freePoint.getFreeHorizontalPaths().isEmpty()){
                    break;
                }
                traverse(freePoint);
            }
        }
        // traverse vertically
            List<Point> verticalPaths = startingPoint.getFreeVerticalPaths();
            if(verticalPaths!=null && !verticalPaths.isEmpty()){
                for(Point freePoint: verticalPaths){
                    initalArray[freePoint.getX()][freePoint.getX()] = 'X';
                    updatePointPaths(freePoint);
                    if(freePoint.getFreeVerticalPaths().isEmpty()){
                        break;
                    }
                    traverse(freePoint);
                }
            }

    }

    /**
     * This method will update the horizontal free path and vertical free paths for a given point
     * @param point
     */
    private static void updatePointPaths(Point point) {
        updateFreeVerticalPaths(point);
        updateHorizontalPaths(point);
    }

    /**
     * This method will create a list of points which has empty and lateral to the point
     * @param point
     * @return
     */
    private static void updateHorizontalPaths(Point point) {
        List<Point> freePoints = new ArrayList<>();
        if(point.getY()>0){
            for(int i=point.getY()-1;i>=0;i--){
                if(initalArray[point.getX()][i] == FREE){
                    freePoints.add(new Point(point.getX(),i));
                }
                if(initalArray[point.getX()][i] == WALL){
                    break;
                }
            }
            for(int i=point.getY()+1;i<COLOUMN;i++){
                if(initalArray[point.getX()][i] == FREE){
                    freePoints.add(new Point(point.getX(),i));
                }
                if(initalArray[point.getX()][i] == WALL){
                    break;
                }
            }
        }
        point.setFreeHorizontalPaths(freePoints);

    }

    /**
     * This method will create a list of points which has empty and vertical to the point
     * @param point
     * @return
     */
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
