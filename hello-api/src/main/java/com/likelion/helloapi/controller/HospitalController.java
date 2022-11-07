package com.likelion.helloapi.controller;

import com.likelion.helloapi.dao.HospitalDao;
import com.likelion.helloapi.domain.Hospital;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/hospital")
public class HospitalController {

    private final HospitalDao hospitalDao;

    @GetMapping("/find/{id}")
    public ResponseEntity<Hospital> findById(@PathVariable int id) {
        Hospital hospital = hospitalDao.selectById(id);

        if(hospital == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .ok()
                .body(hospital);
    }

    @GetMapping("/optional/{id}")
    public ResponseEntity<Hospital> selectOptional(@PathVariable Integer id) {
        Hospital hospital = this.hospitalDao.selectById(id);
        Optional<Hospital> opt = Optional.of(hospital);
        if (!opt.isEmpty()) {
            return ResponseEntity.ok().body(hospital);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Hospital());
        }
    }

    @PostMapping("register")
    public ResponseEntity<Integer> register(@RequestBody Hospital hospitalDto) {
//        Hospital hospital = new Hospital(hospitalDto.getId(), hospitalDto.getOpenServiceName(), hospitalDto.getOpenLocalGovernmentCode(),
//                hospitalDto.getManagementNumber(), hospitalDto.getLicenseDate(), hospitalDto.getBusinessStatus(), hospitalDto.getBusinessStatusCode(), hospitalDto.getPhone(), hospitalDto.getFullAddress(), hospitalDto.getRoadNameAddress(), hospitalDto.getHospitalName(),
//                hospitalDto.getBusinessTypeName(), hospitalDto.getHealthcareProviderCnt(), hospitalDto.getPatientRoomCnt(), hospitalDto.getTotalNumberOfBeds(), hospitalDto.getTotalAreaSize());

        return ResponseEntity
                .ok()
                .body(hospitalDao.add(hospitalDto));

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteById(@PathVariable int id) {
        Hospital hospital = hospitalDao.selectById(id);

        if(hospital == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .ok()
                .body(hospitalDao.deleteById(id));
    }

}
