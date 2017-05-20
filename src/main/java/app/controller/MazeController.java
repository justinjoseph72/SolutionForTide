package app.controller;

import app.labEscape.LabEscape;
import app.labEscape.NoEscapeException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Justin on 20/05/2017.
 *
 * This class will be used to create the rest full response
 */
@Controller
public class MazeController {

    /**
     * This method will map the path "/findMaze" to a rest full API
     * @param maze  will be input char array
     * @param startX will be starting x position
     * @param startY will be starting y poistion
     * @return ResponseEnity with following values
     *          OK if a path is dfound
     *          Not Found if there is a NoEscapeException thrown
     * I        nternal Server Error for any other exceptions
     */

    @ApiOperation(value = "This method will be used to find escape route from maze")
    @RequestMapping(value="/findExit", method = RequestMethod.GET)
    public ResponseEntity<String> getMazeSolution(
            @ApiParam(value = "this is the input maze") @RequestParam char[][] maze,
            @ApiParam(value = "this is the start X value") @RequestParam int startX,
            @ApiParam(value = "this is the start Y value") @RequestParam int startY )
    {
        try{
            char [][] output = LabEscape.drawPathForEscape(maze,startX,startY);
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

