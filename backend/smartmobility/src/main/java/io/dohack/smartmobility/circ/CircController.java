package io.dohack.smartmobility.circ;

import io.dohack.smartmobility.circ.model.CircTrip;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequiredArgsConstructor
public class CircController {

    @RequestMapping(method=GET, path="/circTrip")
    public CircTrip circTrip(@RequestParam(value="start") String start, @RequestParam(value="end") String end) {
        return CircProvider.generateCircTip(start, end);
    }
}