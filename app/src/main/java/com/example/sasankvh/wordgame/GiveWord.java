package com.example.sasankvh.wordgame;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class GiveWord extends AppCompatActivity {
    ArrayList<String> dictionary=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_word);
        DictionaryToArrayList();
    }
   public void onClick(View view) {
       final TextView GivenWord = (TextView) findViewById(R.id.GiveWord);
       final Button SetWord = (Button) findViewById(R.id.SetWord);
       String str = (String) GivenWord.getText().toString().trim().toLowerCase();
       Intent i = new Intent(this, FindWord.class);
       i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
       i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       if (checkString(str) && dictionary.contains(str.toLowerCase().toString().trim())) {
           i.putExtra("GivenWord", str);
           startActivity(i);

       } else {
           GivenWord.setText("");
           GivenWord.setHint("INVALID");
       }
   }
   static boolean checkString(String str){
       char[] a=str.trim().toLowerCase().toCharArray();
       HashSet<Character> set=new HashSet<Character>();
       for(Character c:a)
           set.add(c);
       for(Character c:set)
           System.out.print(c);
       if(set.size()==4)
           return true;
       else
           return false;
   }
    void DictionaryToArrayList(){
        String data="";
        StringBuffer sbuffer=new StringBuffer();
        InputStream is=this.getResources().openRawResource(R.raw.dictionary);
        BufferedReader x=new BufferedReader(new InputStreamReader(is));
        if(is !=null){
            try{
                while((data=x.readLine())!=null){
                    data=data.toLowerCase().toString().trim();
                    dictionary.add(data);
                    System.out.println("Done");
                }
                is.close();
                for(String s:dictionary)
                    System.out.println(s);
            } catch (IOException e) {
                System.out.println("Could not load the file");
            }
        }
        String str;

    }
   @Override
    public void onBackPressed() {
        System.out.println("Pressed");
    }
}
