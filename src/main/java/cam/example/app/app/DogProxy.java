package cam.example.app.app;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "dog-client", url = "https://dog.ceo/api")
public interface DogProxy {

    //    https://dog.ceo/api/breeds/image/random
    @GetMapping("/breeds/image/random")
    Dog getRandomDogImage();

    @GetMapping("/breed/{name}/images")
    DogList getDogByBreed(@PathVariable String name);
}
