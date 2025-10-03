package com.project.airconsultant.util;

import com.project.airconsultant.model.Airport;
import com.project.airconsultant.model.Plane;

import java.util.Arrays;

public class CustomMappers {

    public static Airport mapAirportFromRows(Object[] row) {
        Airport airport = new Airport();

        airport.setId(((Number) row[0]).longValue());
        airport.setSiteNumber((String) row[1]);
        airport.setType((String) row[2]);
        airport.setName((String) row[3]);
        airport.setIataCode((String) row[4]);
        airport.setIcaoCode((String) row[5]);
        airport.setDistrictOffice((String) row[6]);
        airport.setState((String) row[7]);
        airport.setStateFull((String) row[8]);
        airport.setCounty((String) row[9]);
        airport.setCity((String) row[10]);
        airport.setOwnership((String) row[11]);
        airport.setUse((String) row[12]);
        airport.setManager((String) row[13]);
        airport.setManagerPhone((String) row[14]);
        airport.setLatitude((String) row[15]);
        airport.setLatitudeSeconds((String) row[16]);
        airport.setLongitude((String) row[17]);
        airport.setLongitudeSeconds((String) row[18]);
        airport.setElevation((String) row[19]);
        airport.setMagneticVariation((String) row[20]);
        airport.setTpa((String) row[21]);
        airport.setVfrSectional((String) row[22]);
        airport.setBoundaryArtcc((String) row[23]);
        airport.setBoundaryArtccName((String) row[24]);
        airport.setResponsibleArtcc((String) row[25]);
        airport.setResponsibleArtccName((String) row[26]);
        airport.setFssPhoneNumber((String) row[27]);
        airport.setFssPhoneNumberTollFree((String) row[28]);
        airport.setNotamFacilityIdent((String) row[29]);
        airport.setStatus((String) row[30]);
        airport.setCertificationTypeDate((String) row[31]);
        airport.setCustomsAirportOfEntry((String) row[32]);
        airport.setMilitaryJointUse((String) row[33]);
        airport.setMilitaryLanding((String) row[34]);
        airport.setLightingSchedule((String) row[35]);
        airport.setBeaconSchedule((String) row[36]);
        airport.setControlTower((String) row[37]);
        airport.setUnicom((String) row[38]);
        airport.setCtaf((String) row[39]);
        airport.setEffectiveDate((String) row[40]);

        return airport;
    }

    public static Plane mapPlaneFromRows(Object[] row) {
        if (row.length <= 43 || row[41] == null) { //airport without any planes
            return null;
        }

        Plane plane = new Plane();
        plane.setId((Long) row[41]);
        plane.setCode((String) row[42]);
        plane.setModel((String) row[43]);

        return plane;
    }
}
