package com.project.airconsultant.unit.testutils;

import com.project.airconsultant.model.Airport;
import com.project.airconsultant.model.AviationAirport;

public class TestUtils {
    private static AviationAirport aviationAirport;
    private static Airport airport;

    public static AviationAirport aviationAirportServiceGetAviationAirportMockObject() {
        if(aviationAirport == null) {
            aviationAirport = new AviationAirport();
            aviationAirport.setSiteNumber("15793.*A");
            aviationAirport.setType("AIRPORT");
            aviationAirport.setName("JOHN F KENNEDY INTL");
            aviationAirport.setIataCode("JFK");
            aviationAirport.setIcaoCode("KJFK");
            aviationAirport.setDistrictOffice("NYC");
            aviationAirport.setState("NY");
            aviationAirport.setStateFull("NEW YORK");
            aviationAirport.setCounty("QUEENS");
            aviationAirport.setCity("NEW YORK");
            aviationAirport.setOwnership("PU");
            aviationAirport.setUse("PU");
            aviationAirport.setManager("CHARLES EVERETT");
            aviationAirport.setManagerPhone("(718) 244-3501");
            aviationAirport.setLatitude("40-38-23.7400N");
            aviationAirport.setLatitudeSeconds("146303.7400N");
            aviationAirport.setLongitude("073-46-43.2930W");
            aviationAirport.setLongitudeSeconds("265603.2930W");
            aviationAirport.setElevation("13");
            aviationAirport.setMagneticVariation("13W");
            aviationAirport.setTpa("");
            aviationAirport.setVfrSectional("NEW YORK");
            aviationAirport.setBoundaryArtcc("ZNY");
            aviationAirport.setBoundaryArtccName("NEW YORK");
            aviationAirport.setResponsibleArtcc("ZNY");
            aviationAirport.setResponsibleArtccName("NEW YORK");
            aviationAirport.setFssPhoneNumber("");
            aviationAirport.setFssPhoneNumberTollFree("1-800-WX-BRIEF");
            aviationAirport.setNotamFacilityIdent("JFK");
            aviationAirport.setStatus("O");
            aviationAirport.setCertificationTypeDate("I E S 05/1973");
            aviationAirport.setCustomsAirportOfEntry("N");
            aviationAirport.setMilitaryJointUse("N");
            aviationAirport.setMilitaryLanding("Y");
            aviationAirport.setLightingSchedule("");
            aviationAirport.setBeaconSchedule("SS-SR");
            aviationAirport.setControlTower("Y");
            aviationAirport.setUnicom("122.950");
            aviationAirport.setCtaf("");
            aviationAirport.setEffectiveDate("11/04/2021");
        }

        return aviationAirport;
    }

    public static Airport airportServiceGetAirportMockObject() {
        if(airport == null) {
            airport = new Airport();
            airport.setId(1L);
            airport.setSiteNumber("15793.*A");
            airport.setType("AIRPORT");
            airport.setName("JOHN F KENNEDY INTL");
            airport.setIataCode("JFK");
            airport.setIcaoCode("KJFK");
            airport.setDistrictOffice("NYC");
            airport.setState("NY");
            airport.setStateFull("NEW YORK");
            airport.setCounty("QUEENS");
            airport.setCity("NEW YORK");
            airport.setOwnership("PU");
            airport.setUse("PU");
            airport.setManager("CHARLES EVERETT");
            airport.setManagerPhone("(718) 244-3501");
            airport.setLatitude("40-38-23.7400N");
            airport.setLatitudeSeconds("146303.7400N");
            airport.setLongitude("073-46-43.2930W");
            airport.setLongitudeSeconds("265603.2930W");
            airport.setElevation("13");
            airport.setMagneticVariation("13W");
            airport.setTpa("");
            airport.setVfrSectional("NEW YORK");
            airport.setBoundaryArtcc("ZNY");
            airport.setBoundaryArtccName("NEW YORK");
            airport.setResponsibleArtcc("ZNY");
            airport.setResponsibleArtccName("NEW YORK");
            airport.setFssPhoneNumber("");
            airport.setFssPhoneNumberTollFree("1-800-WX-BRIEF");
            airport.setNotamFacilityIdent("JFK");
            airport.setStatus("O");
            airport.setCertificationTypeDate("I E S 05/1973");
            airport.setCustomsAirportOfEntry("N");
            airport.setMilitaryJointUse("N");
            airport.setMilitaryLanding("Y");
            airport.setLightingSchedule("");
            airport.setBeaconSchedule("SS-SR");
            airport.setControlTower("Y");
            airport.setUnicom("122.950");
            airport.setCtaf("");
            airport.setEffectiveDate("11/04/2021");
        }

        return airport;
    }

}
