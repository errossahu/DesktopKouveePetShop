/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author ACER
 */
public class dataKosong extends Exception{
    public String message()
    {
        return "Data Tidak Boleh Kosong ";
    }
}
