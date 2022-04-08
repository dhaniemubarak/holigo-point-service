package id.holigo.services.holigopointservice.web.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PointHistoryPaginate extends PageImpl<PointHistoryDto> {

    @JsonCreator(mode = PROPERTIES)
    public PointHistoryPaginate(@JsonProperty("data") List<PointHistoryDto> content,
            @JsonProperty("number") int number,
            @JsonProperty("size") int size, @JsonProperty("totalElement") int totalElements,
            @JsonProperty("pageable") JsonNode pageable, @JsonProperty("last") boolean last,
            @JsonProperty("totalPages") int totalPages, @JsonProperty("sort") JsonNode sort,
            @JsonProperty("first") boolean first, @JsonProperty("numberOfElement") int numberOfElement

    ) {
        super(content, PageRequest.of(number, size), totalElements);
    }

    public PointHistoryPaginate(List<PointHistoryDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public PointHistoryPaginate(List<PointHistoryDto> content) {
        super(content);
    }

}
