package com.likelion.helloapi.dao;


import com.likelion.helloapi.domain.Hospital;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HospitalDao {
    private final JdbcTemplate jdbcTemplate;

    // 11만건 데이터를 List<Hospital> 에서 Hospital로 꺼내어 insert
    public int add(Hospital hospital) {
        String sql = "insert into `likelion-db`.`nation_wide_hospitals` (`id`, `open_service_name`, `open_local_government_code`,\n" +
                "`management_number`, `license_date`, `business_status`, `business_status_code`, `phone`, `full_address`, `road_name_address`, `hospital_name`,\n" +
                "`business_type_name`, `healthcare_provider_count`, `patient_room_count`, `total_number_of_beds`,\n" +
                "`total_area_size`) " +
                "values (?, ?, ?, ?, ?, ?, ?,\n" +
                "?, ?, ?, ?, ?, ?, ?, ?, ?);";

        return this.jdbcTemplate.update(sql,
                hospital.getId(),
                hospital.getOpenServiceName(),
                hospital.getOpenLocalGovernmentCode(),
                hospital.getManagementNumber(),
                hospital.getLicenseDate(),
                hospital.getBusinessStatus(),
                hospital.getBusinessStatusCode(),
                hospital.getPhone(),
                hospital.getFullAddress(),
                hospital.getRoadNameAddress(),
                hospital.getHospitalName(),
                hospital.getBusinessTypeName(),
                hospital.getHealthcareProviderCnt(),
                hospital.getPatientRoomCnt(),
                hospital.getTotalNumberOfBeds(),
                hospital.getTotalAreaSize());
    }

    RowMapper<Hospital> rowMapper = (rs, rowNum) -> {
        Hospital hospital = new Hospital();

        hospital.setId(rs.getInt("id"));
        hospital.setOpenServiceName(rs.getString("open_service_name"));
        hospital.setOpenLocalGovernmentCode(rs.getInt("open_local_government_code"));
        hospital.setManagementNumber(rs.getString("management_number"));
        hospital.setLicenseDate(rs.getTimestamp("license_date").toLocalDateTime());
        hospital.setBusinessStatus(rs.getInt("business_status"));
        hospital.setBusinessStatusCode(rs.getInt("business_status_code"));
        hospital.setPhone(rs.getString("phone"));
        hospital.setFullAddress(rs.getString("full_address"));
        hospital.setRoadNameAddress(rs.getString("road_name_address"));
        hospital.setHospitalName(rs.getString("hospital_name"));
        hospital.setBusinessTypeName(rs.getString("business_type_name"));
        hospital.setHealthcareProviderCnt(rs.getInt("healthcare_provider_count"));
        hospital.setPatientRoomCnt(rs.getInt("patient_room_count"));
        hospital.setTotalNumberOfBeds(rs.getInt("total_number_of_beds"));
        hospital.setTotalAreaSize(rs.getFloat("total_area_size"));
        return hospital;
    };

    public Hospital selectById(int sId) {
        String sql = "select * from `likelion-db`.`nation_wide_hospitals` where `id`=?";

        return this.jdbcTemplate.queryForObject(sql, rowMapper, sId);
    }

    public int deleteAll() {
        String sql = "delete from `likelion-db`.`nation_wide_hospitals`;";
        return this.jdbcTemplate.update(sql);
    }

    public int deleteById(int sId) {
        String sql = "delete from `likelion-db`.`nation_wide_hospitals` where id = ?;";

        return this.jdbcTemplate.update(sql, sId);
    }

    public int getCount() {
        String sql = "select count(id) from `likelion-db`.`nation_wide_hospitals`;";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
