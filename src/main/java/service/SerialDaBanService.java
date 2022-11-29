/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import domainmodel.SerialDaBan;
import java.util.UUID;

/**
 *
 * @author FPT
 */
public interface SerialDaBanService {

    void add(SerialDaBan serialDaBan);
    
    void delete(UUID id);
    
}
