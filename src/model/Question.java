/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Mithila
 */
public class Question implements Serializable{
    private int id;
    private String question;
    private String ans1;
    private String ans2;
    private String ans3;
    private String currect_ans1;

    
      
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return the ans1
     */
    public String getAns1() {
        return ans1;
    }

    /**
     * @param ans1 the ans1 to set
     */
    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }

    /**
     * @return the ans2
     */
    public String getAns2() {
        return ans2;
    }

    /**
     * @param ans2 the ans2 to set
     */
    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }

    /**
     * @return the ans3
     */
    public String getAns3() {
        return ans3;
    }

    /**
     * @param ans3 the ans3 to set
     */
    public void setAns3(String ans3) {
        this.ans3 = ans3;
    }

    /**
     * @return the currect_ans1
     */
    public String getCurrect_ans1() {
        return currect_ans1;
    }

    /**
     * @param currect_ans1 the currect_ans1 to set
     */
    public void setCurrect_ans1(String currect_ans1) {
        this.currect_ans1 = currect_ans1;
    }
    
    
}
