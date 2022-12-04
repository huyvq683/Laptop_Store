/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;
import domainmodel.OCung;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author huyxo
 */
public interface OCungService {
        List<OCung> getAll();

    OCung getOneOCung(String ma);

    OCung getOne(String ma);

    String add(OCung sp);

    String upDate(OCung sp, UUID id);

    String delete(UUID id);

    String validate(OCung sp);

    String validateOCung(OCung spham);

}
