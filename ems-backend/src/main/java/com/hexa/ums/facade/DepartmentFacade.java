package com.hexa.ums.facade;

import com.hexa.ums.dto.DepartmentDto;

import java.util.List;

public interface DepartmentFacade {
    List<DepartmentDto> findAll();
}
