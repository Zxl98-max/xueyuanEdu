package com.online.edu.eduservice.entity.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EduchapterDto {
    private String id;
    private String title;
    //小节集合
    private List<EduVideoDto> children =new ArrayList<>();

}
