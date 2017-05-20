package mazeTexts;

import app.helper.MazeHelper;
import app.labEscape.LabEscape;
import app.labEscape.Point;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Justin on 20/05/2017.
 */
public class MaxeTests {

    @Test
    public void test_path(){

        String baseString ="{o,o,o}|{w, , ,m}";
        char[][] initalArray = MazeHelper.getCharArrayFromString(baseString);
        Point point = new Point(1,1);
        LabEscape.updatePointPaths(point);
        List<Point> horizontalPath = point.getFreeHorizontalPaths();
        Point point1 = horizontalPath.get(1);
        assertEquals(1,point1.getX());
        assertEquals(2,point1.getY());

    }
}
