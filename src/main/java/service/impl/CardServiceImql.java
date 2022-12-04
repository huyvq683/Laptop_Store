/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import domainmodel.CardMH;
import java.util.List;
import java.util.UUID;
import repository.impl.CPURepository;
import repository.impl.CardRepository;
import service.CardService;

/**
 *
 * @author huyxo
 */
public class CardServiceImql implements CardService{

    CardRepository cardRepo = new CardRepository();

    @Override
    public List<CardMH> getAll() {
        return cardRepo.getAll();
    }

    @Override
    public String add(CardMH sp) {
        if (validate(sp) != null) {
            return validate(sp);
        } else {
            boolean add = cardRepo.add(sp);
            if (add) {
                return "Thêm thành công";
            } else {
                return "Thêm không thành công";
            }
        }
    }

    @Override
    public String upDate(CardMH sp, UUID id) {
        if (validateCard(sp) != null) {
            return validateCard(sp);
        } else {
            boolean updateSP = cardRepo.update(sp, id);
            if (updateSP) {
                return "Sửa thành công";
            } else {
                return "Sửa không thành công";
            }
        }
    }

    @Override
    public String delete(UUID id) {
        boolean deleteSP = cardRepo.delete(id);
        if (deleteSP) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public String validate(CardMH sp) {

        if (sp.getTen().trim().isBlank()) {
            return "Tên trống";
        }
        if (getOne(sp.getMa()) != null) {
            return "Trùng mã";
        }
        return null;
    }

    @Override
    public CardMH getOne(String ma) {
        return cardRepo.getOne(ma);
    }

    @Override
    public CardMH getOneCard(String ma) {
        return cardRepo.getOneCard(ma);
    }

    @Override
    public String validateCard(CardMH spham) {
        if (spham.getTen().isEmpty()) {
            return "Tên trống";
        }
        return null;
    }

    @Override
    public List<CardMH> search(String seatchKey) {
        return cardRepo.search(seatchKey);
    }
}
