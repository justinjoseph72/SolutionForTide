package app.controller;

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

    @RequestMapping(value="/findExit", method = RequestMethod.GET)
    public ResponseEntity<Void> getMazeSolution( )
    {
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}

