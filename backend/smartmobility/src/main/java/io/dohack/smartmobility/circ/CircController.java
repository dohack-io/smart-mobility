package io.dohack.smartmobility.circ;

import io.dohack.smartmobility.circ.model.CircTrip;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor
public class CircController {

    @GetMapping("/circTrip")
    public CircTrip generateCircTrip(@RequestParam(value = "start") String start, @RequestParam(value = "end") String end) {
        return CircProvider.generateCircTip(start, end);
    }

}
