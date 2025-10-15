package com.dsl.simulator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunicationLinkDTO {
    private String satelliteId;
    private String satelliteName;
    private String groundStationCode;
    private String groundStationName;
    private Boolean isVisible;
    private Double elevationAngle;
    private Double azimuthAngle;
    private Double distance;  // km
    private String signalQuality;  // "excellent", "good", "fair", "poor"
    private String linkColor;  // Hex color for visualization
}
