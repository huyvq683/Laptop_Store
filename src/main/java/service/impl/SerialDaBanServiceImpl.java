/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import domainmodel.SerialDaBan;
import java.util.UUID;
import repository.impl.SerialDaBanRepository;
import service.SerialDaBanService;

/**
 *
 * @author FPT
 */
public class SerialDaBanServiceImpl implements SerialDaBanService {

    private SerialDaBanRepository serialDaBanRepository = new SerialDaBanRepository();

    @Override
    public void add(SerialDaBan serialDaBan) {
         serialDaBanRepository.add(serialDaBan);
    }

    @Override
    public void delete(UUID id) {
        serialDaBanRepository.delete(id);
    }

}
