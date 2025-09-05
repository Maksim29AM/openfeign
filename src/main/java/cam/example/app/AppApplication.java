package cam.example.app;

import feign.FeignException;
import feign.RetryableException;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

@SpringBootApplication
@EnableFeignClients
public class AppApplication {

    @Autowired
    CrimsonSunProxy crimsonSunClient;

    Logger log = getLogger(AppApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void makeRequestToCrimsonSunEndpoint() {
        try {
            CrimsonSunResponse response = crimsonSunClient.makeSearchRequest("crimsonsun", 100);
            List<CrimsonSunResult> results = response.results();
            results.stream()
                    .filter(crimsonSunResult -> "Neon Lights".equalsIgnoreCase(crimsonSunResult.trackName()))
                    .forEach(System.out::println);

        } catch (FeignException.FeignClientException feignException) {
            log.error("Client exception: {}", feignException.status());
        } catch (FeignException.FeignServerException feignException) {
            log.error("Server exceptions: {}", feignException.status());
        } catch (RetryableException retryableException) {
            log.error("Retryable exceptions: {}", retryableException.getMessage());
        } catch (FeignException feignException) {
            log.error(feignException.getMessage());
            log.error(feignException.status());
        }
    }
}

