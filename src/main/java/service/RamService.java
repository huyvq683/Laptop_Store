/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import domainmodel.Ram;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author huyxo
 */
public interface RamService {

    List<Ram> getAll();

    Ram getOneRam(String ma);

    Ram getOne(String ma);

    String add(Ram sp);

    String upDate(Ram sp, UUID id);

    String delete(UUID id);

    String validate(Ram sp);

    String validateRam(Ram spham);

}
