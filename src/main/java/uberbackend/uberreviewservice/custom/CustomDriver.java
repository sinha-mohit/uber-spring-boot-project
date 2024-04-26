package uberbackend.uberreviewservice.custom;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CustomDriver {
    private Long id;
    private String name;

    public CustomDriver(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "CustomDriver{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
