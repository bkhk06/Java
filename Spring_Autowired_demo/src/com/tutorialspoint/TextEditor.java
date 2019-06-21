package com.tutorialspoint;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Liu.DA on 2019/6/18
 */
public class TextEditor {
    //private SpellChecker spellChecker;

    private  SpellChecker spellChecker;
    /*
    * 可以在属性中使用 @Autowired 注释来除去 setter 方法。当时使用 为自动连接属性传递的时候，
    * Spring 会将这些传递过来的值或者引用自动分配给那些属性。
    * */
    /*public void setSpellChecker(SpellChecker spellChecker){
        this.spellChecker = spellChecker;
    }*/
    @Autowired
    public TextEditor(SpellChecker spellChecker){
        System.out.println("Inside TextEditor constructor.");
        this.spellChecker = spellChecker;
    }
    public SpellChecker getSpellChecker(){
        return spellChecker;
    }

    public void spellCheck(){
        spellChecker.checkSpelling();
    }
}
