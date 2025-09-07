package cam.example.app.itunes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "crimsonsun-client", url = "${crimsonsun.service.url}")
public interface ItunesProxy {

//    https://itunes.apple.com/search?term=radiohead


    @GetMapping("/search")
    ItunesResponse makeSearchRequest(
           @RequestParam("term") String term,
           @RequestParam("limit") Integer limit
    );

}

