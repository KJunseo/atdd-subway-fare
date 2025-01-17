package wooteco.subway.line.domain;

import wooteco.subway.exception.InvalidInputException;
import wooteco.subway.station.domain.Station;

public class Section {
    private Long id;
    private Station upStation;
    private Station downStation;
    private int distance;

    public Section() {
    }

    public Section(Station upStation, Station downStation, int distance) {
        this(null, upStation, downStation, distance);
    }

    public Section(Long id, Station upStation, Station downStation, int distance) {
        validateStations(upStation, downStation);
        this.id = id;
        this.upStation = upStation;
        this.downStation = downStation;
        this.distance = distance;
    }

    private void validateStations(Station upStation, Station downStation) {
        if (upStation.same(downStation)) {
            throw new InvalidInputException("상행역과 하행역이 같을 수 없습니다.");
        }
    }

    public boolean isSameUpStation(Station station) {
        return this.upStation.same(station);
    }

    public boolean isSameDownStation(Station station) {
        return this.downStation.same(station);
    }

    public int adjustDistance(Section newSection) {
        return this.distance - newSection.distance;
    }

    public boolean isShorter(Section section) {
        return this.distance <= section.distance;
    }

    public int increaseDistance(Section section) {
        return this.distance + section.distance;
    }

    public Long getId() {
        return id;
    }

    public Station getUpStation() {
        return upStation;
    }

    public Station getDownStation() {
        return downStation;
    }

    public int getDistance() {
        return distance;
    }
}
