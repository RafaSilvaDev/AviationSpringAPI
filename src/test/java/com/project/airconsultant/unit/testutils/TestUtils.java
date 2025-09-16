package com.project.airconsultant.unit.testutils;

import com.project.airconsultant.model.Airport;

public class TestUtils {
    private static Airport airport;

    public static Airport airportServiceGetAirportMockObject() {
        if(airport == null) {
            airport = new Airport();
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
