package com.project.airconsultant.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "airport")
@NamedEntityGraph(name = "airport-with-planes", attributeNodes = @NamedAttributeNode("planes"))
public class Airport implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Attribute 'site_number' must be informed.")
    @JsonProperty("site_number")
    private String siteNumber;

    @Value("${type:AIRPORT}")
    @JsonProperty("type")
    private String type;

    @NotNull(message = "Attribute 'facility_name' must be informed.")
    @JsonProperty("facility_name")
    private String name;

    @NotNull(message = "Attribute 'faa_ident' must be informed.")
    @Size(max = 4, message = "Attribute 'faa_ident' shouldn't contain more than 4 characters.")
    @JsonProperty("faa_ident")
    private String iataCode;

    @NotNull(message = "Attribute 'icao_ident' must be informed.")
    @Size(max = 4, message = "Attribute 'icao_ident' shouldn't contain more than 4 characters.")
    @JsonProperty("icao_ident")
    private String icaoCode;

    @NotNull(message = "Attribute 'district_office' must be informed.")
    @JsonProperty("district_office")
    private String districtOffice;

    @NotNull(message = "Attribute 'state' must be informed.")
    @Size(max = 2)
    @JsonProperty("state")
    private String state;

    @NotNull(message = "Attribute 'state_full' must be informed.")
    @JsonProperty("state_full")
    private String stateFull;

    @NotNull(message = "Attribute 'county' must be informed.")
    @JsonProperty("county")
    private String county;

    @NotNull(message = "Attribute 'city' must be informed.")
    @JsonProperty("city")
    private String city;

    @NotNull(message = "Attribute 'ownership' must be informed.")
    @Size(max = 2)
    @JsonProperty("ownership")
    private String ownership;

    @NotNull(message = "Attribute 'use' must be informed.")
    @Size(max = 2)
    @JsonProperty("use")
    private String use;

    @NotNull(message = "Attribute 'manageer' must be informed.")
    @JsonProperty("manager")
    private String manager;

    @NotNull(message = "Attribute 'manager_phone' must be informed.")
    @JsonProperty("manager_phone")
    private String managerPhone;

    @NotNull(message = "Attribute 'latitude' must be informed.")
    @JsonProperty("latitude")
    private String latitude;

    @NotNull(message = "Attribute 'latitude_sec' must be informed.")
    @JsonProperty("latitude_sec")
    private String latitudeSeconds;

    @NotNull(message = "Attribute 'longitude' must be informed.")
    @JsonProperty("longitude")
    private String longitude;

    @NotNull(message = "Attribute 'longitude_sec' must be informed.")
    @JsonProperty("longitude_sec")
    private String longitudeSeconds;

    @NotNull(message = "Attribute 'elevation' must be informed.")
    @JsonProperty("elevation")
    private String elevation;

    @NotNull(message = "Attribute 'magnetic_variation' must be informed.")
    @JsonProperty("magnetic_variation")
    private String magneticVariation;

    @JsonProperty("tpa")
    private String tpa;

    @NotNull(message = "Attribute 'vfr_sectional' must be informed.")
    @JsonProperty("vfr_sectional")
    private String vfrSectional;

    @NotNull(message = "Attribute 'boundary_artcc' must be informed.")
    @JsonProperty("boundary_artcc")
    private String boundaryArtcc;

    @NotNull(message = "Attribute 'boundary_artcc_name' must be informed.")
    @JsonProperty("boundary_artcc_name")
    private String boundaryArtccName;

    @NotNull(message = "Attribute 'responsible_artcc' must be informed.")
    @JsonProperty("responsible_artcc")
    private String responsibleArtcc;

    @NotNull(message = "Attribute 'responsible_artcc_name' must be informed.")
    @JsonProperty("responsible_artcc_name")
    private String responsibleArtccName;

    @JsonProperty("fss_phone_number")
    private String fssPhoneNumber;

    @NotNull(message = "Attribute 'fss_phone_number_tollfree' must be informed.")
    @JsonProperty("fss_phone_numer_tollfree")
    private String fssPhoneNumberTollFree;

    @NotNull(message = "Attribute 'notam_facility_ident' must be informed.")
    @JsonProperty("notam_facility_ident")
    private String notamFacilityIdent;

    @NotNull(message = "Attribute 'status' must be informed.")
    @JsonProperty("status")
    private String status;

    @NotNull(message = "Attribute 'certification_typedate' must be informed.")
    @JsonProperty("certification_typedate")
    private String certificationTypeDate;

    @NotNull(message = "Attribute 'customs_airport_of_entry' must be informed.")
    @JsonProperty("customs_airport_of_entry")
    private String customsAirportOfEntry;

    @Pattern(regexp = "^([YN])?$", message = "Field must be 'Y', 'N' or nullable")
    @JsonProperty("military_joint_use")
    private String militaryJointUse;

    @Pattern(regexp = "^([YN])?$", message = "Field must be 'Y', 'N' or nullable")
    @JsonProperty("military_landing")
    private String militaryLanding;

    @JsonProperty("lighting_schedule")
    private String lightingSchedule;

    @NotNull(message = "Attribute 'beacon_schedule' must be informed.")
    @JsonProperty("beacon_schedule")
    private String beaconSchedule;

    @NotNull(message = "Attribute 'control_tower' must be informed.")
    @Pattern(regexp = "^([YN])?$", message = "Field must be 'Y', 'N' or nullable")
    @JsonProperty("control_tower")
    private String controlTower;

    @NotNull(message = "Attribute 'unicom' must be informed.")
    @JsonProperty("unicom")
    private String unicom;

    @JsonProperty("ctaf")
    private String ctaf;

    @NotNull(message = "Attribute 'effective_date' must be informed.")
    @JsonProperty("effective_date")
    private String effectiveDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Plane> planes = new ArrayList<>();
}
