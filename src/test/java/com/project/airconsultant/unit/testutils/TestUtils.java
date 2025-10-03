package com.project.airconsultant.unit.testutils;

import com.project.airconsultant.model.Airport;
import com.project.airconsultant.model.AviationAirport;
import com.project.airconsultant.model.Plane;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static AviationAirport aviationAirportServiceGetAviationAirportMockObject() {
            AviationAirport aviationAirportTest = new AviationAirport();
            aviationAirportTest.setSiteNumber("15793.*A");
            aviationAirportTest.setType("AIRPORT");
            aviationAirportTest.setName("JOHN F KENNEDY INTL");
            aviationAirportTest.setIataCode("JFK");
            aviationAirportTest.setIcaoCode("KJFK");
            aviationAirportTest.setDistrictOffice("NYC");
            aviationAirportTest.setState("NY");
            aviationAirportTest.setStateFull("NEW YORK");
            aviationAirportTest.setCounty("QUEENS");
            aviationAirportTest.setCity("NEW YORK");
            aviationAirportTest.setOwnership("PU");
            aviationAirportTest.setUse("PU");
            aviationAirportTest.setManager("CHARLES EVERETT");
            aviationAirportTest.setManagerPhone("(718) 244-3501");
            aviationAirportTest.setLatitude("40-38-23.7400N");
            aviationAirportTest.setLatitudeSeconds("146303.7400N");
            aviationAirportTest.setLongitude("073-46-43.2930W");
            aviationAirportTest.setLongitudeSeconds("265603.2930W");
            aviationAirportTest.setElevation("13");
            aviationAirportTest.setMagneticVariation("13W");
            aviationAirportTest.setTpa("");
            aviationAirportTest.setVfrSectional("NEW YORK");
            aviationAirportTest.setBoundaryArtcc("ZNY");
            aviationAirportTest.setBoundaryArtccName("NEW YORK");
            aviationAirportTest.setResponsibleArtcc("ZNY");
            aviationAirportTest.setResponsibleArtccName("NEW YORK");
            aviationAirportTest.setFssPhoneNumber("");
            aviationAirportTest.setFssPhoneNumberTollFree("1-800-WX-BRIEF");
            aviationAirportTest.setNotamFacilityIdent("JFK");
            aviationAirportTest.setStatus("O");
            aviationAirportTest.setCertificationTypeDate("I E S 05/1973");
            aviationAirportTest.setCustomsAirportOfEntry("N");
            aviationAirportTest.setMilitaryJointUse("N");
            aviationAirportTest.setMilitaryLanding("Y");
            aviationAirportTest.setLightingSchedule("");
            aviationAirportTest.setBeaconSchedule("SS-SR");
            aviationAirportTest.setControlTower("Y");
            aviationAirportTest.setUnicom("122.950");
            aviationAirportTest.setCtaf("");
            aviationAirportTest.setEffectiveDate("11/04/2021");

        return aviationAirportTest;
    }

    public static Airport airportServiceGetAirportMockObject() {
            List<Plane> testPlanes = generateTestPlanes();

            Airport airportTest = new Airport();
            airportTest.setId(10L);
            airportTest.setSiteNumber("15793.*A");
            airportTest.setType("AIRPORT");
            airportTest.setName("JOHN F KENNEDY INTL");
            airportTest.setIataCode("JFK");
            airportTest.setIcaoCode("KJFK");
            airportTest.setDistrictOffice("NYC");
            airportTest.setState("NY");
            airportTest.setStateFull("NEW YORK");
            airportTest.setCounty("QUEENS");
            airportTest.setCity("NEW YORK");
            airportTest.setOwnership("PU");
            airportTest.setUse("PU");
            airportTest.setManager("CHARLES EVERETT");
            airportTest.setManagerPhone("(718) 244-3501");
            airportTest.setLatitude("40-38-23.7400N");
            airportTest.setLatitudeSeconds("146303.7400N");
            airportTest.setLongitude("073-46-43.2930W");
            airportTest.setLongitudeSeconds("265603.2930W");
            airportTest.setElevation("13");
            airportTest.setMagneticVariation("13W");
            airportTest.setTpa("");
            airportTest.setVfrSectional("NEW YORK");
            airportTest.setBoundaryArtcc("ZNY");
            airportTest.setBoundaryArtccName("NEW YORK");
            airportTest.setResponsibleArtcc("ZNY");
            airportTest.setResponsibleArtccName("NEW YORK");
            airportTest.setFssPhoneNumber("");
            airportTest.setFssPhoneNumberTollFree("1-800-WX-BRIEF");
            airportTest.setNotamFacilityIdent("JFK");
            airportTest.setStatus("O");
            airportTest.setCertificationTypeDate("I E S 05/1973");
            airportTest.setCustomsAirportOfEntry("N");
            airportTest.setMilitaryJointUse("N");
            airportTest.setMilitaryLanding("Y");
            airportTest.setLightingSchedule("");
            airportTest.setBeaconSchedule("SS-SR");
            airportTest.setControlTower("Y");
            airportTest.setUnicom("122.950");
            airportTest.setCtaf("");
            airportTest.setEffectiveDate("11/04/2021");
            airportTest.setPlanes(testPlanes);

        return airportTest;
    }

    public static Airport airportServiceSaveAirportMockObject() {
        Airport airportSaveTest = airportServiceGetAirportMockObject();
        airportSaveTest.setPlanes(new ArrayList<>());
        return airportSaveTest;
    }

    public static List<Object[]> airportServiceGetAirportNativeQueryMockObject() {
        Airport airportTest = airportServiceGetAirportMockObject();
        List<Object[]> rows = new ArrayList<>();

        for (Plane plane : airportTest.getPlanes()) {
            Object[] row = new Object[]{
                    airportTest.getId(),
                    airportTest.getSiteNumber(),
                    airportTest.getType(),
                    airportTest.getName(),
                    airportTest.getIataCode(),
                    airportTest.getIcaoCode(),
                    airportTest.getDistrictOffice(),
                    airportTest.getState(),
                    airportTest.getStateFull(),
                    airportTest.getCounty(),
                    airportTest.getCity(),
                    airportTest.getOwnership(),
                    airportTest.getUse(),
                    airportTest.getManager(),
                    airportTest.getManagerPhone(),
                    airportTest.getLatitude(),
                    airportTest.getLatitudeSeconds(),
                    airportTest.getLongitude(),
                    airportTest.getLongitudeSeconds(),
                    airportTest.getElevation(),
                    airportTest.getMagneticVariation(),
                    airportTest.getTpa(),
                    airportTest.getVfrSectional(),
                    airportTest.getBoundaryArtcc(),
                    airportTest.getBoundaryArtccName(),
                    airportTest.getResponsibleArtcc(),
                    airportTest.getResponsibleArtccName(),
                    airportTest.getFssPhoneNumber(),
                    airportTest.getFssPhoneNumberTollFree(),
                    airportTest.getNotamFacilityIdent(),
                    airportTest.getStatus(),
                    airportTest.getCertificationTypeDate(),
                    airportTest.getCustomsAirportOfEntry(),
                    airportTest.getMilitaryJointUse(),
                    airportTest.getMilitaryLanding(),
                    airportTest.getLightingSchedule(),
                    airportTest.getBeaconSchedule(),
                    airportTest.getControlTower(),
                    airportTest.getUnicom(),
                    airportTest.getCtaf(),
                    airportTest.getEffectiveDate(),
                    plane.getId(),
                    plane.getCode(),
                    plane.getModel()
            };

            rows.add(row);
        }
        return rows;
    }

    private static List<Plane> generateTestPlanes() {
        List<Plane> planes = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            Plane plane = new Plane();
            plane.setId((long) i);
            plane.setModel("Test Model " + i);
            plane.setCode(String.valueOf(i));

            planes.add(plane);
        }

        return planes;
    }

}
