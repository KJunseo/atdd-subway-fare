package wooteco.subway.path.application;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.springframework.stereotype.Service;
import wooteco.subway.exception.InvalidPathException;
import wooteco.subway.line.domain.Line;
import wooteco.subway.path.domain.SectionEdge;
import wooteco.subway.path.domain.SubwayGraph;
import wooteco.subway.path.domain.SubwayPath;
import wooteco.subway.station.domain.Station;

import java.util.ArrayList;
import java.util.List;

@Service
public class PathFinder {

    public SubwayPath findPath(List<Line> lines, Station departure, Station arrival) {
        if (departure.same(arrival)) {
            throw new InvalidPathException("출발역과 도착역은 같을 수 없습니다!");
        }
        SubwayGraph graph = new SubwayGraph(SectionEdge.class);
        graph.addVertexWith(lines);
        graph.addEdge(lines);

        DijkstraShortestPath<Station, SectionEdge> dijkstraShortestPath = new DijkstraShortestPath<>(graph);
        GraphPath<Station, SectionEdge> path = dijkstraShortestPath.getPath(departure, arrival);
        if (path == null) {
            throw new InvalidPathException("연결되어 있지 않은 경로입니다.");
        }

        return convertSubwayPath(path);
    }

    private SubwayPath convertSubwayPath(GraphPath<Station, SectionEdge> graphPath) {
        List<SectionEdge> edges = new ArrayList<>(graphPath.getEdgeList());
        List<Station> stations = graphPath.getVertexList();
        return new SubwayPath(edges, stations);
    }
}
