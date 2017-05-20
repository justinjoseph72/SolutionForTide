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
        ROW = labyrinth.length;
        COLOUMN = labyrinth[0].length;
        Point startingPoint = new Point(startX,startY);
        updatePointPaths(startingPoint);
        traverse(startingPoint);
        checkForPathFound();
        return initalArray;
    }

    /**
     * This method will check if there are any PATH in the boundary of the maze.
     * If it does not find a PATH in the boundary, a NoEscapeException is thrown
     * @throws NoEscapeException
     */
    private static void checkForPathFound() throws NoEscapeException {
        int counter =0;
        for(int i=0;i<ROW;i++){
            if(initalArray[i][COLOUMN-1]== PATH ||  initalArray[i][0] == PATH){
                counter++;
            }
        }
        for(int i=0;i<COLOUMN;i++){
            if(initalArray[0][i] == PATH || initalArray[ROW-1][i] == PATH){
                counter++;
            }
        }
        if(counter==0){
            throw new NoEscapeException();
        }
    }

    /**
     * This method will have the logic to traverse the maze
     * The method will not traverse if the point is in the boundary of the maze.
     * Otherwise it will first traverse the horizontal paths and then the vertical paths
     * @param point
     */
    private static void traverse(Point point) {
        initalArray[point.getX()][point.getY()] = PATH;
        // if the point lies on the bounday then return from the method
        if(checkPointAtBoundary(point)){
           return;
        }
        List<Point> horizontalPaths = point.getFreeHorizontalPaths();
        //first traverse horizontally  then traverse vertically
        if(horizontalPaths!=null && !horizontalPaths.isEmpty()){
            //traverse Horizontaly
            updateandTraversePaths(horizontalPaths);
        }
        // traverse vertically
            List<Point> verticalPaths = point.getFreeVerticalPaths();
            if(verticalPaths!=null && !verticalPaths.isEmpty()){
                updateandTraversePaths(verticalPaths);
            }

    }

    /**
     * This method will update the point to Path and
     * then will update the freePaths for the points and traverse it again
     * @param horizontalPaths
     */
    private static void updateandTraversePaths(List<Point> horizontalPaths) {
        for(Point freePoint: horizontalPaths){
            initalArray[freePoint.getX()][freePoint.getY()] = PATH;
            updatePointPaths(freePoint);
            if(freePoint.getFreeHorizontalPaths().isEmpty() && freePoint.getFreeHorizontalPaths().isEmpty()){
                break;
            }
            traverse(freePoint);
        }
    }

    /**
     * This method will check if the given point is at the boundary of the matrix or not
     * @param point
     * @return
     */
    private static boolean checkPointAtBoundary(Point point) {
        if(point.getX()==0  || point.getY()==0  || point.getY()==COLOUMN-1 || point.getX() == ROW-1){
            return true;
        }
        return false;
    }

    /**
     * This method will update the horizontal free path and vertical free paths for a given point
     * @param point
     */
    public static void updatePointPaths(Point point) {
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
                if(initalArray[point.getX()][i] == FREE || initalArray[point.getX()][i] == PATH){
                    freePoints.add(new Point(point.getX(),i));
                }
                if(initalArray[point.getX()][i] == WALL){
                    break;
                }
            }
            for(int i=point.getY()+1;i<COLOUMN;i++){
                if(initalArray[point.getX()][i] == FREE || initalArray[point.getX()][i] == FREE){
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
    private static void  updateFreeVerticalPaths(Point point){
        List<Point> freePoints = new ArrayList<>();
        if(point.getX()>0){
            for(int i= point.getX()-1;i>=0;i--){
            if(initalArray[i][point.getY()]==FREE || initalArray[i][point.getY()]==PATH){
                freePoints.add(new Point(i,point.getY()));
            }
            if(initalArray[i][point.getY()]==WALL){
                break;
            }
            }
            for(int i=point.getX()+1;i<ROW;i++){
                if(initalArray[i][point.getY()]==FREE || initalArray[i][point.getY()]==PATH){
                    freePoints.add(new Point(i,point.getY()));
                }
                if(initalArray[i][point.getY()]==WALL){
                    break;
                }
            }
        }
        point.setFreeVerticalPaths(freePoints);

    }


}
