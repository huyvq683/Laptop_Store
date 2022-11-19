package service.impl;

import custommodel.ViewChucVuCustomModel;
import domainModel.NhanVien;
import java.util.List;
import repository.ChucVuRepository;
import service.ViewChucVuService;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author WIN11
 */
public class ViewChucVuServiceImpl implements ViewChucVuService {

    private ChucVuRepository ChucVuRepository = new ChucVuRepository();

    @Override
    public List<ViewChucVuCustomModel> getAll() {
        return ChucVuRepository.getAll();
    }

}
