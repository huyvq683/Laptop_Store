/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import domainmodel.Hang;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author huyxo
 */
public interface HangService {
        List<Hang> getAll();

    Hang getOneHang(String ma);

    Hang getOne(String ma);

    String add(Hang sp);

    String upDate(Hang sp, UUID id);

    String delete(UUID id);

    String validate(Hang sp);

    String validateHang(Hang spham);

}
