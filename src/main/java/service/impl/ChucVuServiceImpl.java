package service.impl;

import customModel.ChucVuResponse;
import java.util.List;
import repository.impl.ChucVuRepository;
import service.ChucVuService;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author WIN11
 */
public class ChucVuServiceImpl implements ChucVuService {

    private ChucVuRepository ChucVuRepository = new ChucVuRepository();

    @Override
    public List<ChucVuResponse> getAll() {
        return ChucVuRepository.getAll();
    }

}
