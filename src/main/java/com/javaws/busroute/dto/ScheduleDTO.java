package com.javaws.busroute.dto;

import lombok.Data;

@Data
public class ScheduleDTO {
    private RouteDTO route;
    private TimingDTO timing;

    public ScheduleDTO() {}
    public ScheduleDTO(RouteDTO route, TimingDTO timing) {
        this.route = route;
        this.timing = timing;
    }
}
