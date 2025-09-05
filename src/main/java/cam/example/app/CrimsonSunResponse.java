package cam.example.app;

import java.util.List;

public record CrimsonSunResponse(Integer resultCount, List<CrimsonSunResult> results) {
}
