package com.OzProject.couponSystem.mapper;

import java.io.*;
import java.util.*;

public interface Mapper<DAO, DTO> {

    DAO toBean(DTO dto) throws IOException;

    DTO toDto(DAO dao) throws IOException;

    List<DAO> toBeanList(List<DTO> dtos);

    List<DTO> toDtoList(List<DAO> daos);
}

