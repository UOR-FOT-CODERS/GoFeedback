/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Mithila
 */
public interface Resourse extends Remote{
    public ArrayList<Question> getQuestions() throws RemoteException;
    public int getQuestionsItems()throws RemoteException;
    public void setResults(String name,int result) throws RemoteException;
    
}
