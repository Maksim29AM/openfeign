package cam.example.app;

import cam.example.app.itunes.ItunesProxy;
import cam.example.app.itunes.ItunesResponse;
import cam.example.app.itunes.ItunesResult;
import cam.example.app.sampleshawnmendesserver.SampleServerShawnMendesResponse;
import cam.example.app.sampleshawnmendesserver.SampleShawnMendesRequest;
import cam.example.app.sampleshawnmendesserver.SampleShawnMendesServerProxy;
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
    ItunesProxy iTunesClient;

    @Autowired
    SampleShawnMendesServerProxy sampleShawnMendesServerClient;

    Logger log = getLogger(AppApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void run() {
        try {
//            ItunesResponse response = iTunesClient.makeSearchRequest("crimsonsun", 100);
            SampleServerShawnMendesResponse response = sampleShawnMendesServerClient.fetchSongs();
            SampleShawnMendesRequest request = new SampleShawnMendesRequest("New song");
//            SampleServerShawnMendesResponse addSongs = sampleShawnMendesServerClient.addSong(request);
            sampleShawnMendesServerClient.deleteSong("0");
            log.info(response);
//            log.info(addSongs);

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

