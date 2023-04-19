package com.zjut.Dicom.controller;

import com.zjut.Dicom.pojo.HospitalUser;
import com.zjut.Dicom.service.HospitalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/hospitalUsers")
public class HospitalUserController {
    @Autowired
    private HospitalUserService hospitalUserService;

    @PostMapping
    public boolean save(@RequestBody HospitalUser record) {
        return hospitalUserService.save(record);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Integer id) {
        return hospitalUserService.deleteById(id);
    }

    @PutMapping
    public boolean updateRecord(@RequestBody HospitalUser record) {
        return hospitalUserService.updateRecord(record);
    }

    @GetMapping("/user/{id}")
    public HospitalUser getById(@PathVariable Integer id) {
        return hospitalUserService.getById(id);
    }

    @GetMapping
    public List<HospitalUser> getAll() {
        return hospitalUserService.getAll();
    }

    @GetMapping("/{account}")
    public HospitalUser getByAccount(@PathVariable String account) {
        return hospitalUserService.getByAccount(account);
    }

    @PutMapping("/{id}/{signature}")
    public boolean updateSignature(@PathVariable Integer id, @PathVariable String signature) {
        return hospitalUserService.updateSignature(id, signature);
    }

    @PostMapping ("/updatePwd")
    public boolean updatePassword(@RequestBody Map<String, Object> info){
        return hospitalUserService.updatePassword(info);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody HospitalUser record){
        return hospitalUserService.update(record);
    }
}
