package wooteco.subway.path;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wooteco.subway.path.domain.fare.distance.DefaultDistance;
import wooteco.subway.path.domain.fare.distance.DistanceChain;
import wooteco.subway.path.domain.fare.distance.SecondDistance;
import wooteco.subway.path.domain.fare.distance.ThirdDistance;

@Configuration
public class DistanceChainConfig {
    private static final int FIRST_THRESHOLD = 10;
    private static final int SECOND_THRESHOLD = 50;

    @Bean
    public DistanceChain defaultChain() {
        DistanceChain third = new ThirdDistance();
        DistanceChain second = new SecondDistance(third, SECOND_THRESHOLD - FIRST_THRESHOLD);
        return new DefaultDistance(second, FIRST_THRESHOLD);
    }
}
