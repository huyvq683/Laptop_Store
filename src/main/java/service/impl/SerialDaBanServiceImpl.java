/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.List;
import repository.impl.SerialDaBanRepository;
import service.SerialDaBanService;

/**
 *
 * @author FPT
 */
public class SerialDaBanServiceImpl implements SerialDaBanService {

    private SerialDaBanRepository serialDaBanRepository = new SerialDaBanRepository();

    @Override
    public void add(List<String>listSerial) {
         serialDaBanRepository.add(listSerial);
    }

    @Override
    public void delete(List<String> listSerial) {
        Boolean delete = serialDaBanRepository.delete(listSerial);
    }
}
