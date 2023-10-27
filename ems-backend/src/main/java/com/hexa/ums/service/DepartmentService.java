package com.hexa.ums.service;

import com.hexa.ums.model.Department;

import java.util.List;

public interface DepartmentService {
    Department get(Integer departmentId);

    List<Department> findAll();
}
