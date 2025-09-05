package cam.example.app.app;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "dog-client", url = "${dog.service.url}")
public interface DogProxy {

    //    https://dog.ceo/api/breeds/image/random
    @GetMapping("/api/breeds/image/random")
    Dog getRandomDogImage();

    @GetMapping("/api/breed/{name}/images")
    DogList getDogByBreed(@PathVariable String name);
}
