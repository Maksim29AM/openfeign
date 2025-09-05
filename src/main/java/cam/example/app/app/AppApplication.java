package cam.example.app.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableFeignClients
public class AppApplication {

    @Autowired
    CrimsonSunProxy crimsonSunClient;
    @Autowired
    DogProxy dogClient;

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void makeRequestToCrimsonSunEndpoint() {
        String response = crimsonSunClient.makeSearchRequest("crimsonsun", 100);
        System.out.println(response);

//    @EventListener(ApplicationStartedEvent.class)
//    public void makeRequestToRandomDog() {
//        Dog dogResponse = dogClient.getRandomDogImage();
//        System.out.println("Image: "+dogResponse.getMessage());
//
//        System.out.println("======================");
//
//        DogList dogResponseByBreed = dogClient.getDogByBreed("doberman");
//        for (String dogs : dogResponseByBreed.getMessage()) {
//            System.out.println(dogs);
//        }
    }

}

