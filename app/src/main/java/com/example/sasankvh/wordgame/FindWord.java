package com.example.sasankvh.wordgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FindWord extends AppCompatActivity {
public String str;
    ArrayList<String> EnteredWords=new ArrayList<String>();
    static ArrayList<String> dictionary=new ArrayList<String>();
    ArrayList<String> positions=new ArrayList<String>();
    int position=0;
    int words=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_word);
        Bundle Data=getIntent().getExtras();
        DictionaryToArrayList();
        str=new String(Data.getString("GivenWord").toString().trim());
    }

    public void onClick(View view){
        final TextView CheckingWord=(TextView)findViewById(R.id.CheckingWord);
        String word=CheckingWord.getText().toString().trim().toLowerCase();
        ListAdapter EnteredWordsAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,EnteredWords);
        ListView WordsListView=(ListView)findViewById(R.id.EnteredList);
        for(String ser:dictionary)
            Log.i("Words",ser);
            Intent i ;
        if (word.toString().equals(str.toString())) {
            i= new Intent(this, GiveWord.class);
            startActivity(i);
        }
        else if(!positions.contains(word) && !exit(word) && dictionary.contains(word)) {
                int[] arr=wordsCorrect(word,str);
                String word1="  "+word+"                         "+arr[0]+"                           "+arr[1];
                EnteredWords.add(word1);
                positions.add(word);
                CheckingWord.setText("");
                CheckingWord.setHint("Word");
                WordsListView.setAdapter(EnteredWordsAdapter);
        }
        else {
            CheckingWord.setText("");
            CheckingWord.setHint("Invalid");
        }

    }
    static boolean exit(String str){
        boolean flag=false;
        char[] a=str.trim().toLowerCase().toCharArray();
        HashSet<Character> check=new HashSet<Character>();
        for(Character ch:a)
            check.add(ch);
        if(check.size()==4)
            return false;
        else
            return true;
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
      int[] wordsCorrect(String word,String Givenword){
          char[] wor=word.toCharArray();
          char[] Gwor=Givenword.toCharArray();
          int[] posCor=new int[2];
          posCor[0]=0;
          posCor[1]=0;
          for(int i=0;i<word.length();i++){
              for(int j=0;j<Givenword.length();j++){
                  if(wor[i]==Gwor[j] && i==j)
                      posCor[1]++;
                  else if(wor[i]==Gwor[j] && i!=j)
                      posCor[0]++;
              }
          }
          return posCor;
      }
    @Override
    public void onBackPressed() {

    }
}
