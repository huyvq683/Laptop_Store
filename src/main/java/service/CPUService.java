/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import domainmodel.CPU;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author huyxo
 */
public interface CPUService {
    List<CPU> getAll();

    CPU getOneCPU(String ma);

    CPU getOne(String ma);

    String add(CPU sp);

    String upDate(CPU sp, UUID id);

    String delete(UUID id);

    String validate(CPU sp);

    String validateCPU(CPU spham);

    List<CPU> search(String seatchKey);
}
