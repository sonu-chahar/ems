package com.hexa.ums.facade.populators;

import com.hexa.ums.converter.Populator;
import com.hexa.ums.dto.DepartmentDto;
import com.hexa.ums.dto.UserDto;
import com.hexa.ums.exception.ConversionException;
import com.hexa.ums.model.Department;
import org.springframework.stereotype.Component;

@Component("departmentPopulator")
public class DepartmentPopulator implements Populator<Department, DepartmentDto> {

    @Override
    public DepartmentDto populate(Department department) throws ConversionException {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getDepartmentId());
        departmentDto.setName(department.getName());
        return departmentDto;
    }
}
