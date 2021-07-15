package com.example.thymeleaf.dto.mapper;

import com.example.thymeleaf.dto.StudentRequestDTO;
import com.example.thymeleaf.dto.StudentResponseDTO;
import com.example.thymeleaf.entity.Address;
import com.example.thymeleaf.entity.Student;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentMapper {

    public static Student toEntity(StudentRequestDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setBirthday(dto.getBirthday());

        Address address = AddressMapper.toEntity(dto.getAddress());
        address.setStudent(student);

        student.setAddress(address);
        return student;
    }

    public static StudentResponseDTO toDTO(Student student) {
        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setBirthday(student.getBirthday());
        dto.setCreatedAt(student.getCreatedAt());
        dto.setAddress(AddressMapper.toDTO(student.getAddress()));
        return dto;
    }

    public static List<StudentResponseDTO> toDTO(List<Student> students) {
        return students.stream()
                .map(student -> toDTO(student))
                .collect(Collectors.toList());
    }

}