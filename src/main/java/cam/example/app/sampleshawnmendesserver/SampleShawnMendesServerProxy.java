package cam.example.app.sampleshawnmendesserver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value = "sample-server-shawn-mendes-client")
public interface SampleShawnMendesServerProxy {



//    https://localhost:8080/shawn/songs
    @GetMapping("/shawn/songs")
    SampleServerShawnMendesResponse fetchSongs();


    @PostMapping("/shawn/songs")
    SampleServerShawnMendesResponse addSong(@RequestBody SampleShawnMendesRequest request);
}

