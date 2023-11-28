package uz.gc.travel.logger.api.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author a.ergashev
 * Date: 11/27/2023
 * Time: 8:45 PM
 */
public enum ERequestStatus {
    SUCCESS("SUCCESS"),
    ERROR("ERROR");

    private final String value;
    ERequestStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }
}
