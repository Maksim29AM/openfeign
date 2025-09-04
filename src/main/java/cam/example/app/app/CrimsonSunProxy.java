package cam.example.app.app;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "crimsonsun-client", url = "https://itunes.apple.com")
public interface CrimsonSunProxy {

//    https://itunes.apple.com/search?term=radiohead


    @GetMapping("/search")
    String makeSearchRequest(
           @RequestParam("term") String term,
           @RequestParam("limit") Integer limit
    );

}

