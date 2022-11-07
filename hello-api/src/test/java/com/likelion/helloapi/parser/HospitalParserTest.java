package com.likelion.helloapi.parser;

import com.likelion.helloapi.dao.HospitalDao;
import com.likelion.helloapi.domain.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalParserTest {

    @Autowired
    ReadLineContext<Hospital> hospitalReadLineContext;
    @Autowired
    HospitalDao hospitalDao;

        /*@BeforeEach
    void setup() {
        hospitalDao.deleteAll();
    }*/

    String lineFirst = "\"1\",\"의원\",\"01_01_02_P\",\"3620000\",\"PHMA119993620020041100004\",\"19990612\",\"\",\"01\",\"영업/정상\",\"13\",\"영업중\",\"\",\"\",\"\",\"\",\"062-515-2875\",\"\",\"500881\",\"광주광역시 북구 풍향동 565번지 4호 3층\",\"광주광역시 북구 동문대로 24, 3층 (풍향동)\",\"61205\",\"효치과의원\",\"20211115113642\",\"U\",\"2021-11-17 02:40:00.0\",\"치과의원\",\"192630.735112\",\"185314.617632\",\"치과의원\",\"1\",\"0\",\"0\",\"52.29\",\"401\",\"치과\",\"\",\"\",\"\",\"0\",\"0\",\"\",\"\",\"0\",\"\",";
    /*@Test
    @DisplayName("10만건 이상 파싱테스트")
    void name() throws IOException {
        String filename = "/Users/jinhyuck/Downloads/fulldata_01_01_02_P_의원.csv";
        List<Hospital> hospitals = hospitalReadLineContext.readByLine(filename);
        System.out.println("hospitals.size = " + hospitals.size());
        assertTrue(hospitals.size() > 10000);
        assertTrue(hospitals.size() > 100000);
    }*/

    @Test
    @DisplayName("csv 1줄을 hospital로 잘 만드는지")
    void convertToHospital() {
        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(lineFirst);
        assertEquals(1, hospital.getId());
        assertEquals("의원", hospital.getOpenServiceName());
        assertEquals(3620000, hospital.getOpenLocalGovernmentCode());
        assertEquals("PHMA119993620020041100004", hospital.getManagementNumber());
        assertEquals(LocalDateTime.of(1999, 06, 12, 0, 0, 0), hospital.getLicenseDate());
        assertEquals(1, hospital.getBusinessStatus());
        assertEquals(13, hospital.getBusinessStatusCode());
        assertEquals("062-515-2875", hospital.getPhone());
        assertEquals("광주광역시 북구 풍향동 565번지 4호 3층", hospital.getFullAddress());
        assertEquals("광주광역시 북구 동문대로 24, 3층 (풍향동)", hospital.getRoadNameAddress());
        assertEquals("효치과의원", hospital.getHospitalName());
        assertEquals("치과의원", hospital.getBusinessTypeName());
        assertEquals(1, hospital.getHealthcareProviderCount());
        assertEquals(0, hospital.getPatientRoomCount());
        assertEquals(0, hospital.getTotalNumberOfBeds());
        assertEquals(52.29f, hospital.getTotalAreaSize());
    }

    /*@Test
    @DisplayName("save가 잘 되는지")
    void saveAndDeleteAndGetCount() {
        HospitalParser hp = new HospitalParser();
        Hospital h1 = hp.parse(lineFirst);
        hospitalDao.save(h1);
        Hospital findHospital = hospitalDao.findById(1);
        System.out.println(findHospital.getLicenseDate());
        assertEquals(1, hospitalDao.getCount());
        assertEquals(LocalDateTime.of(1999, 06, 12, 0, 0, 0), findHospital.getLicenseDate());
        assertEquals(52.29f, findHospital.getTotalAreaSize());
        hospitalDao.deleteAll();
        assertEquals(0, hospitalDao.getCount());
    }*/



}