/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import domainmodel.CardMH;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author huyxo
 */
public interface CardService {

    List<CardMH> getAll();

    CardMH getOneCard(String ma);

    CardMH getOne(String ma);

    String add(CardMH sp);

    String upDate(CardMH sp, UUID id);

    String delete(UUID id);

    String validate(CardMH sp);

    String validateCard(CardMH spham);

    List<CardMH> search(String seatchKey);
}
