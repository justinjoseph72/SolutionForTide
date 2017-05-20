package app.controller;

import app.helper.MazeHelper;
import app.labEscape.LabEscape;
import app.labEscape.NoEscapeException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.StringTokenizer;

/**
 * Created by Justin on 20/05/2017.
 *
 * This class will be used to create the rest full response
 */
@Controller
public class MazeController {

    private char[][] initalArray;

    /**
     * This method will map the path "/findMaze" to a rest full API
     * @param maze  will be input char array
     * @param startX will be starting x position
     * @param startY will be starting y poistion
     * @return ResponseEnity with following values
     *          OK if a path is found
     *          Not Found if there is a NoEscapeException thrown
     *          Internal Server Error for any other exceptions
     */

    @ApiOperation(value = "This method will be used to find escape route from maze")
    @RequestMapping(value="/findExit", method = RequestMethod.GET)
    public ResponseEntity<String> getMazeSolution(
            @ApiParam(value = "this is the input maze") @RequestParam String maze,
            @ApiParam(value = "this is the start X value") @RequestParam int startX,
            @ApiParam(value = "this is the start Y value") @RequestParam int startY )
    {
        try{
            initalArray = MazeHelper.getCharArrayFromString(maze);
            char [][] output = LabEscape.drawPathForEscape(initalArray,startX,startY);
            HttpHeaders headers = new HttpHeaders();
            headers.set("result",output.toString());
            return new ResponseEntity<String>(HttpStatus.OK);
        }
        catch(NoEscapeException noEscapeException){
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        catch (Exception ex){
            return  new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}

