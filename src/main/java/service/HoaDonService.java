/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import domainmodel.HoaDon;
import java.util.List;

/**
 *
 * @author WIN11
 */
public interface HoaDonService {

    public List<HoaDon> getByOne(String tt);

    public List<HoaDon> getAll();
}
