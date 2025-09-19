package com.project.airconsultant.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class AviationAirport implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("site_number")
    private String siteNumber;

    @JsonProperty("type")
    private String type;

    @JsonProperty("facility_name")
    private String name;

    @JsonProperty("faa_ident")
    private String iataCode;

    @JsonProperty("icao_ident")
    private String icaoCode;

    @JsonProperty("district_office")
    private String districtOffice;

    @JsonProperty("state")
    private String state;

    @JsonProperty("state_full")
    private String stateFull;

    @JsonProperty("county")
    private String county;

    @JsonProperty("city")
    private String city;

    @JsonProperty("ownership")
    private String ownership;

    @JsonProperty("use")
    private String use;

    @JsonProperty("manager")
    private String manager;

    @JsonProperty("manager_phone")
    private String managerPhone;

    @JsonProperty("latitude")
    private String latitude;

    @JsonProperty("latitude_sec")
    private String latitudeSeconds;

    @JsonProperty("longitude")
    private String longitude;

    @JsonProperty("longitude_sec")
    private String longitudeSeconds;

    @JsonProperty("elevation")
    private String elevation;

    @JsonProperty("magnetic_variation")
    private String magneticVariation;

    @JsonProperty("tpa")
    private String tpa;

    @JsonProperty("vfr_sectional")
    private String vfrSectional;

    @JsonProperty("boundary_artcc")
    private String boundaryArtcc;

    @JsonProperty("boundary_artcc_name")
    private String boundaryArtccName;

    @JsonProperty("responsible_artcc")
    private String responsibleArtcc;

    @JsonProperty("responsible_artcc_name")
    private String responsibleArtccName;

    @JsonProperty("fss_phone_number")
    private String fssPhoneNumber;

    @JsonProperty("fss_phone_numer_tollfree")
    private String fssPhoneNumberTollFree;

    @JsonProperty("notam_facility_ident")
    private String notamFacilityIdent;

    @JsonProperty("status")
    private String status;

    @JsonProperty("certification_typedate")
    private String certificationTypeDate;

    @JsonProperty("customs_airport_of_entry")
    private String customsAirportOfEntry;

    @JsonProperty("military_joint_use")
    private String militaryJointUse;

    @JsonProperty("military_landing")
    private String militaryLanding;

    @JsonProperty("lighting_schedule")
    private String lightingSchedule;

    @JsonProperty("beacon_schedule")
    private String beaconSchedule;

    @JsonProperty("control_tower")
    private String controlTower;

    @JsonProperty("unicom")
    private String unicom;

    @JsonProperty("ctaf")
    private String ctaf;

    @JsonProperty("effective_date")
    private String effectiveDate;
}
