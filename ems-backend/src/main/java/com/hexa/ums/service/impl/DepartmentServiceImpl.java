package com.hexa.ums.service.impl;

import com.hexa.ums.model.Department;
import com.hexa.ums.repository.DepartmentRepository;
import com.hexa.ums.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public Department get(Integer departmentId) {
        return this.departmentRepository.findByDepartmentId(departmentId);
    }

    @Override
    public List<Department> findAll() {
        return this.departmentRepository.findAll();
    }
}
